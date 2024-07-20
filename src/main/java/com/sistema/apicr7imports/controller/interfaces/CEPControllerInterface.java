package com.sistema.apicr7imports.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistema.apicr7imports.domain.CEP;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "CEP")
public interface CEPControllerInterface {
	
	@ApiOperation(value = "Trazer informações do CEP")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Informações do CEP."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro Geral da Aplicação.")
		})
	public ResponseEntity<CEP> findById(@ApiParam(value = "CEP do Endereço.", required = true) @PathVariable String cep);
}
