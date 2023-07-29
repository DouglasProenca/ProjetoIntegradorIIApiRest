package com.sistema.apicr7imports.services;

import java.util.UUID;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.repository.UserRepository;

@Service
public class AcessService {

	@Autowired
	private UserRepository repo;
	

	
}