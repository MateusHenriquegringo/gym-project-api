package edu.mateus.Gym.Exercises.dtos;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record ExerciseDTO(
		@NotBlank
		String name,
		ExerciseIntensity intensity,
		ExerciseType type,
		List<MuscleGroupsEnum> muscles,
		ExerciseDifficulty difficulty

) {
}
