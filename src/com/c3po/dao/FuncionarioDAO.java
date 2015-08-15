package com.c3po.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.c3po.entidade.Funcionario;

public class FuncionarioDAO extends BaseDAO {

// m�todos de busca
	
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
		
// m�todos CRUD	
	
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
                        manager.getTransaction().begin();
			manager.merge(funcionario);
                        manager.getTransaction().commit();
		} catch (Exception e) {
                        manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			fechar();
		}
	}
		
		
	public void apagar(Funcionario funcionario) {
		EntityManager manager = getConnection();
		try { 
                        manager.getTransaction().begin();
			funcionario = manager.find(Funcionario.class, funcionario.getId());
			manager.remove(funcionario);
                        manager.getTransaction().commit();
		} catch (Exception e) {
                        manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			fechar();
		}
	}

}
