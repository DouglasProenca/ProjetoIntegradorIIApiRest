package com.sistema.apicr7imports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}