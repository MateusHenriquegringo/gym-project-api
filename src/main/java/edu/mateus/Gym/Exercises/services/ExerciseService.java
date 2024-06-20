package edu.mateus.Gym.Exercises.services;

import edu.mateus.Gym.Exercises.dtos.ExerciseDTO;
import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.exceptions.ResourceNotFoundException;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import edu.mateus.Gym.Exercises.repositorys.specification.ExerciseSpecs;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExerciseService {

	@Autowired
	private ExerciseRepository exerciseRepository;


	public List<ExerciseModel> getAllExercises(ExerciseDifficulty difficulty, List<MuscleGroupsEnum> muscles) {

		Specification<ExerciseModel> spec = Specification
				.where(ExerciseSpecs.hasAllMuscleGroups(muscles))
				.and(ExerciseSpecs.hasDifficulty(difficulty));

		return exerciseRepository.findAll(spec);
	}


	public ExerciseModel getExerciseById(Long id) {

		return exerciseRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}


	@Transactional
	public ExerciseModel editExercise(@Valid ExerciseDTO exerciseDTO, Long id) {

		ExerciseModel exerciseToEdit = exerciseRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

		if (exerciseRepository.existsByName(exerciseDTO.name())) {
			throw new DataIntegrityViolationException("exercise with the given name already exists");
		}

		BeanUtils.copyProperties(exerciseDTO, exerciseToEdit);
		return exerciseRepository.save(exerciseToEdit);

	}


	@Transactional
	public ExerciseModel createExercise(@Valid ExerciseDTO exerciseDTO) {

		if (exerciseRepository.existsByName(exerciseDTO.name())) {
			throw new DataIntegrityViolationException("this exercise already exists");
		}

		var exerciseToSave = new ExerciseModel();

		BeanUtils.copyProperties(exerciseDTO, exerciseToSave);

		return exerciseRepository.save(exerciseToSave);

	}


	public void deleteExercise(Long id) {

		if (exerciseRepository.existsById(id)) {
			exerciseRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException();
		}
	}

}

