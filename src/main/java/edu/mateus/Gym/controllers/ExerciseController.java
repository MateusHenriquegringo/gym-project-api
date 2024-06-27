package edu.mateus.Gym.controllers;

import edu.mateus.Gym.dtos.ExerciseDTO;
import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.enums.MuscleGroupsEnum;
import edu.mateus.Gym.models.ExerciseModel;

import edu.mateus.Gym.services.ExerciseService;
import edu.mateus.Gym.controllers.assembler.ExerciseAssembler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

	@Autowired
	ExerciseService exerciseService;

	@Autowired
	ExerciseAssembler assembler;


	@GetMapping
	public ResponseEntity<CollectionModel<ExerciseModel>> getAllExercises (
			@Valid @RequestParam(required = false) ExerciseDifficulty difficulty,
			@Valid @RequestParam(required = false) Set<MuscleGroupsEnum> muscles
	) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(assembler.toCollectionModel(exerciseService.getAllExercises(difficulty, muscles)));

	}


	@PostMapping
	public ResponseEntity<ExerciseModel> createExercise(@RequestBody @Valid ExerciseDTO exerciseDTO) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(assembler.toModel(exerciseService.createExercise(exerciseDTO)));
	}


	@GetMapping("/{id}")
	public ResponseEntity<Object> getExerciseById(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(assembler.toModel(exerciseService.getExerciseById(id)));

	}


	@PutMapping("/{id}")
	public ResponseEntity<Object> editExercise(@Valid @RequestBody ExerciseDTO exerciseDTO, @PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(assembler.toModel(exerciseService.editExercise(exerciseDTO, id)));
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteExercise(@PathVariable Long id) {

		exerciseService.deleteExercise(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}

