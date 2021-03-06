package com.c3po.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="AutenticacaoFilter", urlPatterns={"/pages/*","/fragments/*"})
public class AutenticacaoFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httprequest = (HttpServletRequest)request;
		HttpServletResponse httpresponse = (HttpServletResponse)response;
		
		//Recupera a sess�o atreves do objeto httprequest
		HttpSession session = httprequest.getSession();
		
		//O ID � adicionado na classe Autentica��oMB
		String cpfUsuario = (String) session.getAttribute("cpfUsuario");
		
		//Verifica se encontrou o ID do usu�irio na sess�o
		if(cpfUsuario != null && cpfUsuario != ""){
			//Continua o fluxo normal da aplica��o
			chain.doFilter(request, response);
		}else{
			//Redireciona para p�gina index.xhtml
			httpresponse.sendRedirect(httprequest.getContextPath()+"/index.jsf");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
