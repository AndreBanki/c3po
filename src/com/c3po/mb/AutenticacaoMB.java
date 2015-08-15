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
	private Boolean acessoCadastros;
	
	public String autenticaCliente() throws NoSuchAlgorithmException{
		//Retorna o contexto da aplicação
		FacesContext context = FacesContext.getCurrentInstance();
	
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = dao.buscaPorCpf(cpf);
			
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.setAttribute("cpfUsuario", cliente.getCpf());
		this.acessoCadastros = false;
		if(cliente.getId() != 0){
			session.setAttribute("idCliente", cliente.getId());
			return "/pages/pedido.jsf";
		}
		else {
			return "/pages/cadastro.jsf";
		}	
	}	
	
	public String autenticaFuncionario() throws NoSuchAlgorithmException{
		//Retorna o contexto da aplicação
		FacesContext context = FacesContext.getCurrentInstance();
	
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario usuario = dao.buscaPorCpf(cpf);
			
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.setAttribute("cpfUsuario", usuario.getCpf());

		if(usuario.getId() != 0){
			session.setAttribute("idFuncionario", usuario.getId());
			
			this.acessoCadastros = true;
			
			return "/pages/pedido.jsf";
		}
		else {
			FacesContext fcontext = FacesContext.getCurrentInstance();
			fcontext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"E-mail ou senha inválidos!", ""));
			return "";
		}	
	}	

	public String logout(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.invalidate();

		//Retorna para página de index através da navegação
		//configurada no faces-config.xml
		return "logout";
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Boolean getAcessoCadastros() {
		return acessoCadastros;
	}

}
