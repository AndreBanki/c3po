package com.c3po.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.c3po.entidade.Cliente;
import com.c3po.entidade.ItemPedido;
import com.c3po.entidade.Pedido;
import com.c3po.entidade.Produto;

public class PedidoDAO extends BaseDAO{
	
	
// m�todos que lidam com a lista geral de Pedidos	

	public List<Pedido> listarTodos() {
		List<Pedido> lista = new ArrayList<Pedido>();
		EntityManager manager = getConnection();
        manager.getTransaction().begin();
		try {
			Query q = manager.createQuery("select p from Pedido p order by p.id");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return lista;
	}
		
	public void inserir(Pedido pedido) {
		EntityManager manager = getConnection();
		try {
                        manager.getTransaction().begin();
			manager.merge(pedido);
                        manager.getTransaction().commit();
		} catch (Exception e) {
                        manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			fechar();
		}
	}
		
// m�todos que lidam com a lista de ItemPedido de um Pedido	

	
			
	public void inserirItem(ItemPedido item) {
		EntityManager manager = getConnection();
		try {
                        manager.getTransaction().begin();
			manager.merge(item);
                        manager.getTransaction().commit();
		} catch (Exception e) {
                        manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			fechar();
		}
	}


	public void retirarItem(ItemPedido item) {
		EntityManager manager = getConnection();
		try {
                        manager.getTransaction().begin();
			item = manager.find(ItemPedido.class, item.getId());
			manager.merge(item);
                        manager.getTransaction().commit();
		} catch (Exception e) {
                        manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			fechar();
		}
	}

}
