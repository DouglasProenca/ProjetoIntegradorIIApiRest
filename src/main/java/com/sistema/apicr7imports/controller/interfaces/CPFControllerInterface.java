package com.sistema.apicr7imports.controller.interfaces;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistema.apicr7imports.domain.response.CpfResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "CPF")
public interface CPFControllerInterface {

	@ApiOperation(value = "Verficar se o CPF é válido")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Valida o CPF."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro geral da Aplicação.")
		})
	public ResponseEntity<CpfResponse> verifyCPF(@ApiParam(value = "CPF", required = true) @PathVariable String cpf) throws SQLException;
}
