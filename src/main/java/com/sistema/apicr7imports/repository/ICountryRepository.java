package com.sistema.apicr7imports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.data.model.Country;

@Repository
public interface ICountryRepository extends JpaRepository<Country, Integer> {

}