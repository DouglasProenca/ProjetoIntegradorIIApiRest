package com.sistema.apicr7imports.exception;

public class ForeignKeyException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ForeignKeyException (String msg) {
		super(msg);
	}

}
