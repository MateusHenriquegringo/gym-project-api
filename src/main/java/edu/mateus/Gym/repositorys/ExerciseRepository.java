package edu.mateus.Gym.repositorys;

import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
	List<Exercise> findAllByDifficulty(ExerciseDifficulty difficulty);
}