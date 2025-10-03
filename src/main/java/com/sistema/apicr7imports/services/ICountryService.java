package com.sistema.apicr7imports.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import com.sistema.apicr7imports.data.model.Country;

public interface ICountryService {

	List<Country> findAll();
	
	PagedModel<EntityModel<Country>> findAllPage(Pageable pageable);
	

	Country findbyId(Integer id);

}