package com.sistema.apicr7imports.mocks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.domain.User;

public class MockCategory {

	 public Category mockEntity() {
	    	return mockEntity(0);
	    }
	    
	    public List<Category> mockEntityList() {
	        List<Category> categoryList = new ArrayList<Category>();
	        for (int i = 0; i < 14; i++) {
	        	categoryList.add(mockEntity(i));
	        }
	        return categoryList;
	    }
	    
	    public Category mockEntity(Integer number) {
	        return new Category(number,"Category name Test" + number, LocalDate.now(), new User());
	    }
}
