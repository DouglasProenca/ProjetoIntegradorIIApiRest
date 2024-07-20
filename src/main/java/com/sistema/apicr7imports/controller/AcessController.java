package com.sistema.apicr7imports.controller;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.controller.interfaces.AcessControllerInterface;
import com.sistema.apicr7imports.domain.User;
import com.sistema.apicr7imports.domain.request.AcessRequest;
import com.sistema.apicr7imports.domain.response.AcessResponse;
import com.sistema.apicr7imports.exception.InvalidJwtAuthenticationException;
import com.sistema.apicr7imports.security.jwt.JwtTokenProvider;
import com.sistema.apicr7imports.services.AcessService;

@RestController
@RequestMapping(value = "/acesso")
public class AcessController implements AcessControllerInterface {

	@Autowired
	AcessService service;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AcessResponse> login(@FormParam("username") String username,
			                                   @FormParam("password") String password) {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			User user = (User) service.loadUserByUsername(username);

			return ResponseEntity.ok().body(new AcessResponse(username, tokenProvider.createToken(username, user.getRoles())));
		} catch (AuthenticationException e) {
			throw new InvalidJwtAuthenticationException("Usuário ou senha Inválidos!");
		}
	}
	
	@PostMapping(value = "/login/byrequestBody", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AcessResponse> login(@RequestBody AcessRequest acessRequest) {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(acessRequest.getUsername(), acessRequest.getPassword()));

			User user = (User) service.loadUserByUsername(acessRequest.getUsername());

			return ResponseEntity.ok().body(new AcessResponse(acessRequest.getUsername(), tokenProvider.createToken(acessRequest.getUsername(), user.getRoles())));
		} catch (AuthenticationException e) {
			throw new InvalidJwtAuthenticationException("Usuário ou senha Inválidos!");
		}
	}
}
