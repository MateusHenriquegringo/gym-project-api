package edu.mateus.Gym.Exercises.repositorys.specification;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.models.ExerciseModel_;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ExerciseSpecs {

	public static Specification<ExerciseModel> hasDifficulty(ExerciseDifficulty difficulty) {

		return ((root, query, criteriaBuilder) -> {
			if (difficulty == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.equal(root.get(ExerciseModel_.difficulty), difficulty);
		});

	}


	public static Specification<ExerciseModel> containsAllMuscleGroups(List<MuscleGroupsEnum> muscles) {

		return (((root, query, criteriaBuilder) -> {
			if (muscles == null || muscles.isEmpty()) {
				return criteriaBuilder.conjunction();
			}

			List<Predicate> predicates = new ArrayList<>();

			muscles.forEach(
					muscle -> predicates.add(criteriaBuilder.isMember(muscle, root.get(ExerciseModel_.muscles))));

			return criteriaBuilder.and(predicates.toArray(Predicate[]::new));

		}));
	}

}
