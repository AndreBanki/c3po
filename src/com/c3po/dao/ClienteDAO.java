package com.c3po.dao;

import java.util.ArrayList;
import java.util.List;

import com.c3po.entidade.Cliente;

public class ClienteDAO {

// métodos de busca
	
	public Cliente buscaPorNome(String nome) {
		Cliente cliente = new Cliente();
		return cliente;
	}
	
	public Cliente buscaId(int id) {
		Cliente cliente = new Cliente();
		return cliente;
	}
		
// métodos CRUD	
	
	public List<Cliente> listarTodos() {
		List<Cliente> lista = new ArrayList<Cliente>();
		return lista;
	}
		
	public void inserir(Cliente cliente) {
	}
		
	public void atualizar(Cliente cliente) {
	}
		
	public void apagar(Cliente cliente) {
	}

}
