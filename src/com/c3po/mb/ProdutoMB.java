package com.c3po.mb;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.c3po.dao.ProdutoDAO;
import com.c3po.entidade.Produto;

@ManagedBean
@ViewScoped
public class ProdutoMB {

	private Produto produtoEmEdicao;
	private List<Produto> produtos;
	private ProdutoDAO dao;

	@PostConstruct
	public void init() {
		dao = new ProdutoDAO();
		atualizaListaProdutosParaExibicao();
	}
	
// m�todos auxiliares
	
	public void atualizaListaProdutosParaExibicao() {
		produtos = dao.listarTodos();
		limpaProdutoEmEdicao();
	}
	
	public void limpaProdutoEmEdicao() {
		produtoEmEdicao = new Produto();
	}
	
	private Produto produtoMesmoNome(Produto produto) {
		Produto prodIgual = new Produto();
		for (Iterator<Produto> iterator = produtos.iterator(); iterator.hasNext() && prodIgual.getId() == null; ) {    
			Produto prod = (Produto) iterator.next();    
			if (prod.getDescricao().equals(produto.getDescricao()))
				prodIgual = prod;
		}
		return prodIgual;
	}	
	
// m�todos para acesso ao BD	
	
	public void apagarProduto() {
		dao.apagar(produtoEmEdicao);
		atualizaListaProdutosParaExibicao();
	}
	
	public void inserirProduto() {
		Produto produtoMesmoNome = produtoMesmoNome(produtoEmEdicao);
		if (produtoMesmoNome.getId() != produtoEmEdicao.getId()) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.addCallbackParam("jaExisteNome", true);
		}
		else {
			dao.salvar(produtoEmEdicao);
                        atualizaListaProdutosParaExibicao();
		}
	}
	
// getters e setters	
	
	public List<Produto> getProdutos(){		
		return produtos;
	}

	public Produto getProdutoEmEdicao() {
		return produtoEmEdicao;
	}

	public void setProdutoEmEdicao(Produto produto) {
		this.produtoEmEdicao = produto;
	}
}
