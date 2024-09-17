package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.util.ExcelEngine;
import com.sistema.apicr7imports.data.dto.CategoryDTO;
import com.sistema.apicr7imports.data.dto.request.CategoryRequest;
import com.sistema.apicr7imports.data.model.Category;
import com.sistema.apicr7imports.data.model.User;
import com.sistema.apicr7imports.exception.ForeignKeyException;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.mapper.DozerMapper;
import com.sistema.apicr7imports.repository.ICategoryRepository;


@Service
public class CategoryService {

	@Autowired 
	ICategoryRepository repository;
	
	@Autowired
	ExcelEngine excel;

	public List<CategoryDTO> findAll() {
		return DozerMapper.parseListObject(repository.findAll(), CategoryDTO.class);
	}

	public CategoryDTO findbyId(Integer id) {
		Category category = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada!"));
		return DozerMapper.parseObject(category, CategoryDTO.class);
	}

	public List<CategoryDTO> findbyCategory(String name) {
		Optional<List<Category>> list = repository.findByCategoria(name);
		return DozerMapper.parseListObject(list.filter(l -> !l.isEmpty()).orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada!")), CategoryDTO.class);
	}

	public void delete(Integer id) {
		findbyId(id);
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new ForeignKeyException("O Registro possui relação com outros registros e não pode ser excluido!");
		}
	}

	public CategoryDTO save(CategoryRequest categoryRequest) {
		Category category = new Category();
		category.setCategoryName(categoryRequest.getCategoryName());
		category.setDate(LocalDate.now());
        category.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return DozerMapper.parseObject(repository.save(category),CategoryDTO.class);
	}

	public CategoryDTO update(Integer id, CategoryRequest categoryRequest) {
		Category newObj = DozerMapper.parseObject(findbyId(id), Category.class);
		newObj.setCategoryName(categoryRequest.getCategoryName());
		newObj.setDate(LocalDate.now());
		newObj.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return DozerMapper.parseObject(repository.save(newObj), CategoryDTO.class);
	}
	
	public byte[] getExcel() throws IOException {
		String[] titles = new String[]{"ID","Categoria","Data","Usuário"};	
		return excel.generateExcel((ArrayList<?>) repository.findAll(), "Categorias", titles);
	}
	
	public Page<CategoryDTO> findAllPage(Pageable pageable) {
		return repository.findAll(pageable).map(category -> DozerMapper.parseObject(category, CategoryDTO.class));
	}
	
	public Page<CategoryDTO> findbyBrandPageable(String name,Pageable pageable) {
		Optional<Page<Category>> list = repository.findByCategoriaPageable(name,pageable);
		return list.filter(l -> !l.isEmpty()).orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada!"))
		           .map(category -> DozerMapper.parseObject(category, CategoryDTO.class));
	}
}