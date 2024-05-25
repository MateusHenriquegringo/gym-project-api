package edu.mateus.Gym.Exercises.controllers;

import edu.mateus.Gym.Exercises.dtos.ExerciseDTO;
import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.services.ExerciseService;
import edu.mateus.Gym.Exercises.tools.ExercisesHateoas;
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
	public ResponseEntity<List<ExerciseModel>> getAllExercises(
			@RequestParam(required = false) ExerciseDifficulty difficulty) {

		if (difficulty == null) {
			return ResponseEntity.status(HttpStatus.OK).body(ExercisesHateoas.exerciseToHateoas(exerciseService.getAllExercises()));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(ExercisesHateoas.exerciseToHateoas(exerciseService.getExercisesByDifficulty(difficulty)));
		}

	}


	@PostMapping()
	public ResponseEntity<ExerciseModel> createExercise(@RequestBody @Valid ExerciseDTO exerciseDTO) {

		return ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.createExercise(exerciseDTO));

	}


	@GetMapping("/{id}")
	public ResponseEntity<Object> getExerciseById(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(ExercisesHateoas.exerciseToHateoas(exerciseService.getExerciseById(id)));

	}


	@PutMapping("/{id}")
	public ResponseEntity<Object> editExercise(@Valid @RequestBody ExerciseDTO exerciseDTO, @PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(exerciseService.editExercise(exerciseDTO, id));
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteExercise(@PathVariable Long id) {

		exerciseService.deleteExercise(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
