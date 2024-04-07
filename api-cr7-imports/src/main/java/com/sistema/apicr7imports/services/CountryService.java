package com.sistema.apicr7imports.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.domain.Country;
import com.sistema.apicr7imports.repository.CountryRepository;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	public Country findbyId(long id) {
		return countryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pais não encontrado"));
	}
}