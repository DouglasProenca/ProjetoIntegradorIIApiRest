package com.sistema.apicr7imports.data.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

	String userName;
	String password;
	String userMail;
	String mailPassword;
	LocalDate date;
	Boolean enabled;
	
}
