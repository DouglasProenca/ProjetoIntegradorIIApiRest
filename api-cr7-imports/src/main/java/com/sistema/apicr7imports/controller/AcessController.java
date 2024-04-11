package com.sistema.apicr7imports.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.domain.User;
import com.sistema.apicr7imports.exception.InvalidJwtAuthenticationException;
import com.sistema.apicr7imports.security.jwt.JwtTokenProvider;
import com.sistema.apicr7imports.services.AcessService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Autenticação Da Aplicação")
@RestController
@RequestMapping(value = "/acesso")
public class AcessController {

	@Autowired
	AcessService service;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;

	@ApiOperation(value = "Autenticar usuario e retornar um token de acesso")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "OK - Retorna o nome e Usuário e Token de acesso."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - Usuário ou Seha errado, sem permissão para acesso."),
		})
	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<Object> login(
			@ApiParam(value = "Usuário de Cadastro", required = true) @FormParam("username") String username,
			@ApiParam(value = "Senha de Cadastro", required = true) @FormParam("password") String password) {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			User user = (User) service.loadUserByUsername(username);

			Map<String, String> model = new HashMap<String, String>();
			model.put("username", username);
			model.put("token", tokenProvider.createToken(username, user.getRoles()));
			return ResponseEntity.ok().body(model);
		} catch (AuthenticationException e) {
			throw new InvalidJwtAuthenticationException("Usuário ou senha Inválidos!");
		}
	}
}
