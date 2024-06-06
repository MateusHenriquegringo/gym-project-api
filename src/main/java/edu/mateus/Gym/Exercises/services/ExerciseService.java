package edu.mateus.Gym.Exercises.services;

import edu.mateus.Gym.Exercises.builder.model.ExerciseModelBuilder;
import edu.mateus.Gym.Exercises.dtos.ExerciseDTO;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.exceptions.ResourceNotFoundException;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
@RequiredArgsConstructor
public class ExerciseService {

	private final ExerciseRepository exerciseRepository;


	public List<ExerciseModel> getAllExercises() {
		return exerciseRepository.findAll();
	}


	public List<ExerciseModel> getExercisesByDifficulty(ExerciseDifficulty difficulty) {
		return exerciseRepository.findAllByDifficulty(difficulty);
	}


	public ExerciseModel getExerciseById(Long id) {
		return exerciseRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}


	public void deleteExercise(Long id) {

		if (exerciseRepository.existsById(id)) {
			exerciseRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException();
		}
	}


}

