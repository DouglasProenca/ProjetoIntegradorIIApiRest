package com.sistema.apicr7imports.data.dto.response;

import java.time.LocalDate;

import com.sistema.apicr7imports.data.dto.UserDTO;

import lombok.Data;

@Data
public class ClientDTO {

	Integer clientId;
	String clientName;
	String cpf;
	LocalDate date;
	UserDTO user;
	
}
