package edu.mateus.Gym.Exercises.dtos;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import lombok.Builder;

import java.util.List;

@Builder
public record ExerciseDTO(
		String name,
		ExerciseIntensity intensity,
		ExerciseType type,
		List<MuscleGroupsEnum> muscles,
		ExerciseDifficulty difficulty

) {
}
