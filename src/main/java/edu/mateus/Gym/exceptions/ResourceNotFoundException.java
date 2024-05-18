package edu.mateus.Gym.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ResourceNotFoundException extends EntityNotFoundException {
	public ResourceNotFoundException(String mensage){
		super(mensage);
	}
}
