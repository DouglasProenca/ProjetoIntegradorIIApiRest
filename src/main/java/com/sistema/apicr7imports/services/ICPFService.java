package com.sistema.apicr7imports.services;

import com.sistema.apicr7imports.data.dto.response.CpfResponse;

public interface ICPFService {
	
	CpfResponse verifyCPF(String cpf);

}
