package com.sistema.apicr7imports.controller.impl;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistema.apicr7imports.controller.IProductController;
import com.sistema.apicr7imports.data.dto.ProductDTO;
import com.sistema.apicr7imports.data.dto.request.ProductRequest;
import com.sistema.apicr7imports.services.IProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/product")
public class ProductController implements IProductController {

	private final IProductService service;
	
	@GetMapping(value = "/pagelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ProductDTO>> findAllPage(Integer page, Integer limit) {
		return ResponseEntity.ok().body(service.findAllPage(PageRequest.of(page, limit)));
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findbyId(id));
	}
	
	@GetMapping(value = "/pagelist/searchproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ProductDTO>> findByProductPage(String product,Integer page,Integer limit) {
		return ResponseEntity.ok().body(service.findbyProductPageable(product,PageRequest.of(page, limit)));
	}
	
	@GetMapping(value = "/searchproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findByProduct(String product) {
		return ResponseEntity.ok().body(service.findbyProduct(product));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> save(ProductRequest productRequest) {
		ProductDTO productCreate = service.save(productRequest);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(productCreate.getProductId()).toUri()).body(productCreate);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> update(Integer id, ProductRequest productRequest) {
		return ResponseEntity.ok().body(service.update(id,productRequest));
	}
	
	@GetMapping(value = "/excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getExcel () throws IOException{		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename("produtos.xlsx").build().toString()).body(service.getExcel());
	}

	@GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImage (@PathVariable Integer id) throws IOException{		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,ContentDisposition.attachment().filename("image.png").build().toString()).body(service.getImage(id).get(0));
	}
	
	@PostMapping(value = "/image/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> saveImage (Integer id, MultipartFile file) throws IOException{		
		return ResponseEntity.ok().header(ContentDisposition.attachment().filename("image.png").build().toString()).body(service.saveImage(id, file));
	}
}
