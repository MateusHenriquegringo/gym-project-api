package edu.mateus.Gym.Exercises.repositorys;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseModel, Long> , JpaSpecificationExecutor<ExerciseModel> {
	List<ExerciseModel> findAllByDifficulty(ExerciseDifficulty difficulty);

	boolean existsByName(String name);
}
