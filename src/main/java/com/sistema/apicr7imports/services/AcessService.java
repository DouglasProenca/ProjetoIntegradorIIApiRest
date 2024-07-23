package com.sistema.apicr7imports.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.domain.User;
import com.sistema.apicr7imports.domain.Dto.response.AcessResponse;
import com.sistema.apicr7imports.exception.InvalidJwtAuthenticationException;
import com.sistema.apicr7imports.security.jwt.JwtTokenProvider;

@Service
public class AcessService {
	
	@Autowired
	UserDetailsServiceImplemented service;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;
	
	public AcessResponse getAcess(String username,String password) {
		try {
		     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (AuthenticationException e) {
			throw new InvalidJwtAuthenticationException("Usuário ou senha Inválidos!");
		}

		User user = (User) service.loadUserByUsername(username);
		
		return new AcessResponse(username, tokenProvider.createToken(username, user.getRoles()));
	}

}
