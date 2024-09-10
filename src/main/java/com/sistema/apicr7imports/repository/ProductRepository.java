package com.sistema.apicr7imports.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.data.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.productName like %:nome%")
	Optional<List<Product>> findByNome(@Param("nome") String nome);
	
	@Query("SELECT b FROM Product b WHERE b.productName like %:nome%")
	Optional<Page<Product>> findByNomePageable(@Param("nome") String nome, Pageable pageable);
	
}
