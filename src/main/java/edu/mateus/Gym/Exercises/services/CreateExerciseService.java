package edu.mateus.Gym.Exercises.services;

import edu.mateus.Gym.Exercises.dtos.ExerciseDTO;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateExerciseService {

	private final ExerciseRepository exerciseRepository;


	@Transactional
	public ExerciseModel createExercise(@Valid ExerciseDTO exerciseDTO) {

		if (exerciseRepository.existsByName(exerciseDTO.name())) {
			throw new DataIntegrityViolationException("this exercise already exists");
		}

		var exerciseToSave = new ExerciseModel();

		BeanUtils.copyProperties(exerciseDTO, exerciseToSave);

		return exerciseRepository.save(exerciseToSave);

	}

}
