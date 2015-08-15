package com.c3po.dao;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.c3po.entidade.Cliente;


public class ClienteDAO extends BaseDAO{

// m�todos de busca
	
	public Cliente buscaPorCpf(String cpf) {
		EntityManager manager = getConnection();
        manager.getTransaction().begin();
		Cliente cliente = null;
		try {
			Query q = manager.createQuery("select c from Cliente c where c.cpf='" + cpf + "'");
                        if (q.getResultList().size()>0){
                            cliente = (Cliente) q.getSingleResult();			
                        }
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
		
// m�todos CRUD	
	
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
                        manager.getTransaction().begin();
			manager.merge(cliente);
                        manager.getTransaction().commit();
		} catch (Exception e) {
                        manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			fechar();
		}
	}
		
		
	public void apagar(Cliente cliente) {
		EntityManager manager = getConnection();
		try {
                        manager.getTransaction().begin();
			cliente = manager.find(Cliente.class, cliente.getId());
			manager.remove(cliente);
                        manager.getTransaction().commit();
		} catch (Exception e) {
                        manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			fechar();
		}
	}

}
