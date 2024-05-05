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

import com.sistema.apicr7imports.domain.Brand;
import com.sistema.apicr7imports.domain.VO.BrandVO;
import com.sistema.apicr7imports.services.BrandService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "Tipos de Marcas")
@RequestMapping(value = "/private/brand")
public class BrandController {

	@Autowired
	private BrandService service;

	@ApiOperation(value = "Trazer todos os tipos de marcas cadastradas")
	@GetMapping( produces = "application/json")
	public ResponseEntity<List<BrandVO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@ApiOperation(value = "Trazer tipo de marca cadastrada por id")
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Brand> findById(@ApiParam(value = "ID de Cadastro no Banco", required = true, example = "1") @PathVariable Long id) {
		return ResponseEntity.ok().body(service.findbyId(id));
	}

	@ApiOperation(value = "Trazer tipos de marcas cadastradas por nome")
	@GetMapping(value = "/searchbrand", produces = "application/json")
	public ResponseEntity<List<BrandVO>> findByCategoria(@RequestParam(value = "marca") String brand) {
		return ResponseEntity.ok().body(service.findbyBrand(brand));
	}

	@ApiOperation(value = "Deleta uma marca")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@ApiParam(value = "ID de Cadastro no Banco", required = true, example = "1") @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Insere uma marca")
	@PostMapping( produces = "application/json")
	public ResponseEntity<Void> insert(@RequestBody Brand brand) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(service.insert(brand).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza uma Marca")
	@PutMapping( produces = "application/json")
	public ResponseEntity<Void> update(@RequestBody Brand brand) {
		service.update(brand);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Gera Excel das Marcas")
	@GetMapping(value = "/excel", produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> downloadExcel () throws IOException{		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("marcas.xlsx").build());

		return ResponseEntity.ok().headers(headers).body(service.createExcel());
	}
}
