package com.sistema.apicr7imports.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.domain.User;
import com.sistema.apicr7imports.repository.UserRepository;
import com.sistema.apicr7imports.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findbyId(String id) {
		User user = repo.findById(Long.valueOf(id)).orElse(null);
		if (user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user;
	}

	public void delete(String id) {
		findbyId(id);
		repo.deleteById(Long.valueOf(id));
	}

	public User update(User obj) {
		User newObj = findbyId(String.valueOf(obj.getId()));
		updateData(newObj,obj);
		return repo.save(obj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setUser(obj.getUser());
		newObj.setMail(obj.getMail());
	}
}