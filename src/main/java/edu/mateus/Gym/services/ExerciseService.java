package edu.mateus.Gym.services;

import edu.mateus.Gym.dtos.ExerciseDTO;
import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.exceptions.ResourceNotFoundException;
import edu.mateus.Gym.models.Exercise;
import edu.mateus.Gym.repositorys.ExerciseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

	@Autowired
	private ExerciseRepository exerciseRepository;


	public List<Exercise> getAllExercises() {

		return exerciseRepository.findAll();
	}


	public Exercise getExerciseById(Long id) {

		return exerciseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("exercise was not found"));
	}


	@Transactional
	public Exercise createExercise(ExerciseDTO exerciseDTO) {

		Exercise exercise = new Exercise();
		BeanUtils.copyProperties(exerciseDTO, exercise);

		if (exerciseRepository.existsByName(exercise.getName())) {
			throw new DataIntegrityViolationException("this exercise already exists");
		} else {
			return exerciseRepository.save(exercise);
		}
	}


	@Transactional
	public Exercise editExercise(ExerciseDTO exerciseDTO, Long id) {
		Exercise exercise = new Exercise();
		exercise.setId(id);
		BeanUtils.copyProperties(exerciseDTO, exercise);


		if (exerciseRepository.existsById(exercise.getId())) {
			if (exerciseRepository.existsByName(exercise.getName())) {
				throw new DataIntegrityViolationException("this exercise already exists");
			}
			return exerciseRepository.save(exercise);
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


	public List<Exercise> getExercisesByDifficulty(ExerciseDifficulty difficulty) {

		return exerciseRepository.findAllByDifficulty(difficulty);
	}

}

