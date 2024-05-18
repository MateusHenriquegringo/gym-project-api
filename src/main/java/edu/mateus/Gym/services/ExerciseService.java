package edu.mateus.Gym.services;

import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.exceptions.ResourceNotFoundException;
import edu.mateus.Gym.models.Exercise;
import edu.mateus.Gym.repositorys.ExerciseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
	@Autowired
	private ExerciseRepository exerciseRepository;

	public List<Exercise> getAllExercices() {
		return exerciseRepository.findAll();
	}

	public Exercise getExerciseById(Long id) {
		return exerciseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("exercise not found"));
	}

	@Transactional
	public Exercise createExercise(Exercise exerciseDTO){
		return exerciseRepository.save(exerciseDTO);
	}

	@Transactional
	public Exercise updateExercise(Exercise exercise) {
		Exercise ex = this.getExerciseById(exercise.getId());
		ex.setType(exercise.getType());
		ex.setName(exercise.getName());
		ex.setMuscles(exercise.getMuscles());
		ex.setDifficulty(exercise.getDifficulty());
		return exerciseRepository.save(ex);
	}

	public void deleteExercise(Long id) {
		if(exerciseRepository.existsById(id)){
		exerciseRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("don't deleted, exercise was not found");
		}
	}

	public List<Exercise> getExercisesByDifficulty(ExerciseDifficulty difficulty){
		return exerciseRepository.findAllByDifficulty(difficulty);
	}
}

