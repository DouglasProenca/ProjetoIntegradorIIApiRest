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
	
	public boolean isCPF(String cpf) throws SQLException {
		Query query = entityManager.createNativeQuery("SELECT IIF([dbo].[fn_isCPF] ( ?1 ) = 1,'true','false') AS is_cpf");
		query.setParameter(1, cpf);

		return Boolean.valueOf(query.getResultList().get(0).toString());
	}
}
