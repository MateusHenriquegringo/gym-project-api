package edu.mateus.Gym.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FieldNotValidException extends RuntimeException{

	public FieldNotValidException(){
		super("invalid password");
	}

}
