package com.sistema.apicr7imports.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {
	
	@Autowired
	JwtTokenProvider tokenProvider;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String token = tokenProvider.resolveToken((HttpServletRequest) request);
		
		if (token != null && tokenProvider.validateToken(token)) {
			Authentication auth = tokenProvider.getAuthentication(token);
			if (auth != null) 
				SecurityContextHolder.getContext().setAuthentication(auth);
			
		}
		chain.doFilter(request, response);
	}

}