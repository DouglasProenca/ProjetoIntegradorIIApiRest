package com.sistema.apicr7imports.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.data.model.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {

	@Query("SELECT c FROM Category c WHERE c.categoryName like %:categoria%")
	Optional<List<Category>> findByCategoria(@Param("categoria") String categoria);

	@Query("SELECT c FROM Category c WHERE c.categoryName like %:categoria%")
	Optional<Page<Category>> findByCategoriaPageable(@Param("categoria") String categoria, Pageable pageable);

}