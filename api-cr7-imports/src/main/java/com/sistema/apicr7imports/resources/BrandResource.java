package com.sistema.apicr7imports.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistema.apicr7imports.domain.Brand;
import com.sistema.apicr7imports.resources.util.URL;
import com.sistema.apicr7imports.services.BrandService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Tipos de Marcas") 
@RequestMapping(value = "/private/brand")
public class BrandResource {
	
	@Autowired
	private BrandService service;

	@ApiOperation(value = "Trazer todos os tipos de marcas cadastradas")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Brand>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@ApiOperation(value = "Trazer tipo de marca cadastrada por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Brand> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findbyId(id));
	}
	
	@ApiOperation(value = "Trazer tipos de marcas cadastradas por nome")
	@RequestMapping(value = "/searchbrand", method = RequestMethod.GET)
	public ResponseEntity<List<Brand>> findByCategoria(@RequestParam(value= "marca") String brand) {
		return ResponseEntity.ok().body(service.findbyBrand(URL.decodeParam(brand)));
	}
	
	@ApiOperation(value = "Deleta uma marca")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Insere uma marca")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Brand brand) {
		service.insert(brand);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(brand.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Atualiza uma Marca")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Brand brand) {
		service.update(brand);
		return ResponseEntity.noContent().build();
	}
}
