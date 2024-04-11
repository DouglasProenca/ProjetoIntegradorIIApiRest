package com.sistema.apicr7imports.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.services.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jxl.write.WriteException;

@RestController
@Api(tags = "Tipos de Roupas") 
@RequestMapping(value = "/private/category")
public class CategoryController {
	
	@Autowired
	private CategoryService service;

	@ApiOperation(value = "Trazer todos os tipos de roupas cadastrados")
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@ApiOperation(value = "Trazer tipos de roupas cadastrado por id")
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findbyId(id));
	}
	
	@ApiOperation(value = "Trazer tipos de roupas cadastrado por Categoria")
	@GetMapping("/searchcategory")
	public ResponseEntity<List<Category>> findByCategoria(@RequestParam(value= "categoria") String categoria) {
		return ResponseEntity.ok().body(service.findbyCategory(categoria));
	}
	
	@ApiOperation(value = "Deleta uma Categoria")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Insere uma Categoria")
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Category category) {
		service.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Atualiza uma Categoria")
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody Category category) {
		service.update(category);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Gera Excel das Marcas")
	@GetMapping(value = "/excel", produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> downloadExcel () throws WriteException, IOException{		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("categoria.xls").build());

		return ResponseEntity.ok().headers(headers).body(service.createExcel());
	}
}
