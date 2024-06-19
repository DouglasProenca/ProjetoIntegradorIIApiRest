package com.sistema.apicr7imports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Todos os paises cadastrados."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso.")
		})
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Country>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@ApiOperation(value = "Pais cadastrado por id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Pais cadastrado por id."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 404, message = "Pais não encontrado."),
		})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> findById(@ApiParam(value = "ID de Cadastro no Banco.", required = true,example = "1") @PathVariable long id) {
		return ResponseEntity.ok().body(service.findbyId(id));
	}
	
	@ApiOperation(value = "Todos os paises cadastrados de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Todos os paises cadastrados."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso.")
		})
	@GetMapping(value = "/pagelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagedModel<EntityModel<Country>>> findAllPage(@RequestParam(value = "page",defaultValue = "0") int page
			                                        ,@RequestParam(value = "limit",defaultValue = "10") int limit
			                                        ,@RequestParam(value = "direction",defaultValue = "asc") String direction) {
		return ResponseEntity.ok().body(service.findAllPage(PageRequest.of(page, limit,Sort.by(direction.equals("asc")?Direction.ASC:Direction.DESC, "namePort"))));
	}
}
