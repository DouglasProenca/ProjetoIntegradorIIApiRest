package com.sistema.apicr7imports.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("SELECT c FROM Category c WHERE c.categoria like %:categoria%")
	public List<Category> findByCategoria(@Param("categoria") String categoria);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "INSERT INTO rc_categoria VALUES (:#{#c.categoria}, :#{#c.data}, :#{#c.user})", nativeQuery = true)
	public void insert(@Param("c") Category c);

}