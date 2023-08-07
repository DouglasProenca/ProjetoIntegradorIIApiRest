package com.sistema.apicr7imports.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.domain.Acess;
import com.sistema.apicr7imports.domain.User;
import com.sistema.apicr7imports.repository.UserRepository;
import com.sistema.apicr7imports.resources.util.CryptoUtils;
import com.sistema.apicr7imports.services.exception.ObjectNotFoundException;

@Service
public class AcessService {
	
	@Autowired
	private UserRepository repo;

	/* Guarda os tokens gerados em tempo de execução */
	private static Map<String, String> tokens = new HashMap<>();

	private static AcessService autenticator = null;

	public static AcessService getInstance() {
		if (Objects.isNull(autenticator)) {
			autenticator = new AcessService();
		}
		return autenticator;
	}

	public Acess login(String user, String password) throws ObjectNotFoundException {
		
		List<User> usarios = repo.findByUser(user);
		
		if (!usarios.isEmpty()) {

			if (CryptoUtils.verificarSenha(password, usarios.get(0).getPassword())) {
				String auth = UUID.randomUUID().toString();
				tokens.put(auth, user);
				return new Acess(usarios.get(0).getId(),user, password, auth);
			}
		}
		throw new ObjectNotFoundException("Falha no Login");
	}

	public boolean isTokenValid(String token) {
		if (tokens.containsKey(token)) {
			return true;
		}
		return false;
	}

	public void logout(String token) throws ObjectNotFoundException {
		if (isTokenValid(token)) {
				tokens.remove(token);
				return;
		}
		throw new ObjectNotFoundException("Falha no Login");
	}
}
