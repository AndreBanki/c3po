package com.c3po.entidade;

public class ItemPedido {
	
	private int id;
	private Produto item;
	private int quantidade;
	
	public float getTotal() {
		float total = quantidade * item.getValor();
		return total;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Produto getItem() {
		return item;
	}
	public void setItem(Produto item) {
		this.item = item;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
