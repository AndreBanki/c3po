package com.c3po.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.c3po.dao.ClienteDAO;
import com.c3po.entidade.Cliente;

@ManagedBean
@ViewScoped
public class CadastroMB {

	private String nome;
	private String endereco;
	private String telefone;
	private String cpf;
	
    @ManagedProperty(value="#{autenticacaoMB}")
    private AutenticacaoMB autenticacaoMB;	
	
	@PostConstruct
	public void init() {
		this.cpf = autenticacaoMB.getCpf();
	}
	
	public String cadastrarUsuario() {
		Cliente cliente = new Cliente();
		cliente.setCpf(this.cpf);
		cliente.setNome(this.nome);
		cliente.setEndereco(this.endereco);
		cliente.setTelefone(this.telefone);
		
		ClienteDAO dao = new ClienteDAO();
		dao.salvar(cliente);
		
		autenticacaoMB.setSelfService(true);
		return "pedido";
	}
	
	public void setAutenticacaoMB(AutenticacaoMB autenticacaoMB) {
		this.autenticacaoMB = autenticacaoMB;
	}	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
