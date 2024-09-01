package com.sistema.apicr7imports.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.sistema.apicr7imports.repository.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;

import com.sistema.apicr7imports.util.CodeString;
import com.sistema.apicr7imports.data.dto.request.CreateUserRequest;
import com.sistema.apicr7imports.data.model.User;
import com.sistema.apicr7imports.exception.ForeignKeyException;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;
import com.sistema.apicr7imports.mapper.DozerMapper;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findbyId(Integer id) {		
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
	}
	
	public User findbyUser(String text) {		
		return repository.findByUsername(text).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
	}

	public void delete(Integer id) {
		findbyId(id);
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new ForeignKeyException("O Registro possui relação com outros registros e não pode ser excluido");
		}
	}
	
	public User insert(CreateUserRequest createUserRequest) {
		User user = DozerMapper.parseObject(createUserRequest, User.class);
		user.setPassword(BCrypt.withDefaults().hashToString(8, createUserRequest.getPassword().toCharArray()));
		user.setMailPassword(CodeString.codeString(createUserRequest.getMailPassword()));
		user.setAccountNonLocked(true);
		user.setAccountNonExpired(true);
		return repository.save(user);
	}

	public User update(User obj) {
		User newObj = findbyId(obj.getUserId());
		newObj.setUserName(obj.getUsername());
		newObj.setUserMail(obj.getUserMail());
		return repository.save(obj);
	}
}