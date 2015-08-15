package com.c3po.converter;

import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.c3po.entidade.Produto;

@FacesConverter(value="produtoConverter")
public class ProdutoConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		List<Produto> produtos = (List<Produto>) arg1.getAttributes().get("listaProduto");
		for (Iterator<Produto> iterator = produtos.iterator(); iterator.hasNext(); ) {    
			Produto f = (Produto) iterator.next();    
			if (f.getDescricao().equals(arg2))
				return f;
		} 
		return null;
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Produto produto = (Produto)arg2;
		return produto.getDescricao();
	}
}