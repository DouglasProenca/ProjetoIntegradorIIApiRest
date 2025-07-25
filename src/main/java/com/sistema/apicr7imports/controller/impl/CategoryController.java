package com.sistema.apicr7imports.controller.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistema.apicr7imports.controller.ICategoryController;
import com.sistema.apicr7imports.data.dto.CategoryDTO;
import com.sistema.apicr7imports.data.dto.request.CategoryRequest;
import com.sistema.apicr7imports.services.CategoryService;

@RestController
@RequestMapping(value = "/private/category")
public class CategoryController implements ICategoryController {
	
	@Autowired
	CategoryService service;
	
	@GetMapping(value = "/pagelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<CategoryDTO>> findAllPage(@RequestParam(value = "page",defaultValue = "0") Integer page
			                                            ,@RequestParam(value = "limit",defaultValue = "10") Integer limit) {
		return ResponseEntity.ok().body(service.findAllPage(PageRequest.of(page, limit)));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value = "/excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getExcel () throws IOException{		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename("categorias.xlsx").build().toString()).body(service.getExcel());
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findbyId(id));
	}
	
	@GetMapping(value = "/pagelist/searchcategory", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<CategoryDTO>> findByCategoryPage(@RequestParam(value = "category") String category
			                                                   ,@RequestParam(value = "page",defaultValue = "0") Integer page
			                                                   ,@RequestParam(value = "limit",defaultValue = "10") Integer limit) {
		return ResponseEntity.ok().body(service.findbyBrandPageable(category,PageRequest.of(page, limit)));
	}
	
	@GetMapping(value = "/searchcategory",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDTO>> findByCategoria(@RequestParam(value = "categoria") String categoria) {
		return ResponseEntity.ok().body(service.findbyCategory(categoria));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> save(@RequestBody CategoryRequest categoryRequest) {
		CategoryDTO categoryCreate = service.save(categoryRequest);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(categoryCreate.getCategoryId()).toUri()).body(categoryCreate);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> update(@PathVariable Integer id, @RequestBody CategoryRequest categoryRequest) {
		return ResponseEntity.ok().body(service.update(id,categoryRequest));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
