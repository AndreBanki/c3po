package com.c3po.dao;

import java.util.ArrayList;
import java.util.List;

import com.c3po.entidade.Funcionario;

public class FuncionarioDAO {

// métodos de busca
	
	public Funcionario buscaPorCpf(String cpf) {
		Funcionario funcionario = new Funcionario();
		return funcionario;
	}
	
	public Funcionario buscaId(int id) {
		Funcionario funcionario = new Funcionario();
		return funcionario;
	}
		
// métodos CRUD	
	
	public List<Funcionario> listarTodos() {
		List<Funcionario> lista = new ArrayList<Funcionario>();
		return lista;
	}
		
	public void inserir(Funcionario funcionario) {
	}
		
	public void atualizar(Funcionario funcionario) {
	}
		
	public void apagar(Funcionario funcionario) {
	}

}
