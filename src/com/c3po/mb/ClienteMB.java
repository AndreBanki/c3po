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
	
// m�todos auxiliares
	
	public void atualizaListaClientesParaExibicao() {
		clientes = dao.listarTodos();
		limpaClienteEmEdicao();
	}
	
	public void limpaClienteEmEdicao() {
		clienteEmEdicao = new Cliente();
	}
	
	private Cliente clienteMesmoCpf(Cliente cliente) {
		Cliente cliIgual = new Cliente();
		for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext() && cliIgual.getId() == null; ) {    
			Cliente cli = (Cliente) iterator.next();    
			if (cli.getCpf().equals(cliente.getCpf()))
				cliIgual = cli;
		}
		return cliIgual;
	}	
	
// m�todos para acesso ao BD	
	
	public void apagarCliente() {
		dao.apagar(clienteEmEdicao);
		atualizaListaClientesParaExibicao();
	}
	
	public void inserirCliente() {
		Cliente clienteMesmoCPF = clienteMesmoCpf(clienteEmEdicao);
		if (clienteMesmoCPF.getId() != clienteEmEdicao.getId()) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.addCallbackParam("jaExisteNome", true);
		}
		else {
			dao.salvar(clienteEmEdicao);
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
