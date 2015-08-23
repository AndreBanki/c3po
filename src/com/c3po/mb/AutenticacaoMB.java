package com.c3po.mb;

import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.c3po.dao.ClienteDAO;
import com.c3po.dao.FuncionarioDAO;
import com.c3po.entidade.Cliente;
import com.c3po.entidade.Funcionario;

@ManagedBean
@SessionScoped
public class AutenticacaoMB {

	private String cpf;
	private String senha;
	private Boolean acessoCadastros = false;
	private Boolean selfService = false;
	
	public String autenticaCliente() throws NoSuchAlgorithmException{
		//Retorna o contexto da aplicação
		FacesContext context = FacesContext.getCurrentInstance();
	
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = dao.buscaPorCpf(cpf);
			
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.setAttribute("cpfUsuario", cpf);
		
		this.acessoCadastros = false;
		
		if(cliente != null && cliente.getId() != 0){
			this.selfService = true;
			return "pedido";
		}
		else {
			this.selfService = false;
			return "/pages/cadastro.jsf";
		}	
	}	
	
	public String autenticaFuncionario() throws NoSuchAlgorithmException{
		//Retorna o contexto da aplicação
		FacesContext context = FacesContext.getCurrentInstance();
	
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario usuario = dao.buscaPorCpf(cpf);
			
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.setAttribute("cpfUsuario", cpf);

		if(usuario != null && usuario.getId() != 0 && this.senha.equals(usuario.getSenha())){
			this.acessoCadastros = true;
			this.selfService = false;
			
			return "/pages/produto.jsf";
		}
		else {
			FacesContext fcontext = FacesContext.getCurrentInstance();
			fcontext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Login inválido!", ""));
			return "";
		}	
	}	

	public String logout(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.invalidate();
		
		this.cpf = "";
		this.senha = "";
		this.acessoCadastros = false;
		this.selfService = false;

		//Retorna para página de index através da navegação
		//configurada no faces-config.xml
		return "logout";
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Boolean getAcessoCadastros() {
		return this.acessoCadastros;
	}

	public Boolean getSelfService() {
		return this.selfService;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
