package edu.mateus.Gym.Exercises.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mateus.Gym.Exercises.controllers.assembler.ExerciseAssembler;
import edu.mateus.Gym.Exercises.dtos.ExerciseDTO;
import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.services.ExerciseService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

	@Captor
	ArgumentCaptor<ExerciseDifficulty> difficultyArgumentCaptor;

	@Captor
	ArgumentCaptor<Long> longArgumentCaptor;

	@Captor
	ArgumentCaptor<List<MuscleGroupsEnum>> musclesArgumentCaptor;

	@InjectMocks
	ExerciseController controller;

	List<ExerciseModel> exerciseModelList = List.of(ExerciseModel.builder()
			                                                .id(1L)
			                                                .difficulty(ExerciseDifficulty.INICIANTE)
			                                                .name("test")
			                                                .intensity(ExerciseIntensity.BAIXA)
			                                                .muscles(List.of(MuscleGroupsEnum.TRICEPS,
			                                                                 MuscleGroupsEnum.BICEPS))
			                                                .type(ExerciseType.CARDIO)
			                                                .build(), ExerciseModel.builder()
			                                                .id(1L)
			                                                .difficulty(ExerciseDifficulty.INTERMEDIARIO)
			                                                .name("test two")
			                                                .intensity(ExerciseIntensity.BAIXA)
			                                                .muscles(List.of(MuscleGroupsEnum.TRICEPS,
			                                                                 MuscleGroupsEnum.PEITORAL))
			                                                .type(ExerciseType.CARDIO)
			                                                .build());


	@Nested
	public class getAllExercises {

		@Test
		@DisplayName("getAllExercises should throw exception")
		void testGetAllExercisesWithIncorrectParamsShouldThrowException() throws Exception {

			var nonExistentParam = "badRequest";

			var mvcResult = mvc.perform(get("/api/exercises").param("difficulty", nonExistentParam))
					.andExpect(status().isBadRequest())
					.andReturn();

			assertEquals("No enum constant edu.mateus.Gym.Exercises.enums.ExerciseDifficulty.badRequest",
			             mvcResult.getResponse().getContentAsString());

			verify(assembler, never()).toCollectionModel(any());
			verify(service, never()).getAllExercises(any(), any());

		}


		@Test
		@DisplayName("getAllExercises should work correctly")
		void testGetAllExercisesWithNoParams() throws Exception {

			when(assembler.toCollectionModel(service.getAllExercises(null, null))).thenReturn(
					CollectionModel.of(exerciseModelList));


			mvc.perform(get("/api/exercises"))
					.andExpect(status().is(200))
					.andExpect(jsonPath("$._embedded.exerciseModelList[0].name").value("test"))
					.andExpect(jsonPath("$._embedded.exerciseModelList[0].id").value(exerciseModelList.get(0).getId()))
					.andExpect(jsonPath("$._embedded.exerciseModelList[0].muscles").isNotEmpty())
					.andExpect(jsonPath("$._embedded.exerciseModelList.size()").value(exerciseModelList.size()))
					.andExpect(content().contentType("application/hal+json"));


			//hal+json refers to the hyperlinks navigation provided by spring HATEOAS in ExerciseAssembler.class
		}


		@Test
		@DisplayName("getAllExercises should capture the difficulty param and return correctly")
		void testGetAllExercisesWithDifficultyParam() throws Exception {

			var difficulty = ExerciseDifficulty.INTERMEDIARIO;

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

			var muscles = List.of(MuscleGroupsEnum.PEITORAL);

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
					.muscles(List.of(MuscleGroupsEnum.TRICEPS))
					.intensity(ExerciseIntensity.BAIXA)
					.difficulty(ExerciseDifficulty.INICIANTE).type(ExerciseType.CARDIO).build();


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
