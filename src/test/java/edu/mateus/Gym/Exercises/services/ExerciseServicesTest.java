package edu.mateus.Gym.Exercises.services;


import edu.mateus.Gym.Exercises.dtos.ExerciseDTO;
import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.exceptions.ResourceNotFoundException;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
public class ExerciseServicesTest {

	private final List<ExerciseModel> exercises = List.of(ExerciseModel.builder()
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

	@InjectMocks
	private ExerciseService service;

	@Mock
	private ExerciseRepository repository;

	@Captor
	private ArgumentCaptor<Long> longArgumentCaptor;

	@Captor
	private ArgumentCaptor<String> nameCaptor;

	@Captor
	private ArgumentCaptor<Specification<ExerciseModel>> specificationCaptor;


	@BeforeEach
	void setUp() {

		MockitoAnnotations.openMocks(this);
	}


	@Nested
	class getExerciseById {

		@Test
		@DisplayName("should return exercise by id")
		void testGetExerciseByIdWhenExerciseExists() {

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
		public void testGetAllExercisesWithNonNullDifficultyAndMuscles() {

			ExerciseDifficulty difficulty = ExerciseDifficulty.INTERMEDIARIO;
			List<MuscleGroupsEnum> muscles = Arrays.asList(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.DORSAL);


			when(repository.findAll(specificationCaptor.capture())).thenReturn(Collections.emptyList());

			service.getAllExercises(difficulty, muscles);


			verify(repository, times(1)).findAll(specificationCaptor.capture());

			Specification<ExerciseModel> capturedSpec = specificationCaptor.getValue();
			assertNotNull(capturedSpec);

		}


		@Test
		public void testGetAllExercisesWithNullDifficulty() {

			List<MuscleGroupsEnum> muscles = Arrays.asList(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.DORSAL);

			when(repository.findAll(specificationCaptor.capture())).thenReturn(Collections.emptyList());

			service.getAllExercises(null, muscles);

			verify(repository, times(1)).findAll(specificationCaptor.capture());
		}


		@Test
		public void testGetAllExercisesWithEmptyMuscleGroups() {

			ExerciseDifficulty difficulty = ExerciseDifficulty.INTERMEDIARIO;
			List<MuscleGroupsEnum> muscles = Collections.emptyList();

			when(repository.findAll(specificationCaptor.capture())).thenReturn(Collections.emptyList());

			service.getAllExercises(difficulty, muscles);

			verify(repository, times(1)).findAll(specificationCaptor.capture());
		}


		@Test
		public void testGetAllExercisesWithNullMuscleGroups() {

			ExerciseDifficulty difficulty = ExerciseDifficulty.INTERMEDIARIO;

			when(repository.findAll(specificationCaptor.capture())).thenReturn(Collections.emptyList());

			service.getAllExercises(difficulty, null);

			verify(repository, times(1)).findAll(specificationCaptor.capture());
		}

	}

	@Nested
	class editExercise {


		@Test
		@DisplayName("Should throws an exception if exercise to edit is not present")
		void testEditExerciseShouldThrowsIfIdToEditIsNotPresent() {

			Long nonExistentId = 90L;

			ExerciseDTO exerciseToEdit = ExerciseDTO.builder()
					.name("exercise three")
					.type(ExerciseType.CARDIO)
					.intensity(ExerciseIntensity.MEDIA)
					.muscles(List.of(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.ANTEBRACO))
					.difficulty(ExerciseDifficulty.EXPERIENTE)
					.build();


			when(repository.findById(nonExistentId)).thenThrow(ResourceNotFoundException.class);


			assertThrowsExactly(ResourceNotFoundException.class,
			                    () -> service.editExercise(exerciseToEdit, nonExistentId));
			verify(repository, times(1)).findById(nonExistentId);
			verify(repository, never()).save(any());
		}


		@Test
		@DisplayName("Should throws an exception if exercise edited contains an name there is already present")
		void testEditExerciseShouldThrowsIfNameIsAlreadyPresent() {

			ExerciseDTO exerciseToEdit = ExerciseDTO.builder()
					.name("exercise three")
					.type(ExerciseType.CARDIO)
					.intensity(ExerciseIntensity.MEDIA)
					.muscles(List.of(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.ANTEBRACO))
					.difficulty(ExerciseDifficulty.EXPERIENTE)
					.build();


			when(repository.existsByName(nameCaptor.capture())).thenThrow(DataIntegrityViolationException.class);

			when(repository.findById(any(Long.class))).thenReturn(Optional.of(exercises.get(0)));

			assertThrowsExactly(DataIntegrityViolationException.class,
			                    () -> service.editExercise(exerciseToEdit, any(Long.class)));


			verify(repository, never()).save(any());
			verify(repository, times(1)).existsByName(nameCaptor.getValue());
			assertEquals(exerciseToEdit.name(), nameCaptor.getValue());
		}


		@Test
		@DisplayName("Should edit correctly")
		void testEditExerciseShouldEditCorrectly() {

			var exerciseToEdit = ExerciseModel.builder()
					.id(1L)
					.name("exercise three")
					.type(ExerciseType.CARDIO)
					.intensity(ExerciseIntensity.MEDIA)
					.muscles(List.of(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.ANTEBRACO))
					.difficulty(ExerciseDifficulty.EXPERIENTE)
					.build();

			var exerciseRequest = ExerciseDTO.builder()
					.name("exercise three")
					.type(ExerciseType.CARDIO)
					.intensity(ExerciseIntensity.MEDIA)
					.muscles(List.of(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.ANTEBRACO))
					.difficulty(ExerciseDifficulty.EXPERIENTE)
					.build();


			when(repository.existsByName(nameCaptor.capture())).thenReturn(false);

			when(repository.findById(any(Long.class))).thenReturn(Optional.of(exerciseToEdit));

			assertDoesNotThrow(() -> service.editExercise(exerciseRequest, any(Long.class)));

			assertEquals(exerciseRequest.name(), nameCaptor.getValue());
			verify(repository, times(1)).save(
					argThat(updated -> updated.getName().equals(exerciseRequest.name()) && updated.getType()
							.equals(exerciseRequest.type())));
			verify(repository, times(1)).existsByName(nameCaptor.getValue());

		}

	}

	@Nested
	class createExercise {

		@Test
		@DisplayName("should not create an exercise when name already exists")
		void testCreateExerciseThrowsExceptionWhenNameAlreadyExists() {

			var exerciseRequestToCreate = ExerciseDTO.builder()
					.name("exercise three")
					.type(ExerciseType.CARDIO)
					.intensity(ExerciseIntensity.MEDIA)
					.muscles(List.of(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.ANTEBRACO))
					.difficulty(ExerciseDifficulty.EXPERIENTE)
					.build();


			when(repository.existsByName(nameCaptor.capture())).thenThrow(DataIntegrityViolationException.class);

			assertThrowsExactly(DataIntegrityViolationException.class,
			                    () -> service.createExercise(exerciseRequestToCreate));

			verify(repository, never()).save(any());
			verify(repository, times(1)).existsByName(nameCaptor.capture());
			assertEquals(exerciseRequestToCreate.name(), nameCaptor.getValue());
		}


		@Test
		@DisplayName("should create an exercise correctly")
		void testCreateExerciseCreateAnExerciseSuccessfully() {

			var exerciseRequestToCreate = ExerciseDTO.builder()
					.name("exercise three")
					.type(ExerciseType.CARDIO)
					.intensity(ExerciseIntensity.MEDIA)
					.muscles(List.of(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.ANTEBRACO))
					.difficulty(ExerciseDifficulty.EXPERIENTE)
					.build();

			when(repository.existsByName(exerciseRequestToCreate.name())).thenReturn(false);

			assertDoesNotThrow(() -> service.createExercise(exerciseRequestToCreate));

			verify(repository, times(1)).existsByName(any());
			verify(repository, times(1)).save(
					argThat(created -> created.getName().equals(exerciseRequestToCreate.name()) && created.getType()
							.equals(exerciseRequestToCreate.type()) && created.getMuscles()
							.equals(exerciseRequestToCreate.muscles())));
		}

	}

	@Nested
	class deleteExercise {

		@Test
		@DisplayName("should not delete if id dont exists")
		void testDeleteExerciseThrowsExceptionWhenIdDoesNotExists(){

			when(repository.existsById(longArgumentCaptor.capture())).thenReturn(false);

			assertThrowsExactly(ResourceNotFoundException.class, ()-> service.deleteExercise(90L));
			verify(repository, never()).deleteById(any());
		}

		@Test
		@DisplayName("should delete exercise id correctly")
		void testDeleteExerciseDeleteSuccessfully(){

			when(repository.existsById(longArgumentCaptor.capture())).thenReturn(true);

			assertDoesNotThrow(()-> service.deleteExercise(90L));

			verify(repository, times(1)).deleteById(longArgumentCaptor.capture());
			assertEquals(90L, longArgumentCaptor.getValue());
		}

	}


}
