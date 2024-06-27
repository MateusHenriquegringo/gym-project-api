package edu.mateus.Gym.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mateus.Gym.config.SpringSecurityConfiguration;
import edu.mateus.Gym.controllers.assembler.ExerciseAssembler;
import edu.mateus.Gym.dtos.ExerciseDTO;
import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.enums.ExerciseIntensity;
import edu.mateus.Gym.enums.MuscleGroupsEnum;
import edu.mateus.Gym.models.ExerciseModel;
import edu.mateus.Gym.services.ExerciseService;
import jakarta.annotation.Resource;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SpringSecurityConfiguration.class)
@WebMvcTest(controllers = ExerciseController.class)
public class ExerciseControllerTest {

	@Autowired
	MockMvc mvc;

	@Resource
	ObjectMapper mapper;

	@MockBean
	ExerciseService service;

	@MockBean
	ExerciseAssembler assembler;

	@Autowired
	WebApplicationContext context;

	@Captor
	ArgumentCaptor<ExerciseDifficulty> difficultyArgumentCaptor;

	@Captor
	ArgumentCaptor<Long> longArgumentCaptor;

	@Captor
	ArgumentCaptor<Set<MuscleGroupsEnum>> musclesArgumentCaptor;

	@InjectMocks
	ExerciseController controller;

	List<ExerciseModel> exerciseModelList = List.of(ExerciseModel.builder()
			                                                .id(1L)
			                                                .difficulty(ExerciseDifficulty.BEGINNER)
			                                                .name("test")
			                                                .intensity(ExerciseIntensity.LOW)
			                                                .muscles(Set.of(MuscleGroupsEnum.TRICEPS,
			                                                                MuscleGroupsEnum.BICEPS))
			                                                .build(), ExerciseModel.builder()
			                                                .id(1L)
			                                                .difficulty(ExerciseDifficulty.INTERMEDIATE)
			                                                .name("test two")
			                                                .intensity(ExerciseIntensity.LOW)
			                                                .muscles(Set.of(MuscleGroupsEnum.TRICEPS,
			                                                                MuscleGroupsEnum.CHEST))
			                                                .build());


	@Nested
	public class getAllExercises {

		@Test
		@DisplayName("getAllExercises should throw exception")
		void testGetAllExercisesWithIncorrectParamsShouldThrowException() throws Exception {

			var nonExistentParam = "badRequest";

			mvc.perform(get("/api/exercises").param("difficulty", nonExistentParam))
					.andExpect(status().isBadRequest())
					.andReturn();

			verify(assembler, never()).toCollectionModel(any());
			verify(service, never()).getAllExercises(any(), any());

		}


		@Test
		@DisplayName("getAllExercises should work correctly")
		void testGetAllExercisesWithNoParams() throws Exception {

			when(assembler.toCollectionModel(service.getAllExercises(null, null))).thenReturn(
					CollectionModel.of(exerciseModelList));


			var out = mvc.perform(get("/api/exercises"))
					.andExpect(status().is(200))
					.andExpect(jsonPath("$._embedded.exercises[0].name").value("test"))
					.andExpect(jsonPath("$._embedded.exercises[0].id").value(exerciseModelList.get(0).getId()))
					.andExpect(jsonPath("$._embedded.exercises[0].muscles").isNotEmpty())
					.andExpect(jsonPath("$._embedded.exercises.size()").value(exerciseModelList.size()))
					.andExpect(content().contentType("application/hal+json"))
					.andReturn();

			//hal+json refers to the hyperlinks navigation provided by spring HATEOAS in ExerciseAssembler.class
		}


		@Test
		@DisplayName("getAllExercises should capture the difficulty param and return correctly")
		void testGetAllExercisesWithDifficultyParam() throws Exception {

			var difficulty = ExerciseDifficulty.INTERMEDIATE;

			var filteredByDifficulty = exerciseModelList.stream()
					.filter(exerciseModel -> exerciseModel.getDifficulty().equals(difficulty))
					.toList();

			var exerciseModelsCollection = CollectionModel.of(filteredByDifficulty);

			when(service.getAllExercises(difficultyArgumentCaptor.capture(), eq(null))).thenReturn(
					filteredByDifficulty);
			when(assembler.toCollectionModel(anyList())).thenReturn(exerciseModelsCollection)
					.thenReturn(exerciseModelsCollection);

			mvc.perform(get("/api/exercises").param("difficulty", String.valueOf(difficulty)))
					.andExpect(jsonPath("$._embedded.exercises[0].name").value("test two"))
					.andExpect(jsonPath("$._embedded.exercises[0].id").value(filteredByDifficulty.get(0).getId()))
					.andExpect(jsonPath("$._embedded.exercises[0].muscles").isNotEmpty())
					.andExpect(jsonPath("$._embedded.exercises[0].difficulty").value(
							difficultyArgumentCaptor.getValue().toString()))
					.andExpect(content().contentType("application/hal+json"))
					.andExpect(jsonPath("$._embedded.exercises.size()").value(filteredByDifficulty.size()))
					.andExpect(status().is(200));


			assertEquals(difficulty, difficultyArgumentCaptor.getValue());

			verify(assembler, times(1)).toCollectionModel(any());
			verify(service, times(1)).getAllExercises(any(), any());
		}


