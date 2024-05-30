package edu.mateus.Gym.Exercises.services;

import edu.mateus.Gym.Exercises.builder.model.ExerciseModelBuilder;
import edu.mateus.Gym.Exercises.dtos.ExerciseDTO;
import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.exceptions.ResourceNotFoundException;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


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
		ExerciseModel byId = getExerciseById(id);

		if(byId.getName().equals(exerciseDTO.name())){
			throw new DataIntegrityViolationException("this exercise already exists");
		}

		ExerciseModel model = ExerciseModelBuilder.buildModel(byId, exerciseDTO);
			return exerciseRepository.save(model);
	}



	public void deleteExercise(Long id) {

		if (exerciseRepository.existsById(id)) {
			exerciseRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException();
		}
	}


}

