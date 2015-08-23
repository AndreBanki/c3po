package com.c3po.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.c3po.entidade.Produto;

public class ProdutoDAO extends BaseDAO{

// m�todos de busca
	
	public Produto buscaPorNome(String nome) {
		EntityManager manager = getConnection();
        manager.getTransaction().begin();
		Produto Produto = null;
		try {
			Query q = manager.createQuery("select c from Produto c where c.descricao like '%" + nome + "%'");
			Produto = (Produto) q.getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return Produto;
	}
	
	public Produto buscaId(int id) {
		EntityManager manager = getConnection();
        manager.getTransaction().begin();
		Produto Produto = null;
		try {
			Query q = manager.createQuery("select c from Produto c where c.id=" + id);
			Produto = (Produto) q.getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return Produto;
	}
		
// m�todos CRUD	
	
	public List<Produto> listarTodos() {
		List<Produto> lista = new ArrayList<Produto>();
		EntityManager manager = getConnection();
        manager.getTransaction().begin();
		try {
			Query q = manager.createQuery("select c from Produto c order by c.descricao");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return lista;
	}
		
	public void salvar(Produto Produto) {
		EntityManager manager = getConnection();
		try {
                        manager.getTransaction().begin();
			manager.merge(Produto);
                        manager.getTransaction().commit();
		} catch (Exception e) {
                        manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			fechar();
		}
	}
		
		
	public void apagar(Produto Produto) {
		EntityManager manager = getConnection();
		try {
                        manager.getTransaction().begin();
			Produto = manager.find(Produto.class, Produto.getId());
			manager.remove(Produto);
                        manager.getTransaction().commit();
		} catch (Exception e) {
                        manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			fechar();
		}
	}

}

