package edu.mateus.Gym.Exercises.services;

import edu.mateus.Gym.Exercises.dtos.response.ExerciseDTO;
import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExerciseServiceTest {

	@Mock
	ExerciseService exerciseService;

	@InjectMocks
	ExerciseRepository exerciseRepository;


	@Test
	void createExercise_ThrowsException() {

		ExerciseDTO exerciseDTO = new ExerciseDTO("supino teste", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
		                                          Arrays.asList(MuscleGroupsEnum.BICEPS, MuscleGroupsEnum.ANTEBRACO),
		                                          ExerciseDifficulty.INICIANTE);


		ExerciseDTO exerciseDTO2 = new ExerciseDTO("supino teste", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
		                                           Arrays.asList(MuscleGroupsEnum.BICEPS, MuscleGroupsEnum.ANTEBRACO),
		                                           ExerciseDifficulty.INICIANTE);

		ExerciseModel expectedExerciseModel = new ExerciseModel();
		ExerciseModel expectedExerciseModel2 = new ExerciseModel();
		BeanUtils.copyProperties(exerciseDTO, expectedExerciseModel);
		BeanUtils.copyProperties(exerciseDTO2, expectedExerciseModel2);

		exerciseService.createExercise(exerciseDTO);

		assertThrows(DataIntegrityViolationException.class, () -> exerciseService.createExercise(exerciseDTO2));

	}


	@Test
	void editExercise() {

	}


	@Test
	void deleteExercise() {

	}

}