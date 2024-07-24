package com.sistema.apicr7imports.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("SELECT c FROM Category c WHERE c.categoria like %:categoria%")
	public List<Category> findByCategoria(@Param("categoria") String categoria);
	
	@Query("SELECT c FROM Category c WHERE c.categoria like %:categoria%")
	public Page<Category> findByCategoriaPageable(@Param("categoria") String categoria, Pageable pageable);

}