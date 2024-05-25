package edu.mateus.Gym.Exercises.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DataIntegrityViolationExecptionHandler {
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handler(DataIntegrityViolationException exception){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
	}
}
