package edu.mateus.Gym.repositorys.specification;

import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.enums.MuscleGroupsEnum;
import edu.mateus.Gym.models.ExerciseModel;
import edu.mateus.Gym.models.ExerciseModel_;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ExerciseSpecs {

	public static Specification<ExerciseModel> hasDifficulty(ExerciseDifficulty difficulty) {

		return ((root, query, criteriaBuilder) -> {
			if (difficulty == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.equal(root.get(ExerciseModel_.difficulty), difficulty);
		});

	}


	public static Specification<ExerciseModel> hasAllMuscleGroups(Set<MuscleGroupsEnum> muscles) {

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
