package com.sistema.apicr7imports.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.sistema.apicr7imports.data.dto.request.AcessRequest;
import com.sistema.apicr7imports.data.dto.response.AcessResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autenticação")
public interface IAcessController {

	@Operation(description = "Autenticar usuário e retornar um token de acesso")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK - Retorna o nome e Usuário e Token de acesso."),
			@ApiResponse(responseCode = "403", description = "FORBIDDEN - Usuário ou Seha errado, sem permissão para acesso."),
			@ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
			})
	ResponseEntity<AcessResponse> login(@Parameter(description = "Usuário de Cadastro", required = true) @NotNull @FormParam("username") String username,
			                            @Parameter(description = "Senha de Cadastro", required = true) @NotNull @FormParam("password") String password);
	
	@Operation(description = "Autenticar usuário e retornar um token de acesso por request Body")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK - Retorna o nome e Usuário e Token de acesso."),
			@ApiResponse(responseCode = "403", description = "FORBIDDEN - Usuário ou Seha errado, sem permissão para acesso."),
			@ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.")
			})
	ResponseEntity<AcessResponse> login(@RequestBody @Valid AcessRequest acessRequest);
	
}
