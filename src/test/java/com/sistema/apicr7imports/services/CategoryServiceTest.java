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

import com.sistema.apicr7imports.data.dto.CategoryDTO;
import com.sistema.apicr7imports.data.model.Category;
import com.sistema.apicr7imports.mapper.DozerMapper;
import com.sistema.apicr7imports.mocks.MockCategory;
import com.sistema.apicr7imports.repository.ICategoryRepository;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

	MockCategory input;

	@InjectMocks
	private CategoryService service;

	@Mock
	ICategoryRepository categoryRepository;

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

		List<Category> cateogry = DozerMapper.parseListObject(service.findAll(), Category.class);

		assertNotNull(cateogry);
		assertEquals(14, cateogry.size());

		Category categoryOne = cateogry.get(1);

		assertNotNull(categoryOne);
		assertNotNull(categoryOne.getCategoryId());

		assertEquals("Category name Test1", categoryOne.getCategoryName());


		Category categoryFour = cateogry.get(4);

		assertNotNull(categoryFour);
		assertNotNull(categoryFour.getCategoryId());

		assertEquals("Category name Test4", categoryFour.getCategoryName());
		
		Category categorySeven = cateogry.get(7);

		assertNotNull(categorySeven);
		assertNotNull(categorySeven.getCategoryId());

		assertEquals("Category name Test7", categorySeven.getCategoryName());

	}

	@Test
	void testFindbyId() {
		Category category = input.mockEntity(1);

		when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

		CategoryDTO result =  service.findbyId(1);

		assertNotNull(result);
		assertNotNull(result.getCategoryId());
		assertTrue(result.toString().contains(""));
		assertEquals("Category name Test1", result.getCategoryName());

	}

	@Test
	void testFindbyCategory() {
		List<Category> list = input.mockEntityList();

		when(categoryRepository.findAll()).thenReturn(list);

		List<Category> cateogry = DozerMapper.parseListObject(service.findAll(), Category.class);

		assertNotNull(cateogry);
		assertEquals(14, cateogry.size());

		Category categoryOne = cateogry.get(1);

		assertNotNull(categoryOne);
		assertNotNull(categoryOne.getCategoryId());

		assertTrue(categoryOne.getCategoryName().contains("Category name Test1"));

		Category categoryFour = cateogry.get(4);

		assertNotNull(categoryFour);
		assertNotNull(categoryFour.getCategoryId());

		assertTrue(categoryFour.getCategoryName().contains("Category name Test4"));
		
		Category categorySeven = cateogry.get(7);

		assertNotNull(categorySeven);
		assertNotNull(categorySeven.getCategoryId());

		assertTrue(categorySeven.getCategoryName().contains("Category name Test7"));
	
	}

	@Test
	void testUpdate() {
		/*EditCategoryRequest entity = input.mockEntity(1);

		EditCategoryRequest persisted = entity;
		persisted.setId(1);

		when(categoryRepository.findById(1)).thenReturn(Optional.of(entity));
		when(categoryRepository.save(entity)).thenReturn(persisted);

		EditCategoryRequest categoryUpdate = persisted;
		categoryUpdate.setCategoria("teste2");

		CategoryDTO result = service.update(categoryUpdate);

		assertNotNull(result);
		assertNotNull(result.getId());

		assertEquals("teste2", result.getCategoria());
		assertEquals(1, result.getId());
*/
	}

	@Test
	void testeDelete() {
		Category category = input.mockEntity(1);

		when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

		service.delete(category.getCategoryId());

	}

}
