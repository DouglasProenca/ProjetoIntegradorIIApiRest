package com.sistema.apicr7imports.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.domain.Country;
import com.sistema.apicr7imports.services.CountryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Paises")
@RequestMapping(value = "/private/country")
public class CountryResource {

	@Autowired
	private CountryService service;

	@ApiOperation(value = "Todos os paises cadastrados")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Country>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@ApiOperation(value = "Pais cadastrado por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Country> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(service.findbyId(id));
	}
}
