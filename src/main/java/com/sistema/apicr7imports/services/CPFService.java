package com.sistema.apicr7imports.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.apicr7imports.data.dto.response.CpfResponse;
import com.sistema.apicr7imports.repository.CPFRepository;

@Service
public class CPFService {

	@Autowired
	CPFRepository repository;

	public CpfResponse verifyCPF(String cpf) throws SQLException {
		return new CpfResponse(repository.isCPF(cpf));
	}
}
