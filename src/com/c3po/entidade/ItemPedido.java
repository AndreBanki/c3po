package com.c3po.entidade;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;	
import javax.persistence.Table;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "itempedido")
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantidade")
    private int quantidade;
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto produto;
    
    public float getTotal() {
		float total = quantidade * produto.getValor();
		return total;
	}
    

    public ItemPedido() {
    	this.quantidade = 1;
    }

    public ItemPedido(Integer id) {
        this.id = id;
        this.quantidade = 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPedido)) {
            return false;
        }
        ItemPedido other = (ItemPedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "teste.Itempedido[ id=" + id + " ]";
    }
    
    
}