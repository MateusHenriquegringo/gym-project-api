package edu.mateus.Gym.Users.exeptions.handlers;

import edu.mateus.Gym.Users.exeptions.EmailNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmailNotValidExceptionHandler {

	@ExceptionHandler(EmailNotValidException.class)
	public ResponseEntity<Object> handle(EmailNotValidException exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
