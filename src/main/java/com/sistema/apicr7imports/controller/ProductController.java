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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistema.apicr7imports.controller.interfaces.ProductControllerInterface;
import com.sistema.apicr7imports.domain.Product;
import com.sistema.apicr7imports.services.ProductService;

@RestController
@RequestMapping("/private/product")
public class ProductController implements ProductControllerInterface {

	@Autowired
	ProductService productService;
	
	@GetMapping(value = "/pagelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Product>> findAllPage(@RequestParam(value = "page",defaultValue = "0") Integer page
			                                        ,@RequestParam(value = "limit",defaultValue = "10") Integer limit) {
		return ResponseEntity.ok().body(productService.findAllPage(PageRequest.of(page, limit)));
	}
	
	@GetMapping(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> findAll() {
		return ResponseEntity.ok().body(productService.findAll());
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(productService.findbyId(id));
	}
	
	@GetMapping(value = "/pagelist/searchproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Product>> findByProductPage(@RequestParam(value = "product") String product
			                                              ,@RequestParam(value = "page",defaultValue = "0") Integer page
			                                              ,@RequestParam(value = "limit",defaultValue = "10") Integer limit) {
		return ResponseEntity.ok().body(productService.findbyProductPageable(product,PageRequest.of(page, limit)));
	}
	
	@GetMapping(value = "/searchproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> findByProduct(@RequestParam(value = "product") String product) {
		return ResponseEntity.ok().body(productService.findbyProduct(product));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> insert(@RequestBody Product product) {
		Product productCreate = productService.insert(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(productCreate.getId()).toUri();
		return ResponseEntity.created(uri).body(productCreate);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> update(@RequestBody Product product) {
		return ResponseEntity.ok().body(productService.update(product));
	}
	
	@GetMapping(value = "/excel", produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getExcel () throws IOException{		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("produtos.xlsx").build());
		return ResponseEntity.ok().headers(headers).body(productService.createExcel());
	}

}
