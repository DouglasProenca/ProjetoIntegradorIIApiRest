package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.repository.CategoryRepository;
import com.sistema.apicr7imports.services.excel.Excel;

@Service
public class CategoryService {

	@Autowired 
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findbyId(Long id) {
		return categoryRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada!"));
	}

	public List<Category> findbyCategory(String text) {
		List<Category> category = categoryRepository.findByCategoria(text);
		if (category.isEmpty()) {
			throw new ObjectNotFoundException("Categoria não encontrada!");
		}
		return category;
	}

	public void delete(Long id) {
		findbyId(id);
		categoryRepository.deleteById(id);
	}

	public Category insert(Category obj) {
		categoryRepository.insert(obj);
		return obj;
	}

	public Category update(Category obj) {
		Category newObj = findbyId(Long.valueOf(obj.getId()));
		updateData(newObj, obj);
		return categoryRepository.save(obj);
	}

	private void updateData(Category newObj, Category category) {
		newObj.setCategoria(category.getCategoria());
		newObj.setData(category.getData());
		newObj.setUser(category.getUser());
	}
	
	public byte[] createExcel() throws IOException {
		
		Excel excel = new Excel();
		ArrayList<?> dados = (ArrayList<?>) findAll();
		String[] titulos = new String[]{"ID","Categoria","Data","Usuário"};
		
		return excel.exportExcel(dados, "Categorias", titulos).toByteArray();
	}
}