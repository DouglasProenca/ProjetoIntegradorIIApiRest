package com.sistema.apicr7imports.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.services.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Tipos de Roupas") 
@RequestMapping(value = "/private/category")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;

	@ApiOperation(value = "Trazer todos os tipos de roupas cadastrados")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Trazer tipos de roupas cadastrado por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> findById(@PathVariable String id) {
		Category country = service.findbyId(id);
		return ResponseEntity.ok().body(country);
	}
}
