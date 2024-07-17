package com.sistema.apicr7imports.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT b FROM Product b WHERE b.nome like %:nome%")
	public List<Product> findByNome(@Param("nome") String nome);
}
