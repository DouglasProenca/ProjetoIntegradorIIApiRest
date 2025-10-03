package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistema.apicr7imports.data.dto.BrandDTO;
import com.sistema.apicr7imports.data.dto.request.BrandRequest;

public interface IBrandService {

	List<BrandDTO> findAll();

	BrandDTO findbyId(Integer id);

	List<BrandDTO> findbyBrand(String name);

	void delete(Integer id);

	BrandDTO save(BrandRequest brandRequest);

	BrandDTO update(Integer id, BrandRequest brandRequest);
	
	byte[] getExcel() throws IOException;
	
	Page<BrandDTO> findAllPage(Pageable pageable);
	
	Page<BrandDTO> findbyBrandPageable(String name,Pageable pageable);
}
