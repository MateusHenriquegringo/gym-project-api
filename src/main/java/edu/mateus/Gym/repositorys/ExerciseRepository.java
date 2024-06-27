package edu.mateus.Gym.repositorys;

import edu.mateus.Gym.models.ExerciseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseModel, Long> , JpaSpecificationExecutor<ExerciseModel> {

	boolean existsByName(String name);

}
