package com.sistema.apicr7imports.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sistema.apicr7imports.domain.Category;
import com.sistema.apicr7imports.domain.User;
import com.sistema.apicr7imports.domain.Dto.UserDTO;

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
	        return new Category(number,"Category name Test" + number, new Date(), new User());
	    }
}
