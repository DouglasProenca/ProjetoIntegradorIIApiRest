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
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.controller.interfaces.CountryControllerInterface;
import com.sistema.apicr7imports.data.model.Country;
import com.sistema.apicr7imports.services.CountryService;

@RestController
@RequestMapping(value = "/private/country")
public class CountryController implements CountryControllerInterface {

	@Autowired
	CountryService service;
	
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Country>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findbyId(id));
	}
	
	@GetMapping(value = "/pagelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagedModel<EntityModel<Country>>> findAllPage(Integer page,Integer limit,String direction) {
		return ResponseEntity.ok().body(service.findAllPage(PageRequest.of(page, limit,Sort.by(direction.equals("asc")?Direction.ASC:Direction.DESC, "namePort"))));
	}
}
