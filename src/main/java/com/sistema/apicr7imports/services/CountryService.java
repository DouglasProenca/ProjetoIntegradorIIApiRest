package com.sistema.apicr7imports.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;

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
	
	@Autowired
	private PagedResourcesAssembler<Country> assembler;

	public List<Country> findAll() {
		List<Country> list = countryRepository.findAll();
		list.stream().forEach(l -> l.add(linkTo(methodOn(CountryController.class).findById(l.getId())).withSelfRel()));
		return list;
	}
	
	public PagedModel<EntityModel<Country>> findAllPage(Pageable pageable) {
		Page<Country> list = countryRepository.findAll(pageable);
		
		list.stream().forEach(l -> l.add(linkTo(methodOn(CountryController.class).findById(l.getId())).withSelfRel()));
		
		Link link = linkTo(methodOn(CountryController.class).findAllPage(pageable.getPageNumber(),pageable.getPageSize(),"asc")).withSelfRel();
		
		return assembler.toModel(list, link);
	}
	

	public Country findbyId(Integer id) {
		Country country = countryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pais não encontrado"));
		country.add(linkTo(methodOn(CountryController.class).findById(id)).withSelfRel());
		return country;
	}
}