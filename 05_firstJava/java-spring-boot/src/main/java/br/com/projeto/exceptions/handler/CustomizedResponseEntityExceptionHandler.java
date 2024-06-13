package br.com.projeto.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.projeto.exceptions.ExceptionResponse;
import br.com.projeto.exceptions.InvalidJwtAutenticationException;
import br.com.projeto.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest webReq){
		
		ExceptionResponse er = new ExceptionResponse(
				new Date(), 
				ex.getMessage(),
				webReq.getDescription(false));
		
		
		return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest webReq){
		
		ExceptionResponse er = new ExceptionResponse(
				new Date(), 
				ex.getMessage(),
				webReq.getDescription(false));
		
		
		return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);	
	}

	@ExceptionHandler(InvalidJwtAutenticationException.class)
	public final ResponseEntity<ExceptionResponse> handleInvalidJwtAutenticationException(Exception ex, WebRequest webReq){
		
		ExceptionResponse er = new ExceptionResponse(
				new Date(), 
				ex.getMessage(),
				webReq.getDescription(false));
		
		
		return new ResponseEntity<>(er, HttpStatus.FORBIDDEN);	
	}
	

}
