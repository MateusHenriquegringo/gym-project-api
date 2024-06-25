package edu.mateus.Gym.Exercises.services.assembler;

import edu.mateus.Gym.Exercises.controllers.ExerciseController;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ExerciseAssembler extends RepresentationModelAssemblerSupport<ExerciseModel, ExerciseModel> {


	public ExerciseAssembler() {

		super(ExerciseController.class, ExerciseModel.class);
	}


	@Override
	public ExerciseModel toModel(ExerciseModel entity) {

		entity
				.add(linkTo(methodOn(ExerciseController.class).getExerciseById(entity.getId())).withSelfRel());
		return entity;
	}

	@Override
	public CollectionModel <ExerciseModel> toCollectionModel(Iterable<? extends ExerciseModel> entities) {

		return super.toCollectionModel(entities);
	}


}
