package com.sistema.apicr7imports.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.domain.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{

	@Query("SELECT b FROM Brand b WHERE b.marca like %:marca%")
	public List<Brand> findByMarca(@Param("marca") String marca);
	
	@Query("SELECT b FROM Brand b WHERE b.marca like %:marca%")
	public Page<Brand> findByMarcaPageable(@Param("marca") String marca,Pageable pageable);


	/*@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "INSERT INTO rc_marca VALUES (:#{#b.marca}, :#{#b.country}, :#{#b.data}, :#{#b.user})", nativeQuery = true)
	public void insert(@Param("b") Brand b);*/
}
