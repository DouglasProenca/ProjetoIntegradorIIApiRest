package com.sistema.apicr7imports.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.controller.CountryController;
import com.sistema.apicr7imports.domain.Country;
import com.sistema.apicr7imports.repository.CountryRepository;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> findAll() {
		List<Country> list = countryRepository.findAll();
		list.stream().forEach(l -> l.add(linkTo(methodOn(CountryController.class).findById(l.getId())).withSelfRel()));
		return list;
	}

	public Country findbyId(long id) {
		Country country = countryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pais não encontrado"));
		country.add(linkTo(methodOn(CountryController.class).findById(id)).withSelfRel());
		return country;
	}
}