package edu.mateus.Gym.Exercises.services;

import edu.mateus.Gym.Exercises.controllers.ExerciseController;
import edu.mateus.Gym.Exercises.dtos.ExerciseDTO;
import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.exceptions.ResourceNotFoundException;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class ExerciseService {

	@Autowired
	private ExerciseRepository exerciseRepository;


	public List<ExerciseModel> getAllExercises() {
		return exerciseRepository.findAll();
	}


	public List<ExerciseModel> getExercisesByDifficulty(ExerciseDifficulty difficulty) {
		return exerciseRepository.findAllByDifficulty(difficulty);
	}


	public ExerciseModel getExerciseById(Long id) {
		return exerciseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("exercise was not found"));
	}


	@Transactional
	public ExerciseModel createExercise(ExerciseDTO exerciseDTO) {

		ExerciseModel exerciseModel = new ExerciseModel();
		BeanUtils.copyProperties(exerciseDTO, exerciseModel);

		if (exerciseRepository.existsByName(exerciseModel.getName())) {
			throw new DataIntegrityViolationException("this exercise already exists");
		} else {
			return exerciseRepository.save(exerciseModel);
		}
	}



	@Transactional
	public ExerciseModel editExercise(ExerciseDTO exerciseDTO, Long id) {

		ExerciseModel exerciseModel = new ExerciseModel();
		exerciseModel.setId(id);
		BeanUtils.copyProperties(exerciseDTO, exerciseModel);


		if (exerciseRepository.existsById(exerciseModel.getId())) {
			if (exerciseRepository.existsByName(exerciseModel.getName())) {
				throw new DataIntegrityViolationException("this exercise already exists");
			}
			return exerciseRepository.save(exerciseModel);
		} else {
			throw new ResourceNotFoundException("didn't edited, exercise was not found");
		}
	}



	public void deleteExercise(Long id) {

		if (exerciseRepository.existsById(id)) {
			exerciseRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("didn't deleted, exercise was not found");
		}
	}


}

