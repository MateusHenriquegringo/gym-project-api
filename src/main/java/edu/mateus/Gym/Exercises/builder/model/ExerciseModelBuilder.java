package edu.mateus.Gym.Exercises.builder.model;

import edu.mateus.Gym.Exercises.dtos.ExerciseDTO;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExerciseModelBuilder {

    public static ExerciseModel buildModel(ExerciseModel exerciseModel, ExerciseDTO exerciseDTO){
        return exerciseModel
                .toBuilder()
                .name(exerciseModel.getName())
                .build();

    }

}