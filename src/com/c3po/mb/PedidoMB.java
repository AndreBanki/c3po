package com.c3po.mb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

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
	
	private PedidoDAO dao;

	private ItemPedido itemEmEdicao;
	private List<ItemPedido> itens;
	
	private List<Produto> produtos;
	private ProdutoDAO produtoDao;
	
	@PostConstruct
	public void init() {
		pedido = new Pedido(); // TODO (vai receber de onde?)
		
		dao = new PedidoDAO();
		atualizaListaItensParaExibicao();
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
		dao.inserirItem(itemEmEdicao);
		atualizaListaItensParaExibicao();
	}
	
// getters e setters	
	
	public List<ItemPedido> getItens(){		
		return itens;
	}

	public ItemPedido getItemEmEdicao() {
		return itemEmEdicao;
	}

	public void setPedidoEmEdicao(ItemPedido item) {
		this.itemEmEdicao = item;
	}

}
