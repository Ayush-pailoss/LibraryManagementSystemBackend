 package com.lms.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionEntity>  resourceNotFoundHandler(ResourceNotFoundException ex){
		ExceptionEntity exception= ExceptionEntity.builder()
				.message(ex.getMessage()).httpStatus(HttpStatus.NOT_FOUND).build();
		
		return new ResponseEntity<ExceptionEntity>(exception,HttpStatus.NOT_FOUND) ;
		
	}
}
