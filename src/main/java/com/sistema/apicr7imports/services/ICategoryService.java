package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistema.apicr7imports.data.dto.CategoryDTO;
import com.sistema.apicr7imports.data.dto.request.CategoryRequest;

public interface ICategoryService {

	List<CategoryDTO> findAll();

	CategoryDTO findbyId(Integer id);

	List<CategoryDTO> findbyCategory(String name);

	void delete(Integer id);

	CategoryDTO save(CategoryRequest categoryRequest);

	CategoryDTO update(Integer id, CategoryRequest categoryRequest);
	
	byte[] getExcel() throws IOException;
	
	Page<CategoryDTO> findAllPage(Pageable pageable);
	
	Page<CategoryDTO> findbyBrandPageable(String name,Pageable pageable);
}
