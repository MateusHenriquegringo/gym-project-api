package edu.mateus.Gym.controllers;

import edu.mateus.Gym.dtos.ExerciseDTO;
import edu.mateus.Gym.models.Exercise;
import edu.mateus.Gym.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExerciseController {

	@Autowired
	ExerciseService exerciseService;

	@GetMapping("/exercise")
	public ResponseEntity<List<Exercise>> getExercises(){

		return null;
	}

	@PostMapping("/exercise")
	public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise){
		return new ResponseEntity<>(exerciseService.createExercise(exercise), HttpStatus.CREATED);
	}
}
