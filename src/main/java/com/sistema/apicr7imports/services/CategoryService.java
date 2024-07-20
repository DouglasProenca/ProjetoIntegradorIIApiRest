package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistema.apicr7imports.component.Excel;
import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.repository.CategoryRepository;


@Service
public class CategoryService {

	@Autowired 
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findbyId(Integer id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada!"));
	}

	public List<Category> findbyCategory(String text) {
		List<Category> category = categoryRepository.findByCategoria(text);
		if (category.isEmpty()) {
			throw new ObjectNotFoundException("Categoria não encontrada!");
		}
		return category;
	}

	public ResponseEntity<Void> delete(Integer id) {
		findbyId(id);
		categoryRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<Void> insert(Category obj) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(categoryRepository.save(obj).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	public Category update(Category category) {
		Category newObj = findbyId(category.getId());
		
		newObj.setCategoria(category.getCategoria());
		newObj.setData(category.getData());
		newObj.setUser(category.getUser());
		
		return categoryRepository.save(category);
	}
	
	public ResponseEntity<byte[]> createExcel() throws IOException {
		
		Excel excel = new Excel();
		ArrayList<?> dados = (ArrayList<?>) findAll();
		String[] titulos = new String[]{"ID","Categoria","Data","Usuário"};
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename("categorias.xlsx").build());

		return ResponseEntity.ok().headers(headers).body(excel.exportExcel(dados, "Categorias", titulos).toByteArray());
	}
}