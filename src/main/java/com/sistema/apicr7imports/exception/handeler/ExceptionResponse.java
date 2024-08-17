package com.sistema.apicr7imports.exception.handeler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

	Long timestamp;
	Integer status;
	String error;
	String message;
	String path;

}
