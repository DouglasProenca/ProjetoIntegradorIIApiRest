package com.sistema.apicr7imports.controller;

import java.io.IOException;
import java.net.URI;
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

import com.sistema.apicr7imports.controller.interfaces.BrandControllerInterface;
import com.sistema.apicr7imports.domain.Brand;
import com.sistema.apicr7imports.domain.VO.BrandVO;
import com.sistema.apicr7imports.services.BrandService;

@RestController
@RequestMapping(value = "/private/brand")
public class BrandController implements BrandControllerInterface {

	@Autowired
	private BrandService service;
	
	@GetMapping(value = "/pagelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Brand>> findAllPage(@RequestParam(value = "page",defaultValue = "0") Integer page
			                                        ,@RequestParam(value = "limit",defaultValue = "10") Integer limit) {
		return ResponseEntity.ok().body(service.findAllPage(PageRequest.of(page, limit)));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BrandVO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Brand> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findbyId(id));
	}
	
	@GetMapping(value = "/pagelist/searchbrand", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Brand>> findByBrandPage(@RequestParam(value = "brand") String brand
			                                              ,@RequestParam(value = "page",defaultValue = "0") Integer page
			                                              ,@RequestParam(value = "limit",defaultValue = "10") Integer limit) {
		return ResponseEntity.ok().body(service.findbyBrandPageable(brand,PageRequest.of(page, limit)));
	}

	@GetMapping(value = "/searchbrand", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BrandVO>> findByBrand(@RequestParam(value = "marca") String brand) {
		return ResponseEntity.ok().body(service.findbyBrand(brand));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Brand> insert(@RequestBody Brand brand) {
		Brand brandCreate = service.insert(brand);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(brandCreate.getId()).toUri();
		return ResponseEntity.created(uri).body(brandCreate);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Brand> update(@RequestBody Brand brand) {
		return ResponseEntity.ok().body(service.update(brand));
	}
	
	@GetMapping(value = "/excel", produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getExcel () throws IOException{		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("marcas.xlsx").build());
		return ResponseEntity.ok().headers(headers).body(service.getExcel());
	}
}
