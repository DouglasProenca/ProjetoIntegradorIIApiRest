package com.sistema.apicr7imports.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistema.apicr7imports.data.dto.response.CpfResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CPF")
public interface ICPFController {

	@Operation(description = "Verficar se o CPF é válido")
    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Valida o CPF."),
			@ApiResponse(responseCode = "403", description = "FORBIDDEN - sem permissão para acesso."),
			@ApiResponse(responseCode = "500", description = "Erro geral da Aplicação.") 
	    })
	ResponseEntity<CpfResponse> verifyCPF(@Parameter(description = "CPF", required = true) @PathVariable String cpf);
}
