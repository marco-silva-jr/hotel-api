package com.hotel.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandarError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
		StandarError err = new StandarError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Erro!");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

}
