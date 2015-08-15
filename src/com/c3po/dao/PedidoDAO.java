package com.c3po.dao;

import java.util.ArrayList;
import java.util.List;

import com.c3po.entidade.ItemPedido;
import com.c3po.entidade.Pedido;
import com.c3po.entidade.Produto;

public class PedidoDAO {
	
	
// métodos que lidam com a lista geral de Pedidos	

	public List<Pedido> listarTodos() {
		List<Pedido> lista = new ArrayList<Pedido>();
		return lista;
	}
		
	public void inserir(Pedido pedido) {
	}
		
// métodos que lidam com a lista de ItemPedido de um Pedido	

	public List<ItemPedido> listarItens(Pedido pedido) {
		List<ItemPedido> lista = new ArrayList<ItemPedido>();
		return lista;
	}
			
	public void inserirItem(Pedido pedido, ItemPedido item) {
	}

	public void atualizarItem(Pedido pedido, ItemPedido item) {
	}

	public void retirarItem(Pedido pedido, ItemPedido item) {
	}

}
