package edu.mateus.Gym.Exercises.services;

import edu.mateus.Gym.Exercises.dtos.ExerciseDTO;
import edu.mateus.Gym.Exercises.exceptions.ResourceNotFoundException;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditExerciseService {

	private final ExerciseRepository repository;


	@Transactional
	public ExerciseModel editExercise(@Valid ExerciseDTO exerciseDTO, Long id) {

		ExerciseModel exerciseToEdit = repository.findById(id).orElseThrow(ResourceNotFoundException::new);

		if (repository.existsByName(exerciseDTO.name())) {
			throw new DataIntegrityViolationException("exercise with the given name already exists");
		}

		BeanUtils.copyProperties(exerciseDTO, exerciseToEdit);
		return repository.save(exerciseToEdit);

	}

}