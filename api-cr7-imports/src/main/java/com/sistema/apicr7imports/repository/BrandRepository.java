package com.sistema.apicr7imports.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.domain.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{

	@Query("SELECT b FROM Brand b WHERE b.marca like %:marca%")
	public List<Brand> findByMarca(@Param("marca") String marca);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "INSERT INTO rc_marca VALUES (:#{#b.marca}, :#{#b.country}, :#{#b.data}, :#{#b.user})", nativeQuery = true)
	public void insert(@Param("b") Brand b);
}
