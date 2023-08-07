package com.sistema.apicr7imports.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sistema.apicr7imports.services.AcessService;
import com.sistema.apicr7imports.services.exception.ObjectNotFoundException;

@Component
@Order(1)
public class TransactionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (!((HttpServletRequest) request).getRequestURI().equals("/apicr7imports/acesso/login")) {
			AcessService autenticator = AcessService.getInstance();
			HttpServletRequest req = (HttpServletRequest) request;
			System.out.println(((HttpServletRequest) request).getRequestURI());

			if (req.getHeader("key") == null || req.getHeader("key").equals("")) {
				throw new ObjectNotFoundException("Falta de Token no header");
			}

			if (!autenticator.isTokenValid(req.getHeader("key"))) {
				throw new ObjectNotFoundException("Token Errado");
			}
		}
		chain.doFilter(request, response);
	}
}
