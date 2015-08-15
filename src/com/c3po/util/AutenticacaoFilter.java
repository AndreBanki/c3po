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
		
		//Recupera a sessão atreves do objeto httprequest
		HttpSession session = httprequest.getSession();
		
		//O ID é adicionado na classe AutenticaçãoMB
		Integer idUsuario = (Integer) session.getAttribute("idCliente");
		
		//Verifica se encontrou o ID do usuáirio na sessão
		if(idUsuario != null && idUsuario > 0){
			//Continua o fluxo normal da aplicação
			chain.doFilter(request, response);
		}else{
			//Redireciona para página index.xhtml
			httpresponse.sendRedirect(httprequest.getContextPath()+"/index.jsf");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
