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
	
	
// métodos que lidam com a lista geral de Pedidos	

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
			manager.merge(pedido);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
	}
		
// métodos que lidam com a lista de ItemPedido de um Pedido	

	
			
	public void inserirItem(Pedido pedido, ItemPedido item) {
		EntityManager manager = getConnection();
		try {
			manager.merge(item);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
	}


	public void retirarItem(ItemPedido item) {
		EntityManager manager = getConnection();
		try {
			item = manager.find(ItemPedido.class, item.getId());
			manager.merge(item);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
	}

}
