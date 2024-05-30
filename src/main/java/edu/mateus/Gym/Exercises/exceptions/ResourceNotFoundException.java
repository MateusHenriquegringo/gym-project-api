package edu.mateus.Gym.Exercises.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends EntityNotFoundException {
	public ResourceNotFoundException(){
		super("exercise was not found");

	}
}
