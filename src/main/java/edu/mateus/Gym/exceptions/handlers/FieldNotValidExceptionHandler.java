package edu.mateus.Gym.exceptions.handlers;

import edu.mateus.Gym.exceptions.FieldNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FieldNotValidExceptionHandler {

	@ExceptionHandler(FieldNotValidException.class)
	public ResponseEntity<Object> handle(FieldNotValidException exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
