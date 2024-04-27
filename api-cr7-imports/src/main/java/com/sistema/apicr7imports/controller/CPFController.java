package com.sistema.apicr7imports.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.apicr7imports.domain.response.CpfResponse;
import com.sistema.apicr7imports.services.CPFService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Api(tags = "CPF")
@RequestMapping(value = "/private/cpf")
public class CPFController {

	@Autowired
	CPFService cpfService;

	@ApiOperation(value = "Verficar se o CPF é válido")
	@GetMapping(value = "/{cpf}", produces = "application/json")
	public ResponseEntity<CpfResponse> verifyCPF(@ApiParam(value = "CPF", required = true) @PathVariable String cpf) throws SQLException {
		return ResponseEntity.ok().body(cpfService.verifyCPF(cpf));
	}
}
