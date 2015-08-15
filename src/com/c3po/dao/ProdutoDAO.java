package com.c3po.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.c3po.entidade.Produto;

public class ProdutoDAO extends BaseDAO{

// métodos de busca
	
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
		
// métodos CRUD	
	
	public List<Produto> listarTodos() {
		List<Produto> lista = new ArrayList<Produto>();
		EntityManager manager = getConnection();
        manager.getTransaction().begin();
		try {
			Query q = manager.createQuery("select c from Produto c order by c.nome");
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
			manager.merge(Produto);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
	}
		
		
	public void apagar(Produto Produto) {
		EntityManager manager = getConnection();
		try {
			Produto = manager.find(Produto.class, Produto.getId());
			manager.merge(Produto);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
	}

}

