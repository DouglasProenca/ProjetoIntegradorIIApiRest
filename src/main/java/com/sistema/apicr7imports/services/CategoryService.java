package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.component.Excel;
import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.repository.CategoryRepository;


@Service
public class CategoryService {

	@Autowired 
	private CategoryRepository repository;
	
	@Autowired
	Excel excel;

	public List<Category> findAll() {
		return repository.findAll();
	}

	public Category findbyId(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada!"));
	}

	public List<Category> findbyCategory(String name) {
		List<Category> list = repository.findByCategoria(name);
		if (list.isEmpty()) 
			throw new ObjectNotFoundException("Categoria não encontrada!");
		
		return list;
	}

	public void delete(Integer id) {
		findbyId(id);
		repository.deleteById(id);
	}

	public Category save(Category category) {
		return repository.save(category);
	}

	public Category update(Category category) {
		Category newObj = findbyId(category.getId());
		
		newObj.setCategoria(category.getCategoria());
		newObj.setData(category.getData());
		newObj.setUser(category.getUser());
		
		return repository.save(category);
	}
	
	public byte[] getExcel() throws IOException {
		String[] titulos = new String[]{"ID","Categoria","Data","Usuário"};	
		return excel.exportExcel((ArrayList<?>) findAll(), "Categorias", titulos).toByteArray();
	}
	
	public Page<Category> findAllPage(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Page<Category> findbyBrandPageable(String name,Pageable pageable) {
		Page<Category> list = repository.findByCategoriaPageable(name,pageable);
		
		if (list.isEmpty()) 
			throw new ObjectNotFoundException("Categoria não encontrada!");

		return list;
	}
}