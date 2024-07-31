package com.sistema.apicr7imports.domain.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDTO {

	@NonNull
	Integer id;
	String userName;
	
}