package edu.mateus.Gym.Exercises.tools;

import edu.mateus.Gym.Exercises.controllers.ExerciseController;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ExercisesHateoas {
	public static ExerciseModel exerciseToHateoas(ExerciseModel exerciseModel){
		exerciseModel.add(linkTo(methodOn(ExerciseController.class).getAllExercises(null)).withRel("allExercises"));
		return exerciseModel;
	}
	public static List<ExerciseModel> exerciseToHateoas(List<ExerciseModel> exerciseModels){
		for (ExerciseModel exerciseModel : exerciseModels){
			exerciseModel.add(linkTo(methodOn(ExerciseController.class).getExerciseById(exerciseModel.getId())).withSelfRel());
		}
		return exerciseModels;
	}
}
