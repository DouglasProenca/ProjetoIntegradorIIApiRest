package com.sistema.apicr7imports.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.repository.IUserRepository;


@Service
public class UserDetailsServiceImplemented implements UserDetailsService {
	
	@Autowired
	IUserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
	}
	
}
