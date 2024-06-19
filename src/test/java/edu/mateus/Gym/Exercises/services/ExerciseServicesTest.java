package edu.mateus.Gym.Exercises.services;


import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.exceptions.ResourceNotFoundException;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import edu.mateus.Gym.Exercises.repositorys.specification.ExerciseSpecs;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ActiveProfiles("test")
public class ExerciseServicesTest {

	@InjectMocks
	private ExerciseService service;

	@Mock
	private ExerciseRepository repository;

	@Captor
	private ArgumentCaptor<Long> longArgumentCaptor;

	@Captor
	private ArgumentCaptor<Specification<ExerciseModel>> exerciseSpecification;

	private List<ExerciseModel> exercises = List.of(ExerciseModel.builder()
			                                                .name("exercise")
			                                                .type(ExerciseType.CARDIO)
			                                                .intensity(ExerciseIntensity.MEDIA)
			                                                .muscles(List.of(MuscleGroupsEnum.PEITORAL,
			                                                                 MuscleGroupsEnum.TRICEPS,
			                                                                 MuscleGroupsEnum.LOMBAR))
			                                                .difficulty(ExerciseDifficulty.INICIANTE)
			                                                .build(),

	                                                ExerciseModel.builder()
			                                                .name("exercise two")
			                                                .type(ExerciseType.CARDIO)
			                                                .intensity(ExerciseIntensity.MEDIA)
			                                                .muscles(List.of(MuscleGroupsEnum.PEITORAL,
			                                                                 MuscleGroupsEnum.TRICEPS))
			                                                .difficulty(ExerciseDifficulty.INICIANTE)
			                                                .build(),

	                                                ExerciseModel.builder()
			                                                .name("exercise three")
			                                                .type(ExerciseType.CARDIO)
			                                                .intensity(ExerciseIntensity.MEDIA)
			                                                .muscles(List.of(MuscleGroupsEnum.PEITORAL,
			                                                                 MuscleGroupsEnum.ANTEBRACO))
			                                                .difficulty(ExerciseDifficulty.EXPERIENTE)
			                                                .build());


	@BeforeEach
	void setUp() {

		MockitoAnnotations.openMocks(this);
	}


	@Nested()
	class getExerciseById {

		@Test
		@DisplayName("should return exercise by id")
		void getExerciseByIdWhenExerciseExists() {

			//arrange
			var exercise = ExerciseModel.builder()
					.id(90L)
					.name("teste")
					.type(ExerciseType.CARDIO)
					.intensity(ExerciseIntensity.MEDIA)
					.muscles(List.of(MuscleGroupsEnum.PEITORAL))
					.difficulty(ExerciseDifficulty.INICIANTE)
					.build();

			when(repository.findById(longArgumentCaptor.capture())).thenReturn(Optional.of(exercise));

			//act
			var output = service.getExerciseById(exercise.getId());

			//assert
			assertDoesNotThrow(() -> service.getExerciseById(exercise.getId()));
			assertEquals(exercise.getId(), longArgumentCaptor.getValue(), output.getId());
		}


		@Test
		@DisplayName("should throw ResourceNotFoundExeption when exercise does not exists")
		void getExerciseByIdWhenExerciseDoesNotExists() {

			var exerciseId = 90L;
			when(repository.findById(longArgumentCaptor.capture())).thenReturn(Optional.empty());

			assertThrowsExactly(ResourceNotFoundException.class, () -> service.getExerciseById(exerciseId));

		}

	}


	@Nested
	class getAllExercise {

		@Test
		@DisplayName("should return all exercises without filter correctly")
		void getAllExercisesWithoutFilter() {

		}


		@Test
		@DisplayName("should return filtered results by difficulty correctly")
		void getAllExercisesWithDifficultyFilterOnly() {

		}

//
//		@Test
//		@DisplayName("should return filtered results by muscles list correctly")
//		void getAllExercisesWithMusclesFilterOnly() {
//
//			List<MuscleGroupsEnum> muscles = List.of(MuscleGroupsEnum.TRICEPS, MuscleGroupsEnum.PEITORAL);
//
//
//			List<ExerciseModel> filteredExercisesByMuscles = exercises.stream()
//					.filter(exerciseModel -> muscles.containsAll(exerciseModel.getMuscles()))
//					.toList();
//
//
//			Specification<ExerciseModel> spec = Specification
//					.where(ExerciseSpecs.hasAllMuscleGroups(muscles));
//
//
//			when(repository.findAll(spec)).thenReturn(filteredExercisesByMuscles);
//
//
//			var output = service.getAllExercises(spec);
//
//			assertIterableEquals(output, filteredExercisesByMuscles);
//			output.forEach(exerciseModel -> assertTrue(exerciseModel.getMuscles().containsAll(muscles)));
//			verify(repository, times(1)).findAll(exerciseSpecification.capture());
//		}
//
//
//		@Test
//		@DisplayName("should return filtered results by muscles list correctly")
//		void getAllExercisesWithMusclesFilterAndDifficultyFilter() {
//
//			ExerciseDifficulty difficulty = ExerciseDifficulty.INICIANTE;
//			List<MuscleGroupsEnum> muscles = List.of(MuscleGroupsEnum.TRICEPS, MuscleGroupsEnum.PEITORAL);
//
//
//			List<ExerciseModel> filteredExercisesByMusclesAndDifficulty = exercises.stream()
//					.filter(exerciseModel -> muscles.containsAll(
//							exerciseModel.getMuscles()) && exerciseModel.getDifficulty().equals(difficulty))
//					.toList();
//
//
//			Specification<ExerciseModel> spec = Specification
//					.where(ExerciseSpecs.hasAllMuscleGroups(muscles))
//					.and(ExerciseSpecs.hasDifficulty(difficulty));
//
//
//			when(repository.findAll(spec)).thenReturn(filteredExercisesByMusclesAndDifficulty);
//
//
//			var output = service.getAllExercises(spec);
//
//			assertIterableEquals(output, filteredExercisesByMusclesAndDifficulty);
//			output.forEach(exerciseModel -> assertTrue(
//					exerciseModel.getMuscles().containsAll(muscles) && exerciseModel.getDifficulty()
//							.equals(difficulty)));
//
//			verify(repository, times(1)).findAll(exerciseSpecification.capture());
//		}

	}

}
