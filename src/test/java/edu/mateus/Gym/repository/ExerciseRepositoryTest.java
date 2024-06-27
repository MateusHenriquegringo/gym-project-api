package edu.mateus.Gym.repository;

import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.enums.ExerciseIntensity;

import edu.mateus.Gym.enums.MuscleGroupsEnum;
import edu.mateus.Gym.models.ExerciseModel;
import edu.mateus.Gym.repositorys.ExerciseRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

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
				                        .intensity(ExerciseIntensity.MEDIUM)
				                        .muscles(Set.of(MuscleGroupsEnum.CHEST,
				                                        MuscleGroupsEnum.FOREARM))
				                        .difficulty(ExerciseDifficulty.EXPERIENCED)
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
