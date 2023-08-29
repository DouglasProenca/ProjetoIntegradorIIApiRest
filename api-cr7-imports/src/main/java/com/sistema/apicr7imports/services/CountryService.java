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
	private CountryRepository repo;

	public List<Country> findAll() {
		return repo.findAll();
	}

	public Country findbyId(String id) {
		Country country = repo.findById(Long.valueOf(id)).orElse(null);
		if (country == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return country;
	}
}