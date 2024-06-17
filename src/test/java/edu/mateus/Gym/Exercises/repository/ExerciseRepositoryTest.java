package edu.mateus.Gym.Exercises.repository;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
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

	@Autowired
	private ExerciseRepository exerciseRepository;

	private ExerciseModel exerciseModel;
	private ExerciseModel exerciseModel2;

	@BeforeEach
	public void setUp() {
		exerciseModel = new ExerciseModel("exercisio teste", ExerciseIntensity.MEDIA,
		                                  ExerciseType.CARDIO, List.of(MuscleGroupsEnum.ANTEBRACO),
		                                  ExerciseDifficulty.INICIANTE);


		exerciseModel2 = new ExerciseModel("exercisio segundo", ExerciseIntensity.MEDIA,
		                                   ExerciseType.CARDIO, List.of(MuscleGroupsEnum.ANTEBRACO),
		                                   ExerciseDifficulty.INICIANTE);
	}

	@AfterEach
	public void clean(){
		exerciseRepository.deleteAll();
	}

	@Test
	public void checkExistsByName() {



		exerciseRepository.save(exerciseModel);
		exerciseRepository.save(exerciseModel2);

		assertTrue(exerciseRepository.existsByName(exerciseModel.getName()));
		assertTrue(exerciseRepository.existsByName(exerciseModel2.getName()));
		assertFalse(exerciseRepository.existsByName("nome nao existente"));

	}

}