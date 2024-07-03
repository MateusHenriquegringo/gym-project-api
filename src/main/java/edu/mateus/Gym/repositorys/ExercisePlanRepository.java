package edu.mateus.Gym.repositorys;

import edu.mateus.Gym.models.ExercisePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExercisePlanRepository extends JpaRepository<ExercisePlan, Long> {

	List<ExercisePlan> findAllByFollowers_id(Long id);

}
