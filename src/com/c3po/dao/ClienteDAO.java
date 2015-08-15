package com.c3po.dao;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.c3po.entidade.Cliente;


public class ClienteDAO extends BaseDAO{

// métodos de busca
	
	public Cliente buscaPorCpf(String cpf) {
		EntityManager manager = getConnection();
        manager.getTransaction().begin();
		Cliente cliente = null;
		try {
			Query q = manager.createQuery("select c from Cliente c where c.cpf='" + cpf + "'");
			cliente = (Cliente) q.getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return cliente;
	}
	
	public Cliente buscaId(int id) {
		EntityManager manager = getConnection();
        manager.getTransaction().begin();
		Cliente cliente = null;
		try {
			Query q = manager.createQuery("select c from Cliente c where c.id=" + id);
			cliente = (Cliente) q.getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return cliente;
	}
		
// métodos CRUD	
	
	public List<Cliente> listarTodos() {
		List<Cliente> lista = new ArrayList<Cliente>();
		EntityManager manager = getConnection();
        manager.getTransaction().begin();
		try {
			Query q = manager.createQuery("select c from Cliente c order by c.nome");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return lista;
	}
		
	public void salvar(Cliente cliente) {
		EntityManager manager = getConnection();
		try {
			manager.merge(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
	}
		
		
	public void apagar(Cliente cliente) {
		EntityManager manager = getConnection();
		try {
			cliente = manager.find(Cliente.class, cliente.getId());
			manager.merge(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
	}

}
