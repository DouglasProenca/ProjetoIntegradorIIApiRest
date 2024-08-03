package com.sistema.apicr7imports.exception.handeler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.sistema.apicr7imports.exception.ForeignKeyException;
import com.sistema.apicr7imports.exception.InvalidJwtAuthenticationException;
import com.sistema.apicr7imports.exception.ObjectNotFoundException;

@ControllerAdvice
@RestController
public class ResourceExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ExceptionResponse err = new ExceptionResponse(System.currentTimeMillis(), status.value(), "Erro Geral",
				e.getMessage(), request.getDescription(false));
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ExceptionResponse err = new ExceptionResponse(System.currentTimeMillis(), status.value(), "Não encotrado",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);

	}
	
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public final ResponseEntity<ExceptionResponse> invalidJwtAuthenticationException(Exception ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		ExceptionResponse err = new ExceptionResponse(System.currentTimeMillis(), status.value(), "Erro de Acesso",
				ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ForeignKeyException.class)
	public final ResponseEntity<ExceptionResponse> foreignKeyExceptionException(ForeignKeyException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ExceptionResponse err = new ExceptionResponse(System.currentTimeMillis(), status.value(), "Erro de Chave Estrangeira",
				ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
