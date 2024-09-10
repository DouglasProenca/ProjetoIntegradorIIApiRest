package com.sistema.apicr7imports.exception.handeler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExceptionResponse {

	Long timestamp;
	Integer status;
	String error;
	String message;
	String path;

}
