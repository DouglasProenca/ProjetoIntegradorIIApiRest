package com.sistema.apicr7imports.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.data.model.Brand;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{

	@Query("SELECT b FROM Brand b WHERE b.brandName like %:marca%")
	Optional<List<Brand>> findByMarca(@Param("marca") String marca);
	
	@Query("SELECT b FROM Brand b WHERE b.brandName like %:marca%")
	Optional<Page<Brand>> findByMarcaPageable(@Param("marca") String marca,Pageable pageable);

}
