package com.sistema.apicr7imports.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.component.Excel;
import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.domain.User;
import com.sistema.apicr7imports.domain.Dto.CategoryDTO;
import com.sistema.apicr7imports.domain.Dto.request.CreateCategoryRequest;
import com.sistema.apicr7imports.domain.Dto.request.EditCategoryRequest;
import com.sistema.apicr7imports.exception.ForeignKeyException;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.mapper.DozerMapper;
import com.sistema.apicr7imports.repository.CategoryRepository;


@Service
public class CategoryService {

	@Autowired 
	CategoryRepository repository;
	
	@Autowired
	Excel excel;

	public List<CategoryDTO> findAll() {
		return DozerMapper.parseListObject(repository.findAll(), CategoryDTO.class);
	}

	public CategoryDTO findbyId(Integer id) {
		Category category = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada!"));
		return DozerMapper.parseObject(category, CategoryDTO.class);
	}

	public List<CategoryDTO> findbyCategory(String name) {
		List<Category> list = repository.findByCategoria(name);
		
		if (list.isEmpty()) 
			throw new ObjectNotFoundException("Categoria não encontrada!");
		
		return DozerMapper.parseListObject(list, CategoryDTO.class);
	}

	public void delete(Integer id) {
		findbyId(id);
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new ForeignKeyException("O Registro possui relação com outros registros e não pode ser excluido");
		}
	}

	public CategoryDTO save(CreateCategoryRequest categoryRequest) {
		Category category = new Category();
		category.setCategoria(categoryRequest.getCategoria());
		category.setData(categoryRequest.getData());
        category.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return DozerMapper.parseObject(repository.save(category),CategoryDTO.class);
	}

	public CategoryDTO update(EditCategoryRequest categoryRequest) {
		Category newObj = DozerMapper.parseObject(findbyId(categoryRequest.getId()),Category.class);
		newObj.setCategoria(categoryRequest.getCategoria());
		newObj.setData(categoryRequest.getData());
		newObj.setUser(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return DozerMapper.parseObject(repository.save(newObj),CategoryDTO.class);
	}
	
	public byte[] getExcel() throws IOException {
		String[] titulos = new String[]{"ID","Categoria","Data","Usuário"};	
		return excel.exportExcel((ArrayList<?>) findAll(), "Categorias", titulos).toByteArray();
	}
	
	public Page<CategoryDTO> findAllPage(Pageable pageable) {
		return repository.findAll(pageable).map(category -> DozerMapper.parseObject(category, CategoryDTO.class));
	}
	
	public Page<CategoryDTO> findbyBrandPageable(String name,Pageable pageable) {
		Page<Category> list = repository.findByCategoriaPageable(name,pageable);
		
		if (list.isEmpty()) 
			throw new ObjectNotFoundException("Categoria não encontrada!");

		return list.map(category -> DozerMapper.parseObject(category, CategoryDTO.class));
	}
}