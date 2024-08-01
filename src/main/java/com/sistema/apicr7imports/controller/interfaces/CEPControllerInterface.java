package com.sistema.apicr7imports.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistema.apicr7imports.domain.Dto.response.CEPResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CEP")
public interface CEPControllerInterface {
	
	@Operation(description = "Trazer informações do CEP")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Informações do CEP."),
		    @ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(responseCode = "500", description = "Erro Geral da Aplicação.")
		})
	public ResponseEntity<CEPResponse> findById(@Parameter(description = "CEP do Endereço.", required = true) @PathVariable String cep);
}
