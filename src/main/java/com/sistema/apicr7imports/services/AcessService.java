package com.sistema.apicr7imports.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.data.dto.response.AcessResponse;
import com.sistema.apicr7imports.exception.InvalidJwtAuthenticationException;
import com.sistema.apicr7imports.security.jwt.JwtTokenProvider;

@Service
public class AcessService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;

	public AcessResponse getAcess(String username, String password) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			return new AcessResponse(authentication.getName(), tokenProvider.createToken(authentication.getName(), authentication.getAuthorities().stream().map(obj -> obj.getAuthority()).collect(Collectors.toList())));
		} catch (AuthenticationException e) {
			throw new InvalidJwtAuthenticationException("Usuário ou senha Inválidos!");
		}
	}

}
