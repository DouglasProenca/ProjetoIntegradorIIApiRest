package com.sistema.apicr7imports.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.apicr7imports.data.model.ProductImage;

public interface IProductImageRepository extends JpaRepository<ProductImage, Integer> {
	

}
