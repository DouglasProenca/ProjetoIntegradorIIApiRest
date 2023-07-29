package com.sistema.apicr7imports.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sistema.apicr7imports.domain.Autenticator;
import com.sistema.apicr7imports.domain.Headers;
import com.sistema.apicr7imports.services.exception.ObjectNotFoundException;

@Component
@Order(1)
public class TransactionFilter implements Filter {

	// private final static Logger LOG =
	// Logger.getLogger(RequestFilter.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (!((HttpServletRequest) request).getRequestURI().equals("/acesso/login")) {
			Autenticator autenticator = Autenticator.getInstance();
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
