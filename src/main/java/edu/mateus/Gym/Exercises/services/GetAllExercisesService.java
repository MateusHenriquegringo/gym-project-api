package edu.mateus.Gym.Exercises.services;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import edu.mateus.Gym.Exercises.repositorys.specification.ExerciseSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllExercisesService {

	private final ExerciseRepository exerciseRepository;

	public List<ExerciseModel> getAllExercises(ExerciseDifficulty difficulty, List<MuscleGroupsEnum> muscles) {

		Specification<ExerciseModel> spec = Specification
				.where(ExerciseSpecs.containsAllMuscleGroups(muscles))
				.and(Specification
						     .where(ExerciseSpecs.hasDifficulty(difficulty)));

		return exerciseRepository.findAll(spec);
	}
}
