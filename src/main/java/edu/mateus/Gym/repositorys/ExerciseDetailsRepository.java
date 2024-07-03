package edu.mateus.Gym.repositorys;


import edu.mateus.Gym.models.ExerciseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseDetailsRepository extends JpaRepository<ExerciseDetails, UUID> {
}
