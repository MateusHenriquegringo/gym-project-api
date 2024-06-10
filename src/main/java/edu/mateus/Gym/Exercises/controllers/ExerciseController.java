package edu.mateus.Gym.Exercises.controllers;

import edu.mateus.Gym.Exercises.dtos.ExerciseDTO;
import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.services.CreateExerciseService;
import edu.mateus.Gym.Exercises.services.EditExerciseService;
import edu.mateus.Gym.Exercises.services.ExerciseGetByIdAndDeleteService;
import edu.mateus.Gym.Exercises.services.GetAllExercisesService;
import edu.mateus.Gym.Exercises.services.assembler.ExerciseAssembler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

	private final ExerciseGetByIdAndDeleteService exerciseGetByIdAndDeleteService;

	private final EditExerciseService editExerciseService;

	private final GetAllExercisesService getAllExercisesService;

	private final CreateExerciseService createExerciseService;

	@Autowired
	ExerciseAssembler assembler;


	@GetMapping
	public ResponseEntity<CollectionModel<ExerciseModel>> getAllExercises(
			@RequestParam(required = false) ExerciseDifficulty difficulty,
			@RequestParam(required = false) List<MuscleGroupsEnum> muscles
	) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(assembler.toCollectionModel(getAllExercisesService.getAllExercises(difficulty, muscles)));


	}


	@PostMapping
	public ResponseEntity<ExerciseModel> createExercise(@RequestBody @Valid ExerciseDTO exerciseDTO) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(assembler.toModel(createExerciseService.createExercise(exerciseDTO)));
	}


	@GetMapping("/{id}")
	public ResponseEntity<Object> getExerciseById(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(assembler.toModel(exerciseGetByIdAndDeleteService.getExerciseById(id)));

	}


	@PutMapping("/{id}")
	public ResponseEntity<Object> editExercise(@Valid @RequestBody ExerciseDTO exerciseDTO, @PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(assembler.toModel(editExerciseService.editExercise(exerciseDTO, id)));
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteExercise(@PathVariable Long id) {

		exerciseGetByIdAndDeleteService.deleteExercise(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

//	@GetMapping
//	public ResponseEntity<PageDTO<ExerciseResponseDTO>> getAllByFilter(PageFilter pageFilter,
//																	   FilterRequestDTO filterRequestDTO){
//		return ResponseEntity.ok().body(exerciseListingService.getAllByFilter(pageFilter, filterRequestDTO));
//	}


}

