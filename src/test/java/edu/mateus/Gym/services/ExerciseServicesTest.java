package edu.mateus.Gym.services;


import edu.mateus.Gym.dtos.ExerciseDTO;
import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.enums.ExerciseIntensity;

import edu.mateus.Gym.enums.MuscleGroupsEnum;
import edu.mateus.Gym.exceptions.ResourceNotFoundException;
import edu.mateus.Gym.models.ExerciseModel;
import edu.mateus.Gym.repositorys.ExerciseRepository;
import edu.mateus.Gym.services.ExerciseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
public class ExerciseServicesTest {

	private final List<ExerciseModel> exercises = List.of(ExerciseModel.builder()
			                                                      .name("exercise")
			                                                      .intensity(ExerciseIntensity.MEDIUM)
			                                                      .muscles(Set.of(MuscleGroupsEnum.CHEST,
			                                                                           MuscleGroupsEnum.TRICEPS,
			                                                                           MuscleGroupsEnum.BACK))
			                                                      .difficulty(ExerciseDifficulty.BEGINNER)
			                                                      .build(),

	                                                      ExerciseModel.builder()
			                                                      .name("exercise two")
			                                                      .intensity(ExerciseIntensity.MEDIUM)
			                                                      .muscles(Set.of(MuscleGroupsEnum.CHEST,
			                                                                       MuscleGroupsEnum.TRICEPS))
			                                                      .difficulty(ExerciseDifficulty.BEGINNER)
			                                                      .build(),

	                                                      ExerciseModel.builder()
			                                                      .name("exercise three")
			                                                      .intensity(ExerciseIntensity.MEDIUM)
			                                                      .muscles(Set.of(MuscleGroupsEnum.CHEST,
			                                                                       MuscleGroupsEnum.FOREARM))
			                                                      .difficulty(ExerciseDifficulty.EXPERIENCED)
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



			when(repository.findById(longArgumentCaptor.capture())).thenReturn(Optional.of(exercises.get(0)));

			//act
			var output = service.getExerciseById(exercises.get(0).getId());

			//assert
			assertDoesNotThrow(() -> service.getExerciseById(exercises.get(0).getId()));
			assertEquals(exercises.get(0).getId(), longArgumentCaptor.getValue(), output.getId());
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

			ExerciseDifficulty difficulty = ExerciseDifficulty.INTERMEDIATE;
			Set<MuscleGroupsEnum> muscles = Set.of(MuscleGroupsEnum.CHEST, MuscleGroupsEnum.BACK);


			when(repository.findAll(specificationCaptor.capture())).thenReturn(Collections.emptyList());

			service.getAllExercises(difficulty, muscles);


			verify(repository, times(1)).findAll(specificationCaptor.capture());

			Specification<ExerciseModel> capturedSpec = specificationCaptor.getValue();
			assertNotNull(capturedSpec);

		}


		@Test
		public void testGetAllExercisesWithNullDifficulty() {

			Set<MuscleGroupsEnum> muscles = Set.of(MuscleGroupsEnum.CHEST, MuscleGroupsEnum.BACK);

			when(repository.findAll(specificationCaptor.capture())).thenReturn(Collections.emptyList());

			service.getAllExercises(null, muscles);

			verify(repository, times(1)).findAll(specificationCaptor.capture());
		}


		@Test
		public void testGetAllExercisesWithEmptyMuscleGroups() {

			ExerciseDifficulty difficulty = ExerciseDifficulty.INTERMEDIATE;
			Set<MuscleGroupsEnum> muscles = Set.of();

			when(repository.findAll(specificationCaptor.capture())).thenReturn(Collections.emptyList());

			service.getAllExercises(difficulty, muscles);

			verify(repository, times(1)).findAll(specificationCaptor.capture());
		}


		@Test
		public void testGetAllExercisesWithNullMuscleGroups() {

			ExerciseDifficulty difficulty = ExerciseDifficulty.INTERMEDIATE;

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
					.intensity(ExerciseIntensity.MEDIUM)
					.muscles(Set.of(MuscleGroupsEnum.CHEST, MuscleGroupsEnum.FOREARM))
					.difficulty(ExerciseDifficulty.EXPERIENCED)
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
					.intensity(ExerciseIntensity.MEDIUM)
					.muscles(Set.of(MuscleGroupsEnum.CHEST, MuscleGroupsEnum.FOREARM))
					.difficulty(ExerciseDifficulty.EXPERIENCED)
					.build();


			when(repository.existsByName(nameCaptor.capture())).thenReturn(true);

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
					.intensity(ExerciseIntensity.MEDIUM)
					.muscles(Set.of(MuscleGroupsEnum.CHEST, MuscleGroupsEnum.FOREARM))
					.difficulty(ExerciseDifficulty.EXPERIENCED)
					.build();

			var exerciseRequest = ExerciseDTO.builder()
					.name("exercise three")
					.intensity(ExerciseIntensity.MEDIUM)
					.muscles(Set.of(MuscleGroupsEnum.CHEST, MuscleGroupsEnum.FOREARM))
					.difficulty(ExerciseDifficulty.EXPERIENCED)
					.build();


			when(repository.existsByName(nameCaptor.capture())).thenReturn(false);

			when(repository.findById(any(Long.class))).thenReturn(Optional.of(exerciseToEdit));

			assertDoesNotThrow(() -> service.editExercise(exerciseRequest, any(Long.class)));

			assertEquals(exerciseRequest.name(), nameCaptor.getValue());
			verify(repository, times(1)).save(
					argThat(updated -> updated.getName().equals(exerciseRequest.name())));
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
					.intensity(ExerciseIntensity.MEDIUM)
					.muscles(Set.of(MuscleGroupsEnum.CHEST, MuscleGroupsEnum.FOREARM))
					.difficulty(ExerciseDifficulty.EXPERIENCED)
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
					.intensity(ExerciseIntensity.MEDIUM)
					.muscles(Set.of(MuscleGroupsEnum.CHEST, MuscleGroupsEnum.FOREARM))
					.difficulty(ExerciseDifficulty.EXPERIENCED)
					.build();

			when(repository.existsByName(exerciseRequestToCreate.name())).thenReturn(false);

			assertDoesNotThrow(() -> service.createExercise(exerciseRequestToCreate));

			verify(repository, times(1)).existsByName(any());
			verify(repository, times(1)).save(
					argThat(created -> created.getName().equals(exerciseRequestToCreate.name()) && created.getMuscles()
							.equals(exerciseRequestToCreate.muscles())));
		}

	}

	@Nested
	class deleteExercise {

		@Test
		@DisplayName("should not delete if id dont exists")
		void testDeleteExerciseThrowsExceptionWhenIdDoesNotExists() {

			when(repository.existsById(longArgumentCaptor.capture())).thenReturn(false);

			assertThrowsExactly(ResourceNotFoundException.class, () -> service.deleteExercise(90L));
			verify(repository, never()).deleteById(any());
		}


		@Test
		@DisplayName("should delete exercise id correctly")
		void testDeleteExerciseDeleteSuccessfully() {

			when(repository.existsById(longArgumentCaptor.capture())).thenReturn(true);

			assertDoesNotThrow(() -> service.deleteExercise(90L));

			verify(repository, times(1)).deleteById(longArgumentCaptor.capture());
			assertEquals(90L, longArgumentCaptor.getValue());
		}

	}


}
