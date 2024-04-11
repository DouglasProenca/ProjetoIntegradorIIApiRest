package com.sistema.apicr7imports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sistema.apicr7imports.domain.Country;
import com.sistema.apicr7imports.services.CountryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "Paises")
@RequestMapping(value = "/private/country")
public class CountryController {

	@Autowired
	private CountryService service;

	@ApiOperation(value = "Todos os paises cadastrados")
	@GetMapping( produces = "application/json")
	public ResponseEntity<List<Country>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@ApiOperation(value = "Pais cadastrado por id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Pais cadastrado por id."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Pais não encontrado."),
		})
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Country> findById(@ApiParam(value = "ID de Cadastro no Banco.", required = true) @PathVariable long id) {
		return ResponseEntity.ok().body(service.findbyId(id));
	}
}
