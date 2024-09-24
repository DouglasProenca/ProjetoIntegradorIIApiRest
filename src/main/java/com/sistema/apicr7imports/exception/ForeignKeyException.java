package com.sistema.apicr7imports.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ForeignKeyException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ForeignKeyException (String msg) {
		super(msg);
	}

}
