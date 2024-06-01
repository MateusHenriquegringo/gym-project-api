package edu.mateus.Gym.Exercises.controllers;

import edu.mateus.Gym.Exercises.dtos.ExerciseDTO;
import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.services.ExerciseService;
import edu.mateus.Gym.Exercises.tools.ExercisesHateoas;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

	//private final ExerciseListingService exerciseListingService;
	@Autowired
	ExerciseService exerciseService;


	@GetMapping
	public ResponseEntity<List<ExerciseModel>> getAllExercises(
			@RequestParam(required = false) ExerciseDifficulty dificuldade) {

		if (ObjectUtils.isEmpty(dificuldade)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(ExercisesHateoas.exerciseToHateoas(exerciseService.getAllExercises()));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(ExercisesHateoas.exerciseToHateoas(exerciseService.getExercisesByDifficulty(dificuldade)));
		}

	}

//	@GetMapping
//	public ResponseEntity<PageDTO<ExerciseResponseDTO>> getAllByFilter(PageFilter pageFilter,
//																	   FilterRequestDTO filterRequestDTO){
//		return ResponseEntity.ok().body(exerciseListingService.getAllByFilter(pageFilter, filterRequestDTO));
//	}


	@PostMapping()
	public ResponseEntity<ExerciseModel> createExercise(@RequestBody @Valid ExerciseDTO exerciseDTO) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ExercisesHateoas.exerciseToHateoas(exerciseService.createExercise(exerciseDTO)));

	}


	@GetMapping("/{id}")
	public ResponseEntity<Object> getExerciseById(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(ExercisesHateoas.exerciseToHateoas(exerciseService.getExerciseById(id)));

	}


	@PutMapping("/{id}")
	public ResponseEntity<Object> editExercise(@Valid @RequestBody ExerciseDTO exerciseDTO, @PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(ExercisesHateoas.exerciseToHateoas(exerciseService.editExercise(exerciseDTO, id)));
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteExercise(@PathVariable Long id) {

		exerciseService.deleteExercise(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
