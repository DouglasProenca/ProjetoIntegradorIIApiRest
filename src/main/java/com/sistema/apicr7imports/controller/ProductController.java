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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistema.apicr7imports.domain.Product;
import com.sistema.apicr7imports.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@Api(tags = "Produtos")
@RequestMapping("/private/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@ApiOperation(value = "Trazer todos os produtos cadastrados")
	@GetMapping( produces = "application/json")
	public ResponseEntity<List<Product>> findAll() {
		return ResponseEntity.ok().body(productService.findAll());
	}
	
	@ApiOperation(value = "Trazer produto por id")
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Product> findById(@ApiParam(value = "ID de Cadastro no Banco", required = true, example = "1") @PathVariable Integer id) {
		return ResponseEntity.ok().body(productService.findbyId(id));
	}
	
	@ApiOperation(value = "Trazer tipos de produtos cadastrados por nome")
	@GetMapping(value = "/searchproduct", produces = "application/json")
	public ResponseEntity<List<Product>> findByCategoria(@RequestParam(value = "product") String product) {
		return ResponseEntity.ok().body(productService.findbyBrand(product));
	}

	@ApiOperation(value = "Deleta um produto")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@ApiParam(value = "ID de Cadastro no Banco", required = true, example = "1") @PathVariable Integer id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Insere um produto")
	@PostMapping( produces = "application/json")
	public ResponseEntity<Void> insert(@RequestBody Product product) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(productService.insert(product).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza um produto")
	@PutMapping( produces = "application/json")
	public ResponseEntity<Void> update(@RequestBody Product product) {
		productService.update(product);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Gera Excel dos produtos")
	@GetMapping(value = "/excel", produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> downloadExcel () throws IOException{		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("produtos.xlsx").build());

		return ResponseEntity.ok().headers(headers).body(productService.createExcel());
	}
	
	@ApiOperation(value = "Todos os produtos cadastrados de forma paginada")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Todos os produtos cadastrados."),
		    @ApiResponse(code = 403, message = "FORBIDDEN - sem permissão para acesso."),
		    @ApiResponse(code = 500, message = "Erro de Servidor interno")
		})
	@GetMapping(value = "/pagelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Product>> findAllPage(@RequestParam(value = "page",defaultValue = "0") int page
			                                        ,@RequestParam(value = "limit",defaultValue = "10") int limit) {
		return ResponseEntity.ok().body(productService.findAllPage(PageRequest.of(page, limit)));
	}
}
