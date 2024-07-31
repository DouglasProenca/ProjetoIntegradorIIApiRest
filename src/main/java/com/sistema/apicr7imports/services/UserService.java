package com.sistema.apicr7imports.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.domain.User;
import com.sistema.apicr7imports.repository.UserRepository;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findbyId(Integer id) {
		User user = repository.findById(id).orElse(null);
		
		if (user == null) 
			throw new ObjectNotFoundException("Objeto não encontrado");
		
		return user;
	}
	
	public User findbyUser(String text) {
		User user = repository.findByUsername(text);
		
		if (user == null) 
			throw new ObjectNotFoundException("Objeto não encontrado");
		
		return user;
	}

	public void delete(Integer id) {
		findbyId(id);
		repository.deleteById(id);
	}
	
	public User insert(User obj) {
		repository.insert(obj);
		return obj;
	}

	public User update(User obj) {
		User newObj = findbyId(obj.getId());
		updateData(newObj,obj);
		return repository.save(obj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setUserName(obj.getUsername());
		newObj.setMail(obj.getMail());
	}
}