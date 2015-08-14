package com.c3po.mb;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.c3po.dao.ClienteDAO;
import com.c3po.entidade.Cliente;

@ManagedBean
@ViewScoped
public class ClienteMB {

	private Cliente clienteEmEdicao;
	private List<Cliente> clientes;
	private ClienteDAO dao;

	@PostConstruct
	public void init() {
		dao = new ClienteDAO();
		atualizaListaClientesParaExibicao();
	}
	
// métodos auxiliares
	
	public void atualizaListaClientesParaExibicao() {
		clientes = dao.listarTodos();
		limpaClienteEmEdicao();
	}
	
	public void limpaClienteEmEdicao() {
		clienteEmEdicao = new Cliente();
	}
	
	private Cliente clienteMesmoNome(Cliente cliente) {
		Cliente fabIgual = new Cliente();
		for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext() && fabIgual.getId() == 0; ) {    
			Cliente f = (Cliente) iterator.next();    
			if (f.getNome().equals(cliente.getNome()))
				fabIgual = f;
		}
		return fabIgual;
	}	
	
// métodos para acesso ao BD	
	
	public void apagarCliente() {
		dao.apagar(clienteEmEdicao);
		atualizaListaClientesParaExibicao();
	}
	
	public void inserirCliente() {
		Cliente clienteMesmoNome = clienteMesmoNome(clienteEmEdicao);
		if (clienteMesmoNome.getId() != clienteEmEdicao.getId()) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.addCallbackParam("jaExisteNome", true);
		}
		else {
			if (clienteEmEdicao.getId() == 0)
				dao.inserir(clienteEmEdicao);
			else 
				dao.atualizar(clienteEmEdicao);
			atualizaListaClientesParaExibicao();
		}
	}
	
// getters e setters	
	
	public List<Cliente> getClientes(){		
		return clientes;
	}

	public Cliente getClienteEmEdicao() {
		return clienteEmEdicao;
	}

	public void setClienteEmEdicao(Cliente cliente) {
		this.clienteEmEdicao = cliente;
	}
}
