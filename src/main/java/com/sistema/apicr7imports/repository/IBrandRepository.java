package com.sistema.apicr7imports.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.data.model.Brand;


@Repository
public interface IBrandRepository extends JpaRepository<Brand, Integer>{

	Optional<List<Brand>> findByBrandNameContaining(@Param("marca") String marca);
	
	Optional<Page<Brand>> findByBrandNameContaining(@Param("marca") String marca,Pageable pageable);

}
