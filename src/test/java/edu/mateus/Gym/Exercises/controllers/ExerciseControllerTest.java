package edu.mateus.Gym.Exercises.controllers;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.services.ExerciseService;
import edu.mateus.Gym.Exercises.services.assembler.ExerciseAssembler;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ExerciseController.class)
public class ExerciseControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	ExerciseService service;

	@MockBean
	ExerciseAssembler assembler;

	@Captor
	ArgumentCaptor<ExerciseDifficulty> difficultyArgumentCaptor;

	@InjectMocks
	ExerciseController controller;

	List<ExerciseModel> exerciseModelList = List.of(
			ExerciseModel.builder()
					.id(1L)
					.difficulty(ExerciseDifficulty.INICIANTE)
					.name("test")
					.intensity(ExerciseIntensity.BAIXA)
					.muscles(List.of(MuscleGroupsEnum.TRICEPS))
					.type(ExerciseType.CARDIO)
					.build(),
			ExerciseModel.builder()
					.id(1L)
					.difficulty(ExerciseDifficulty.INTERMEDIARIO)
					.name("test two")
					.intensity(ExerciseIntensity.BAIXA)
					.muscles(List.of(MuscleGroupsEnum.TRICEPS))
					.type(ExerciseType.CARDIO)
					.build()
	);


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
		void testAssemblerHATEOAS() throws Exception {

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
					.filter(exerciseModel -> exerciseModel.getDifficulty()
							.equals(difficulty))
					.toList();

			var exerciseModelsCollection = CollectionModel.of(filteredByDifficulty);

			when(service.getAllExercises(difficultyArgumentCaptor.capture(), eq(null))).thenReturn(
					filteredByDifficulty);
			when(assembler.toCollectionModel(anyList())).thenReturn(exerciseModelsCollection)
					.thenReturn(exerciseModelsCollection);

			mvc.perform(get("/api/exercises")
					            .param("difficulty", String.valueOf(difficulty)))
					.andExpect(jsonPath("$._embedded.exerciseModelList[0].name").value("test two"))
					.andExpect(
							jsonPath("$._embedded.exerciseModelList[0].id").value(filteredByDifficulty.get(0).getId()))
					.andExpect(jsonPath("$._embedded.exerciseModelList[0].muscles").isNotEmpty())
					.andExpect(jsonPath("$._embedded.exerciseModelList[0].difficulty").value(
							difficultyArgumentCaptor.getValue().toString()))
					.andExpect(content().contentType("application/hal+json"))
					.andExpect(jsonPath("$._embedded.exerciseModelList.size()").value(filteredByDifficulty.size()))
					.andExpect(status().is(200));


			assertEquals(difficulty, difficultyArgumentCaptor.getValue());

			verify(assembler, times(1)).toCollectionModel(any());
			verify(service, times(1)).getAllExercises(any(), any());
		}

	}


}
