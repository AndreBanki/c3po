package com.c3po.mb;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.c3po.dao.FuncionarioDAO;
import com.c3po.entidade.Funcionario;

@ManagedBean
@ViewScoped
public class FuncionarioMB {

	private Funcionario funcionarioEmEdicao;
	private List<Funcionario> funcionarios;
	private FuncionarioDAO dao;

	@PostConstruct
	public void init() {
		dao = new FuncionarioDAO();
		atualizaListaFuncionariosParaExibicao();
	}
	
// métodos auxiliares
	
	public void atualizaListaFuncionariosParaExibicao() {
		funcionarios = dao.listarTodos();
		limpaFuncionarioEmEdicao();
	}
	
	public void limpaFuncionarioEmEdicao() {
		funcionarioEmEdicao = new Funcionario();
	}
	
	private Funcionario funcionarioMesmoCpf(Funcionario funcionario) {
		Funcionario funcIgual = new Funcionario();
		for (Iterator<Funcionario> iterator = funcionarios.iterator(); iterator.hasNext() && funcIgual.getId() == 0; ) {    
			Funcionario func = (Funcionario) iterator.next();    
			if (func.getCpf().equals(funcionario.getCpf()))
				funcIgual = func;
		}
		return funcIgual;
	}	
	
// métodos para acesso ao BD	
	
	public void apagarFuncionario() {
		dao.apagar(funcionarioEmEdicao);
		atualizaListaFuncionariosParaExibicao();
	}
	
	public void inserirFuncionario() {
		Funcionario funcionarioMesmoCPF = funcionarioMesmoCpf(funcionarioEmEdicao);
		if (funcionarioMesmoCPF.getId() != funcionarioEmEdicao.getId()) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.addCallbackParam("jaExisteNome", true);
		}
		else {
			if (funcionarioEmEdicao.getId() == 0)
				dao.inserir(funcionarioEmEdicao);
			else 
				dao.atualizar(funcionarioEmEdicao);
			atualizaListaFuncionariosParaExibicao();
		}
	}
	
// getters e setters	
	
	public List<Funcionario> getFuncionarios(){		
		return funcionarios;
	}

	public Funcionario getFuncionarioEmEdicao() {
		return funcionarioEmEdicao;
	}

	public void setFuncionarioEmEdicao(Funcionario funcionario) {
		this.funcionarioEmEdicao = funcionario;
	}
}
