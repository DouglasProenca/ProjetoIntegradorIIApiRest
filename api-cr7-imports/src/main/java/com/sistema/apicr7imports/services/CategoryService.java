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

	public Category findbyId(String id) {
		Category country = repo.findById(Long.valueOf(id)).orElse(null);
		if (country == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return country;
	}
}