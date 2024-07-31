package com.sistema.apicr7imports.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sistema.apicr7imports.domain.Country;
import com.sistema.apicr7imports.mocks.MockCountry;
import com.sistema.apicr7imports.repository.CountryRepository;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

	MockCountry input;
	
	@InjectMocks
	private CountryService service;
	
	@Mock
	CountryRepository countryRepository;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockCountry();
		MockitoAnnotations.initMocks(this);
		
	}

	@Test
	void testFindAllPage() {
		
	}

	@Test
	void testFindbyId() {
		Country country = input.mockEntity(1);
		
		when(countryRepository.findById(1)).thenReturn(Optional.of(country));
		
		Country result = service.findbyId(1);
		
		assertNotNull(result);
		assertNotNull(result.getIdCountry());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains(""));
		assertEquals("Name Port Test1", result.getNamePort());
		assertEquals("Name Eng Test1", result.getNameEng());
	}

}
