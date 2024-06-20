package edu.mateus.Gym.Exercises.repository;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest(showSql = true)
@ActiveProfiles("test")
public class ExerciseRepositoryTest {

	@Resource
	private ExerciseRepository exerciseRepository;

	@BeforeEach
	public void setUp() {
		exerciseRepository.save(ExerciseModel.builder()
				                        .name("exercise one")
				                        .type(ExerciseType.CARDIO)
				                        .intensity(ExerciseIntensity.MEDIA)
				                        .muscles(List.of(MuscleGroupsEnum.PEITORAL,
				                                         MuscleGroupsEnum.ANTEBRACO))
				                        .difficulty(ExerciseDifficulty.EXPERIENTE)
				                        .build());
	}
	@AfterEach
	public void clean(){
		exerciseRepository.deleteAll();
	}

	@Test
	public void checkExistsByName() {
		var output = exerciseRepository.existsByName("exercise one");
		var falseOutput = exerciseRepository.existsByName("exercise non existent");
		assertTrue(output);
		assertFalse(falseOutput);
	}

}
