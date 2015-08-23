package com.c3po.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.c3po.dao.ClienteDAO;
import com.c3po.dao.FuncionarioDAO;
import com.c3po.dao.PedidoDAO;
import com.c3po.dao.ProdutoDAO;
import com.c3po.entidade.Cliente;
import com.c3po.entidade.Funcionario;
import com.c3po.entidade.ItemPedido;
import com.c3po.entidade.Pedido;
import com.c3po.entidade.Produto;

@ManagedBean
@ViewScoped
public class PedidoMB {
	
	private Pedido pedido;
	private Cliente cliente;
	private Funcionario funcionario;
	
	private PedidoDAO dao;

	private ItemPedido itemEmEdicao;
	private List<ItemPedido> itens;
	
	private List<Produto> produtos;
        
    private float valorTotal;
    private Boolean pedidoRecuperado;
    
    @ManagedProperty(value="#{autenticacaoMB}")
    private AutenticacaoMB autenticacaoMB;
	
	@PostConstruct
	public void init() {
        dao = new PedidoDAO();
		ClienteDAO clienteDao = new ClienteDAO();
		FuncionarioDAO funcionarioDao = new FuncionarioDAO();
		ProdutoDAO produtoDao = new ProdutoDAO();
		produtos = produtoDao.listarTodos();

        String cpf = autenticacaoMB.getCpf();
        Boolean selfService = autenticacaoMB.getSelfService();
        // procura por um pedido aberto do cliente ou do funcion�rio
        if (selfService == true) {
    		cliente = clienteDao.buscaPorCpf(cpf);
    		if(cliente != null)
            	pedido = dao.pedidoAberto(cliente);
        } else {
    		funcionario = funcionarioDao.buscaPorCpf(cpf);
    		if(funcionario != null)
            	pedido = dao.pedidoAberto(funcionario);
        }
		// se nao encontrou, cria
        if (pedido == null){
        	pedido = new Pedido();
        	if (selfService == true) 
    			funcionario = funcionarioDao.buscaId(1);
        	else 
        		cliente = clienteDao.buscaId(1);
        	
    		pedido.setCliente(cliente);
			pedido.setFuncionario(funcionario);
            this.pedidoRecuperado = false;
        }
        else
        	this.pedidoRecuperado = true;
		
		atualizaListaItensParaExibicao();
	}
	
// m�todos auxiliares
	
	   public void atualizaListaItensParaExibicao() {
        if (pedido.getId() != null) {
            itens = dao.listarTodosItens(pedido);
            pedido.setItempedidoList(itens);
            valorTotal = pedido.getValorTotal();
        }
        itemEmEdicao = new ItemPedido();
    }
	
	public List<String> listaNomesProdutos() {
		List<String> nomes = new ArrayList<String>();
		for (Iterator<Produto> iterator = produtos.iterator(); iterator.hasNext(); ) {    
			Produto p = (Produto) iterator.next();    
			nomes.add(p.getDescricao());    
		}  
		return nomes;
	}
	
// m�todos para acesso ao BD	
	
	public void apagarItem() {
		itemEmEdicao.setPedido(pedido);
		dao.retirarItem(itemEmEdicao);
		
		atualizaListaItensParaExibicao();
	}
	
	public void inserirItem() {
		if (itens == null) {
			pedido.setData(new Date());
			pedido.setSituacao(0);
			pedido = dao.inserir(pedido);
		}
		itemEmEdicao.setPedido(pedido);
		dao.inserirItem(itemEmEdicao);
		
		atualizaListaItensParaExibicao();
	}
        
        public String finalizar(){
            pedido.setSituacao(1);
            pedido = dao.inserir(pedido);
            valorTotal= pedido.getValorTotal();
            return "finalizado";
        }
        
        public void cancelar(){
        	int nItems = itens.size();
            for (int i=0; i<nItems; i++)
                dao.retirarItem(itens.get(i));
            
            dao.apagar(pedido);
            
            pedido = new Pedido();
            pedido.setCliente(this.cliente);
            itens = null;
            pedidoRecuperado = false;
            atualizaListaItensParaExibicao();
        }
	
// getters e setters
        
	public void setAutenticacaoMB(AutenticacaoMB autenticacaoMB) {
		this.autenticacaoMB = autenticacaoMB;
	}

	public List<ItemPedido> getItens(){		
		return itens;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setItemEmEdicao(ItemPedido itemEmEdicao) {
		this.itemEmEdicao = itemEmEdicao;
	}

	public ItemPedido getItemEmEdicao() {
		return itemEmEdicao;
	}

    public float getValorTotal() {
        return valorTotal;
    }

	public List<Produto> getProdutos() {
		return produtos;
	}

	public Boolean getPedidoRecuperado() {
		return pedidoRecuperado;
	}
}