		@Test
		@DisplayName("getAllExercises should capture the difficulty param and return correctly")
		void testGetAllExercisesWithMusclesParam() throws Exception {

			var muscles = List.of(MuscleGroupsEnum.CHEST);

			var filteredByMuscles = exerciseModelList.stream()
					.filter(exerciseModel -> exerciseModel.getMuscles().containsAll(muscles))
					.toList();

			var exerciseModelsCollection = CollectionModel.of(filteredByMuscles);

			when(service.getAllExercises(eq(null), musclesArgumentCaptor.capture())).thenReturn(filteredByMuscles);
			when(assembler.toCollectionModel(anyList())).thenReturn(exerciseModelsCollection);

			var output = mvc.perform(get("/api/exercises").param("muscles", muscles.get(0).toString()))
					.andExpect(jsonPath("$._embedded.exercises[0].name").value("test two"))
					.andExpect(jsonPath("$._embedded.exercises[0].muscles").isNotEmpty())
					.andExpect(jsonPath("$._embedded.exercises[0].id").value(filteredByMuscles.get(0).getId()))
					.andExpect(content().contentType("application/hal+json"))
					.andExpect(jsonPath("$._embedded.exercises.size()").value(filteredByMuscles.size()))
					.andExpect(status().is(200))
					.andReturn();


			System.out.println(output.getResponse().getContentAsString());
			assertTrue(musclesArgumentCaptor.getValue().containsAll(muscles));

			verify(assembler, times(1)).toCollectionModel(any());
			verify(service, times(1)).getAllExercises(any(), any());
		}


	}

	@Nested
	public class createExercise {

		@Test
		void shouldCreateAnExerciseSuccessfully() throws Exception {

			var dto = ExerciseDTO.builder()
					.name("nome")
					.muscles(Set.of(MuscleGroupsEnum.TRICEPS))
					.intensity(ExerciseIntensity.LOW)
					.difficulty(ExerciseDifficulty.BEGINNER).build();


			when(service.createExercise(any(ExerciseDTO.class))).thenReturn(exerciseModelList.get(0));
			when(assembler.toModel(any(ExerciseModel.class))).thenReturn(exerciseModelList.get(0));

			mvc.perform(post("/api/exercises")
					            .contentType(MediaType.APPLICATION_JSON)
					            .content(mapper.writeValueAsString(dto)))
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.name").value("test"))
					.andReturn();

			verify(assembler, times(1)).toModel(any());
			verify(service, times(1)).createExercise(any());

		}


		@Test
		void shouldNotCreateAnExerciseWithIncorrectParams() throws Exception {

			var dto = ExerciseDTO.builder()
					.name("")
					.build();

			when(service.createExercise(any(ExerciseDTO.class))).thenReturn(exerciseModelList.get(0));
			when(assembler.toModel(any(ExerciseModel.class))).thenReturn(exerciseModelList.get(0));

			mvc.perform(post("/api/exercises")
					            .contentType(MediaType.APPLICATION_JSON)
					            .content(mapper.writeValueAsString(dto)))
					.andExpect(status().isBadRequest());

			verify(service, never()).createExercise(any());
		}


		@Nested
		public class getExerciseById {

			@Test
			void getByIdWorkSuccessfully() throws Exception {

				when(service.getExerciseById(longArgumentCaptor.capture())).thenReturn(exerciseModelList.get(0));
				when(assembler.toModel(any(ExerciseModel.class))).thenReturn(exerciseModelList.get(0));

				mvc.perform(get("/api/exercises/{id}", 1L))
						.andExpect(status().isOk())
						.andExpect(content().contentType("application/hal+json"))
						.andExpect(jsonPath("$.id").value(longArgumentCaptor.getValue()))
						.andReturn();


				verify(service, times(1)).getExerciseById(longArgumentCaptor.getValue());
				verify(assembler, times(1)).toModel(any(ExerciseModel.class));
				assertEquals(longArgumentCaptor.getValue(), 1L);

			}


			@Test
			void getByIdWhenIdDoesNotExists() throws Exception {

				Long id = 9000L;

				var output = mvc.perform(get("/api/exercise/{id}", id))
						.andExpect(status().isNotFound())
						.andReturn();

			}

		}


	}

}
