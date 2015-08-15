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

	public List<Pedido> listarTodosCliente(Cliente cliente) {
		List<Pedido> lista = new ArrayList<Pedido>();
		EntityManager manager = getConnection();
                manager.getTransaction().begin();
		try {
			Query q = manager.createQuery("select p from Pedido p where p.cliente.id=" + cliente.getId() + " order by p.id");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return lista;
	}
        
        public Pedido pedidoAberto(Cliente cliente) {
		EntityManager manager = getConnection();
                manager.getTransaction().begin();
                Pedido pedido=null;
		try {
			Query q = manager.createQuery("select p from Pedido p where p.cliente.id=" + cliente.getId() + " and p.situacao=0  order by p.id");
                        if (q.getResultList().size()>0){
                            pedido = (Pedido) q.getSingleResult();
                        }
                        return pedido;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return pedido;
	}
		
	public Pedido inserir(Pedido pedido) {
		EntityManager manager = getConnection();
		try {
                        manager.getTransaction().begin();
			pedido = manager.merge(pedido);
                        manager.getTransaction().commit();
                        return pedido;
		} catch (Exception e) {
                        manager.getTransaction().rollback();
			e.printStackTrace();
                        return null;
		}finally{
			fechar();
		}
	}
        
        public void apagar(Pedido pedido) {
		EntityManager manager = getConnection();
		try {
                        manager.getTransaction().begin();
			pedido = manager.find(Pedido.class, pedido.getId());
			manager.remove(pedido);
                        manager.getTransaction().commit();
		} catch (Exception e) {
                        manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			fechar();
		}
	}
		
// m�todos que lidam com a lista de ItemPedido de um Pedido	

	
			
	public  ItemPedido inserirItem(ItemPedido item) {
		EntityManager manager = getConnection();
		try {
                        manager.getTransaction().begin();
			item = manager.merge(item);
                        manager.getTransaction().commit();
		} catch (Exception e) {
                        manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			fechar();
		}
                return item;
	}
        
        public List<ItemPedido> listarTodosItens(Pedido pedido) {
		List<ItemPedido> lista = new ArrayList<ItemPedido>();
		EntityManager manager = getConnection();
                manager.getTransaction().begin();
		try {
			Query q = manager.createQuery("select i from ItemPedido i where i.pedido.id=" + pedido.getId() + " order by i.id");
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return lista;
	}


	public void retirarItem(ItemPedido item) {
		EntityManager manager = getConnection();
		try {
                        manager.getTransaction().begin();
			item = manager.find(ItemPedido.class, item.getId());
			manager.remove(item);
                        manager.getTransaction().commit();
		} catch (Exception e) {
                        manager.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			fechar();
		}
	}

}
