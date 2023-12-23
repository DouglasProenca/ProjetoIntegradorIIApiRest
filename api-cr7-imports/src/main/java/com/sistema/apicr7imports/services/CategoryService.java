package com.sistema.apicr7imports.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public List<Category> findAll() {
		return repo.findAll();
	}

	public Category findbyId(Long id) {
		Category country = repo.findById(Long.valueOf(id)).orElse(null);
		if (country == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return country;
	}

	public List<Category> findbyCategory(String text) {
		List<Category> category = repo.findByCategoria(text);
		if (category == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return category;
	}

	public void delete(Long id) {
		findbyId(id);
		repo.deleteById(id);
	}
	
	public Category insert(Category obj) {
		repo.insert(obj);
		return obj;
	}
	
	public Category update(Category obj) {
		Category newObj = findbyId(Long.valueOf(obj.getId()));
		updateData(newObj,obj);
		return repo.save(obj);
	}

	private void updateData(Category newObj, Category category) {
		newObj.setCategoria(category.getCategoria());
		newObj.setData(category.getData());
		newObj.setUser(category.getUser());
	}
}