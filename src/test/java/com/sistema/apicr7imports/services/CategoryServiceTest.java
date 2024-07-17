package com.sistema.apicr7imports.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.mocks.MockCategory;
import com.sistema.apicr7imports.repository.CategoryRepository;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

	MockCategory input;

	@InjectMocks
	private CategoryService service;

	@Mock
	CategoryRepository categoryRepository;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockCategory();
		MockitoAnnotations.initMocks(this);

	}

	@Test
	void testFindAll() {
		List<Category> list = input.mockEntityList();

		when(categoryRepository.findAll()).thenReturn(list);

		List<Category> cateogry = service.findAll();

		assertNotNull(cateogry);
		assertEquals(14, cateogry.size());

		Category categoryOne = cateogry.get(1);

		assertNotNull(categoryOne);
		assertNotNull(categoryOne.getId());

		assertEquals("Category name Test1", categoryOne.getCategoria());


		Category categoryFour = cateogry.get(4);

		assertNotNull(categoryFour);
		assertNotNull(categoryFour.getId());

		assertEquals("Category name Test4", categoryFour.getCategoria());
		
		Category categorySeven = cateogry.get(7);

		assertNotNull(categorySeven);
		assertNotNull(categorySeven.getId());

		assertEquals("Category name Test7", categorySeven.getCategoria());

	}

	@Test
	void testFindbyId() {
		Category category = input.mockEntity(1);

		when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

		Category result = service.findbyId(1);

		assertNotNull(result);
		assertNotNull(result.getId());
		assertTrue(result.toString().contains(""));
		assertEquals("Category name Test1", result.getCategoria());

	}

	@Test
	void testFindbyCategory() {
		List<Category> list = input.mockEntityList();

		when(categoryRepository.findAll()).thenReturn(list);

		List<Category> cateogry = service.findAll();

		assertNotNull(cateogry);
		assertEquals(14, cateogry.size());

		Category categoryOne = cateogry.get(1);

		assertNotNull(categoryOne);
		assertNotNull(categoryOne.getId());

		assertTrue(categoryOne.getCategoria().contains("Category name Test1"));

		Category categoryFour = cateogry.get(4);

		assertNotNull(categoryFour);
		assertNotNull(categoryFour.getId());

		assertTrue(categoryFour.getCategoria().contains("Category name Test4"));
		
		Category categorySeven = cateogry.get(7);

		assertNotNull(categorySeven);
		assertNotNull(categorySeven.getId());

		assertTrue(categorySeven.getCategoria().contains("Category name Test7"));
	
	}

	@Test
	void testUpdate() {
		Category entity = input.mockEntity(1);

		Category persisted = entity;
		persisted.setId(1);

		when(categoryRepository.findById(1)).thenReturn(Optional.of(entity));
		when(categoryRepository.save(entity)).thenReturn(persisted);

		Category categoryUpdate = persisted;
		categoryUpdate.setCategoria("teste2");

		Category result = service.update(categoryUpdate);

		assertNotNull(result);
		assertNotNull(result.getId());

		assertEquals("teste2", result.getCategoria());
		assertEquals(1, result.getId());

	}

	@Test
	void testeDelete() {
		Category category = input.mockEntity(1);

		when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

		service.delete(category.getId());

	}

}
