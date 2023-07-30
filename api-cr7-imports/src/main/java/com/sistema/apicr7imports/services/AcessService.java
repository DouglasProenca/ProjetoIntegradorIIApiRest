package com.sistema.apicr7imports.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.domain.Acess;
import com.sistema.apicr7imports.services.exception.ObjectNotFoundException;

@Service
public class AcessService {

	/* Simula uma tabela do banco com os usuarios */
	private final Map<String, String> users = new HashMap();

	/* Simula uma tabela do banco com as chaves de acesso */
	private final Map<String, String> keys = new HashMap();

	/* Guarda os tokens gerados em tempo de execução */
	private static Map<String, String> tokens = new HashMap<>();

	private static AcessService autenticator = null;

	public AcessService() {

		users.put("walter", "white");
		users.put("toni", "soprano");

	}

	public static AcessService getInstance() {
		if (Objects.isNull(autenticator)) {
			autenticator = new AcessService();
		}
		return autenticator;
	}

	public Acess login(String user, String password) throws ObjectNotFoundException {

		if (users.containsKey(user)) {
			String passwordStorage = users.get(user);

			if (passwordStorage.equals(password)) {
				String auth = UUID.randomUUID().toString();
				tokens.put(auth, user);
				return new Acess(user, password, auth);
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
