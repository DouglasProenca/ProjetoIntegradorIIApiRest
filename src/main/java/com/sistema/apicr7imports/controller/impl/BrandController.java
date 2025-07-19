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

import com.sistema.apicr7imports.controller.IBrandController;
import com.sistema.apicr7imports.data.dto.BrandDTO;
import com.sistema.apicr7imports.data.dto.request.BrandRequest;
import com.sistema.apicr7imports.services.BrandService;

@RestController
@RequestMapping(value = "/private/brand")
public class BrandController implements IBrandController {

	@Autowired
	BrandService service;
	
	@GetMapping(value = "/pagelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<BrandDTO>> findAllPage(@RequestParam(value = "page",defaultValue = "0") Integer page
			                                         ,@RequestParam(value = "limit",defaultValue = "10") Integer limit) {
		return ResponseEntity.ok().body(service.findAllPage(PageRequest.of(page, limit)));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BrandDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BrandDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findbyId(id));
	}
	
	@GetMapping(value = "/pagelist/searchbrand", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<BrandDTO>> findByBrandPage(@RequestParam(value = "brand") String brand
			                                             ,@RequestParam(value = "page",defaultValue = "0") Integer page
			                                             ,@RequestParam(value = "limit",defaultValue = "10") Integer limit) {
		return ResponseEntity.ok().body(service.findbyBrandPageable(brand,PageRequest.of(page, limit)));
	}

	@GetMapping(value = "/searchbrand", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BrandDTO>> findByBrand(@RequestParam String brand) {
		return ResponseEntity.ok().body(service.findbyBrand(brand));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BrandDTO> save(@RequestBody BrandRequest brandRequest) {
		BrandDTO brandCreate = service.save(brandRequest);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(brandCreate.getBrandId()).toUri()).body(brandCreate);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BrandDTO> update(@PathVariable Integer id, @RequestBody BrandRequest brandRequest) {
		return ResponseEntity.ok().body(service.update(id,brandRequest));
	}
	
	@GetMapping(value = "/excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getExcel () throws IOException{		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,ContentDisposition.attachment().filename("marcas.xlsx").build().toString()).body(service.getExcel());
	}
}
