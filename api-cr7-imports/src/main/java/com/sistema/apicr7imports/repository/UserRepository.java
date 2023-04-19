package com.sistema.apicr7imports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}