package com.c3po.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.c3po.dao.PedidoDAO;
import com.c3po.entidade.ItemPedido;
import com.c3po.entidade.Pedido;

@ManagedBean
@ViewScoped
public class PedidosMB {
	
	PedidoDAO dao;
	private List<Pedido> pedidos;
	private Pedido pedidoEmEdicao;
	
	public PedidosMB() {
		dao = new PedidoDAO();
		pedidos = dao.listarTodos();
	}

	public void apagarPedido() {
		dao.apagar(pedidoEmEdicao);
		
		pedidoEmEdicao = new Pedido();
		pedidos = dao.listarTodos();
	}	
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido getPedidoEmEdicao() {
		return pedidoEmEdicao;
	}

	public void setPedidoEmEdicao(Pedido pedidoEmEdicao) {
		this.pedidoEmEdicao = pedidoEmEdicao;
	}
}
