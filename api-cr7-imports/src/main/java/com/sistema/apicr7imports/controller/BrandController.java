package com.sistema.apicr7imports.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistema.apicr7imports.domain.Brand;
import com.sistema.apicr7imports.domain.VO.BrandVO;
import com.sistema.apicr7imports.services.BrandService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jxl.write.WriteException;

@RestController
@Api(tags = "Tipos de Marcas")
@RequestMapping(value = "/private/brand")
public class BrandController {

	@Autowired
	private BrandService service;

	@ApiOperation(value = "Trazer todos os tipos de marcas cadastradas")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<BrandVO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@ApiOperation(value = "Trazer tipo de marca cadastrada por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Brand> findById(@ApiParam(value = "ID de Cadastro no Banco.", required = true) @PathVariable Long id) {
		return ResponseEntity.ok().body(service.findbyId(id));
	}

	@ApiOperation(value = "Trazer tipos de marcas cadastradas por nome")
	@RequestMapping(value = "/searchbrand", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<BrandVO>> findByCategoria(@RequestParam(value = "marca") String brand) {
		return ResponseEntity.ok().body(service.findbyBrand(brand));
	}

	@ApiOperation(value = "Deleta uma marca")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Insere uma marca")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Void> insert(@RequestBody Brand brand) {
		service.insert(brand);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(brand.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza uma Marca")
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Void> update(@RequestBody Brand brand) {
		service.update(brand);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Gera Excel das Marcas")
	@RequestMapping(value = "/excel",method = RequestMethod.GET, produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> downloadExcel () throws WriteException, IOException{		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("marca.xls").build());

		return ResponseEntity.ok().headers(headers).body(service.createExcel());
	}
}
