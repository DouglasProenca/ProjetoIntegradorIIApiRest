package com.sistema.apicr7imports.repository;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


@Repository
public class CPFRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public Boolean isCPF(String cpf) throws SQLException {
		Query query = entityManager.createNativeQuery("SELECT fn_isCPF( ?1 ) AS is_cpf");
		query.setParameter(1, cpf);

		return Boolean.valueOf(query.getSingleResult().toString());
	}
}
