package edu.mateus.Gym.services;

import edu.mateus.Gym.dtos.ExerciseDTO;
import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.enums.MuscleGroupsEnum;
import edu.mateus.Gym.exceptions.ResourceNotFoundException;
import edu.mateus.Gym.models.ExerciseModel;
import edu.mateus.Gym.repositorys.ExerciseRepository;
import edu.mateus.Gym.repositorys.specification.ExerciseSpecs;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class ExerciseService  {

	@Autowired
	private ExerciseRepository exerciseRepository;


	public List<ExerciseModel> getAllExercises(ExerciseDifficulty difficulty, Set<MuscleGroupsEnum> muscles) {

		Specification<ExerciseModel> spec = Specification
				.where(ExerciseSpecs.hasAllMuscleGroups(muscles))
				.and(ExerciseSpecs.hasDifficulty(difficulty));

		return exerciseRepository.findAll(spec);
	}


	public ExerciseModel getExerciseById(Long id) {

		return exerciseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("exercise not found"));
	}


	@Transactional
	public ExerciseModel editExercise(@Valid ExerciseDTO exerciseDTO, Long id) {

		ExerciseModel exerciseToEdit = this.getExerciseById(id);

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
			throw new ResourceNotFoundException("exercise not found");
		}
	}

}

