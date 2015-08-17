package com.c3po.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.c3po.entidade.Produto;

import java.util.Iterator;
import java.util.List;
 
public class ProdutoValidator implements Validator {
	
     @Override
     public void validate(FacesContext arg0, UIComponent arg1, Object valorTela) throws ValidatorException {
 		List<Produto> produtos = (List<Produto>) arg1.getAttributes().get("listaProduto");
 		
 		String valorAnterior = (String) arg1.getAttributes().get("valorAtual");
 		Produto produtoEmEdicao = getAsObject(produtos, String.valueOf(valorAnterior));
 		
 		Produto produtoMesmoNome = produtoMesmoNome(produtos, String.valueOf(valorTela));

 		if (produtoMesmoNome.getId() != produtoEmEdicao.getId()) {
           FacesMessage message = new FacesMessage();
           message.setSeverity(FacesMessage.SEVERITY_ERROR);
           message.setSummary("Já existe um produto com este nome");
           throw new ValidatorException(message);
		}   
     }
     
	private Produto getAsObject(List<Produto> produtos, String arg2) {
		for (Iterator<Produto> iterator = produtos.iterator(); iterator.hasNext(); ) {    
			Produto f = (Produto) iterator.next();    
			if (f.getDescricao().equals(arg2))
				return f;
		} 
		return null;
	}
		
	private Produto produtoMesmoNome(List<Produto> produtos, String nome) {
		Produto prodIgual = new Produto();
		for (Iterator<Produto> iterator = produtos.iterator(); iterator.hasNext() && prodIgual.getId() == null; ) {    
			Produto prod = (Produto) iterator.next();    
			if (prod.getDescricao().equals(nome))
				prodIgual = prod;
		}
		return prodIgual;
	}	
}
