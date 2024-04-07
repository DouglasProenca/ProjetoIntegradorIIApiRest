package com.sistema.apicr7imports.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sistema.apicr7imports.services.CPFService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Api(tags = "CPF")
@RequestMapping(value = "/private/cpf")
public class CPFResource {

	@Autowired
	CPFService cpfService;

	@ApiOperation(value = "Verficar se o CPF é válido")
	@RequestMapping(value = "/{cpf}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> verifyCPF(@ApiParam(value = "CPF.", required = true) @PathVariable String cpf) throws SQLException {
		Map<String, Boolean> model = new HashMap<String, Boolean>();
		model.put("Valido", cpfService.verifyCPF(cpf));
		return ResponseEntity.ok().body(model);
	}
}
