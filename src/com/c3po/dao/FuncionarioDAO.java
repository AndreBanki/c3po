package com.c3po.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.c3po.entidade.Cliente;
import com.c3po.entidade.Funcionario;

public class FuncionarioDAO extends BaseDAO {

// métodos de busca
	
	public Funcionario buscaPorCpf(String cpf) {
		EntityManager manager = getConnection();
        manager.getTransaction().begin();
		Funcionario funcionario = null;
		try {
			Query q = manager.createQuery("select c from Funcionario c where c.cpf='" + cpf + "'");
			funcionario = (Funcionario) q.getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return funcionario;
	}
	
	public Funcionario buscaId(int id) {
		EntityManager manager = getConnection();
        manager.getTransaction().begin();
		Funcionario funcionario = null;
		try {
			Query q = manager.createQuery("select c from Funcionario c where c.id=" + id);
			funcionario = (Funcionario) q.getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return funcionario;
	}
		
// métodos CRUD	
	
	public List<Funcionario> listarTodos() {
		List<Funcionario> lista = new ArrayList<Funcionario>();
		EntityManager manager = getConnection();
        manager.getTransaction().begin();
		try {
			Query q = manager.createQuery("select c from Funcionario c order by c.nome");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return lista;
	}
		
	public void salvar(Funcionario funcionario) {
		EntityManager manager = getConnection();
		try {
			manager.merge(funcionario);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
	}
		
		
	public void apagar(Funcionario funcionario) {
		EntityManager manager = getConnection();
		try {
			funcionario = manager.find(Funcionario.class, funcionario.getId());
			manager.merge(funcionario);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
	}

}
