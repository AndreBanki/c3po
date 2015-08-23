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
		String cpf = autenticacaoMB.getCpf();
		
		ClienteDAO clienteDao = new ClienteDAO();
		this.cliente = clienteDao.buscaPorCpf(cpf);
                
        dao = new PedidoDAO();
		if(cliente != null){
        	pedido = dao.pedidoAberto(cliente);
        }
        if (pedido == null){
        	pedido = new Pedido();
            pedido.setCliente(this.cliente);
            this.pedidoRecuperado = false;
        }
        else
        	this.pedidoRecuperado = true;
		
		atualizaListaItensParaExibicao();
		
		// lista de todos os produtos (n�o muda durante a edi��o da p�gina)
		ProdutoDAO produtoDao = new ProdutoDAO();
		produtos = produtoDao.listarTodos();
                
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
			FuncionarioDAO funcionarioDao = new FuncionarioDAO();
			Funcionario funcionarioDefault = funcionarioDao.buscaId(1); // TODO
			pedido.setFuncionario(funcionarioDefault);
			
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
