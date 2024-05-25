package edu.mateus.Gym.controllers;

import edu.mateus.Gym.dtos.ExerciseDTO;
import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.models.Exercise;
import edu.mateus.Gym.services.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/exercises")
public class ExerciseController {

	@Autowired
	ExerciseService exerciseService;


	@GetMapping()
	public ResponseEntity<List<Exercise>> getExercises() {

		return ResponseEntity.status(HttpStatus.OK).body(exerciseService.getAllExercises());
	}


	@PostMapping()
	public ResponseEntity<Exercise> createExercise(@RequestBody @Valid ExerciseDTO exerciseDTO) {

		return ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.createExercise(exerciseDTO));

	}


	@GetMapping("/{id}")
	public ResponseEntity<Object> getExerciseById(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(exerciseService.getExerciseById(id));

	}

	@GetMapping("/difficulty")
	public ResponseEntity<Object> getExercisesByDifficulty(@RequestParam ExerciseDifficulty difficulty){
		return ResponseEntity.status(HttpStatus.OK).body(exerciseService.getExercisesByDifficulty(difficulty));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> editExercise(@Valid @RequestBody ExerciseDTO exerciseDTO, @PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(exerciseService.editExercise(exerciseDTO, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteExercise(@PathVariable Long id){
		exerciseService.deleteExercise(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
