package com.c3po.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


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
	

// m�todos para acesso ao BD	
	
	public void apagarProduto() {
		dao.apagar(produtoEmEdicao);
		atualizaListaProdutosParaExibicao();
	}
	
	public void inserirProduto() {
		dao.salvar(produtoEmEdicao);
        atualizaListaProdutosParaExibicao();
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
