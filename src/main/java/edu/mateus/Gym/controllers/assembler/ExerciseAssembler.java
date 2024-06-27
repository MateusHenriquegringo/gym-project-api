package edu.mateus.Gym.controllers.assembler;

import edu.mateus.Gym.controllers.ExerciseController;
import edu.mateus.Gym.models.ExerciseModel;
import lombok.NonNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ExerciseAssembler extends RepresentationModelAssemblerSupport<ExerciseModel, ExerciseModel> {


	public ExerciseAssembler() {

		super(ExerciseController.class, ExerciseModel.class);
	}


	@NonNull
	@Override
	public ExerciseModel toModel(ExerciseModel entity) {

		entity
				.add(linkTo(methodOn(ExerciseController.class).getExerciseById(entity.getId())).withSelfRel())
				.add(linkTo(methodOn(ExerciseController.class).getAllExercises(null, null)).withRel("allExercises"));
		return entity;
	}


	@NonNull
	@Override
	public CollectionModel<ExerciseModel> toCollectionModel(@NonNull Iterable<? extends ExerciseModel> entities) {

		return super.toCollectionModel(entities);

	}


}
