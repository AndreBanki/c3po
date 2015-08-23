package com.c3po.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
	
	public PedidoMB() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		String cpf = (String)session.getAttribute("cpfUsuario");
		dao = new PedidoDAO();
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
		
		// lista de todos os produtos (nï¿½o muda durante a ediï¿½ï¿½o da pï¿½gina)
		ProdutoDAO produtoDao = new ProdutoDAO();
		produtos = produtoDao.listarTodos();
                
	}
	
// mï¿½todos auxiliares
	
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
	
// mï¿½todos para acesso ao BD	
	
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
	
	public List<ItemPedido> getItens(){		
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setItemEmEdicao(ItemPedido itemEmEdicao) {
		this.itemEmEdicao = itemEmEdicao;
	}

	public ItemPedido getItemEmEdicao() {
		return itemEmEdicao;
	}

	// métodos apenas para consulta

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
