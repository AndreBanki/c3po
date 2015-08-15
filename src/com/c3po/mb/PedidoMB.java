package com.c3po.mb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.c3po.dao.ClienteDAO;
import com.c3po.dao.PedidoDAO;
import com.c3po.dao.ProdutoDAO;
import com.c3po.entidade.Cliente;
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
	private ProdutoDAO produtoDao;
	
	public PedidoMB() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		String cpf = (String)session.getAttribute("cpfUsuario");
		
		ClienteDAO clienteDao = new ClienteDAO();
		this.cliente = clienteDao.buscaPorCpf(cpf);

		pedido = new Pedido();
		pedido.setCliente(this.cliente);
		
		// lista de todos os produtos (não muda durante a edição da página)
		produtoDao = new ProdutoDAO();
		produtos = produtoDao.listarTodos();
	}
	
// métodos auxiliares
	
	public void atualizaListaItensParaExibicao() {
		itens = pedido.getItempedidoList();
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
	
// métodos para acesso ao BD	
	
	public void apagarItem() {
		dao.retirarItem(itemEmEdicao);
		atualizaListaItensParaExibicao();
	}
	
	public void inserirItem() {
		if (itens.isEmpty()) {
			dao.inserir(pedido);
		}
		itemEmEdicao.setPedido(pedido);
		dao.inserirItem(itemEmEdicao);
		atualizaListaItensParaExibicao();
	}
	
// getters e setters	
	
	public List<ItemPedido> getItens(){		
		return itens;
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setItemEmEdicao(ItemPedido itemEmEdicao) {
		this.itemEmEdicao = itemEmEdicao;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public ItemPedido getItemEmEdicao() {
		return itemEmEdicao;
	}

	public void setPedidoEmEdicao(ItemPedido item) {
		this.itemEmEdicao = item;
	}

}
