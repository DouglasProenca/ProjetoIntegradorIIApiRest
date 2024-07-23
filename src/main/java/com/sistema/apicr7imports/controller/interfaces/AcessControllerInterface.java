package com.sistema.apicr7imports.controller.interfaces;

import javax.ws.rs.FormParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.sistema.apicr7imports.domain.Dto.request.AcessRequest;
import com.sistema.apicr7imports.domain.Dto.response.AcessResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Autenticação Da Aplicação")
public interface AcessControllerInterface {

	@ApiOperation(value = "Autenticar usuário e retornar um token de acesso")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK - Retorna o nome e Usuário e Token de acesso."),
			@ApiResponse(code = 403, message = "FORBIDDEN - Usuário ou Seha errado, sem permissão para acesso."),
			@ApiResponse(code = 500, message = "Erro geral da Aplicação.")
			})
	public ResponseEntity<AcessResponse> login(@ApiParam(value = "Usuário de Cadastro", required = true) @FormParam("username") String username,
			                                   @ApiParam(value = "Senha de Cadastro", required = true) @FormParam("password") String password);
	
	@ApiOperation(value = "Autenticar usuário e retornar um token de acesso por request Body")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK - Retorna o nome e Usuário e Token de acesso."),
			@ApiResponse(code = 403, message = "FORBIDDEN - Usuário ou Seha errado, sem permissão para acesso."),
			@ApiResponse(code = 500, message = "Erro geral da Aplicação.")
			})
	public ResponseEntity<AcessResponse> login(@RequestBody AcessRequest acessRequest);
	
}
