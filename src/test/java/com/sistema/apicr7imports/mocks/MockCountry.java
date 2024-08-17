package com.sistema.apicr7imports.mocks;

import java.util.ArrayList;
import java.util.List;

import com.sistema.apicr7imports.data.model.Country;

public class MockCountry {

	public Country mockEntity() {
		return mockEntity(0);
	}

	public List<Country> mockEntityList() {
		List<Country> countryList = new ArrayList<Country>();
		for (int i = 0; i < 14; i++) {
			countryList.add(mockEntity(i));
		}
		return countryList;
	}

	public Country mockEntity(Integer number) {
		return new Country(number, "Name Port Test" + number, "Name Eng Test" + number);
	}
}
