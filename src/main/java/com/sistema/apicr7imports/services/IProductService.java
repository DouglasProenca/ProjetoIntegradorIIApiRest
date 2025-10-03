package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.sistema.apicr7imports.data.dto.ProductDTO;
import com.sistema.apicr7imports.data.dto.request.ProductRequest;


public interface IProductService {

	List<ProductDTO> findAll();
	
	ProductDTO findbyId(Integer id);

	List<ProductDTO> findbyProduct(String name);

	void delete(Integer id);

	ProductDTO save(ProductRequest productRequest);

	ProductDTO update(Integer id, ProductRequest productRequest);
	
	byte[] getExcel() throws IOException;
	
	List<byte[]> getImage(Integer id) throws IOException;
	
	byte[] saveImage(Integer id, MultipartFile file) throws IOException;
	
	Page<ProductDTO> findAllPage(Pageable pageable);
	
	Page<ProductDTO> findbyProductPageable(String name,Pageable pageable);
}
