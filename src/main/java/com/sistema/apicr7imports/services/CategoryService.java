package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.component.Excel;
import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.repository.CategoryRepository;


@Service
public class CategoryService {

	@Autowired 
	private CategoryRepository categoryRepository;
	
	@Autowired
	Excel excel;

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findbyId(Integer id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada!"));
	}

	public List<Category> findbyCategory(String text) {
		List<Category> category = categoryRepository.findByCategoria(text);
		if (category.isEmpty()) 
			throw new ObjectNotFoundException("Categoria não encontrada!");
		
		return category;
	}

	public void delete(Integer id) {
		findbyId(id);
		categoryRepository.deleteById(id);
	}

	public Category insert(Category category) {
		return categoryRepository.save(category);
	}

	public Category update(Category category) {
		Category newObj = findbyId(category.getId());
		
		newObj.setCategoria(category.getCategoria());
		newObj.setData(category.getData());
		newObj.setUser(category.getUser());
		
		return categoryRepository.save(category);
	}
	
	public byte[] createExcel() throws IOException {
		String[] titulos = new String[]{"ID","Categoria","Data","Usuário"};	
		return excel.exportExcel((ArrayList<?>) findAll(), "Categorias", titulos).toByteArray();
	}
}