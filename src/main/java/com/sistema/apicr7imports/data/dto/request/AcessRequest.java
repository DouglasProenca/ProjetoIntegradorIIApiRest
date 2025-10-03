package com.sistema.apicr7imports.data.dto.request;



import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class AcessRequest {

	@NotNull(message = "username é obrigatório")
	String username;
	@NotNull(message = "Password é obrigatório")
	String password;

}
