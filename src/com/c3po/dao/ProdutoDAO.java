package com.c3po.dao;

import java.util.ArrayList;
import java.util.List;

import com.c3po.entidade.Produto;

public class ProdutoDAO {

// métodos de busca
	
	public Produto buscaPorNome(String nome) {
		Produto produto = new Produto();
		return produto;
	}
	
	public Produto buscaId(int id) {
		Produto produto = new Produto();
		return produto;
	}
		
// métodos CRUD	
	
	public List<Produto> listarTodos() {
		List<Produto> lista = new ArrayList<Produto>();
		return lista;
	}
		
	public void inserir(Produto produto) {
	}
		
	public void atualizar(Produto produto) {
	}
		
	public void apagar(Produto produto) {
	}

}
