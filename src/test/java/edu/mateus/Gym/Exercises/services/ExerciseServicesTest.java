package edu.mateus.Gym.Exercises.services;


import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.exceptions.ResourceNotFoundException;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import edu.mateus.Gym.Exercises.repositorys.specification.ExerciseSpecs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
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


	@BeforeEach
	void setUp() {

		MockitoAnnotations.openMocks(this);
	}


	@Nested()
	class getExerciseById {

		@Test
		@DisplayName(
				"should return exercise by id")
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
		@DisplayName(
				"should throw ResourceNotFoundExeption when exercise does not exists")
		void getExerciseByIdWhenExerciseDoesNotExists() {

			//arrange
			var exerciseId = 90L;
			when(repository.findById(longArgumentCaptor.capture())).thenReturn(Optional.empty());

			//act
			//assert
			assertThrowsExactly(ResourceNotFoundException.class, () -> service.getExerciseById(exerciseId));

		}

	}


	@Nested
	class getAllExercise {

		@Test
		@DisplayName("should return all exercises without filter correctly")
		void getAllExercisesWithoutFilter() {

			List<ExerciseModel> exercises = List.of(
					ExerciseModel.builder()
							.id(90L)
							.name("exercise")
							.type(ExerciseType.CARDIO)
							.intensity(ExerciseIntensity.MEDIA)
							.muscles(List.of(MuscleGroupsEnum.PEITORAL))
							.difficulty(ExerciseDifficulty.INICIANTE)
							.build(),

					ExerciseModel.builder()
							.id(80L)
							.name("exercise two")
							.type(ExerciseType.CARDIO)
							.intensity(ExerciseIntensity.MEDIA)
							.muscles(List.of(MuscleGroupsEnum.PEITORAL))
							.difficulty(ExerciseDifficulty.INICIANTE)
							.build()
			);

			mockStatic(ExerciseSpecs.class);
			when(ExerciseSpecs.hasDifficulty(null)).thenReturn(
					(root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
			when(ExerciseSpecs.hasAllMuscleGroups(null)).thenReturn(
					(root, query, criteriaBuilder) -> criteriaBuilder.conjunction());


			when(repository.findAll(exerciseSpecification.capture())).thenReturn(exercises);

			var output = service.getAllExercises(null, null);

			assertNotNull(output);
			assertEquals(output.size(), exercises.size());
			verify(repository, times(1)).findAll(exerciseSpecification.capture());
		}


		@Test
		@DisplayName(
				"should return filtered results by difficulty correctly")
		void getAllExercisesWithDifficultyFilterOnly() {

			//arrange
			//act
			//assert

		}


		@Test
		@DisplayName(
				"getAllService is returning the filtered results by list of muscles and difficulty==null correctly")
		void testGetAllExercise_WithMusclesFilterOnly() {

		}


		@Test
		@DisplayName(
				"getAllService is returning the filtered results by list of muscles and difficulty null correctly")
		void testGetAllExercise_WithMusclesFilterAndDifficultyFilter() {

		}

	}


}
