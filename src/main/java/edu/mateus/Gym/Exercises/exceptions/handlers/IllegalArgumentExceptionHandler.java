package edu.mateus.Gym.Exercises.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IllegalArgumentExceptionHandler {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handler(IllegalArgumentException exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
