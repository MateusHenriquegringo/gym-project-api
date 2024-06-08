package edu.mateus.Gym.Exercises.services;

import edu.mateus.Gym.Exercises.exceptions.ResourceNotFoundException;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ExerciseGetByIdAndDeleteService {


	private final ExerciseRepository exerciseRepository;


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

