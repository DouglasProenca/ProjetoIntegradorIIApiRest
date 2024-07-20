package com.sistema.apicr7imports.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BrandVO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Brand> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findbyId(id));
	}

	@GetMapping(value = "/searchbrand", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BrandVO>> findByBrand(@RequestParam(value = "marca") String brand) {
		return ResponseEntity.ok().body(service.findbyBrand(brand));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		return service.delete(id);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> insert(@RequestBody Brand brand) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(service.insert(brand).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@RequestBody Brand brand) {
		return service.update(brand);
	}
	
	@GetMapping(value = "/excel", produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getExcel () throws IOException{		
		return service.getExcel();
	}
}
