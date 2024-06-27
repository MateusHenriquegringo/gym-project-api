package edu.mateus.Gym.dtos;

import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.enums.ExerciseIntensity;
import edu.mateus.Gym.enums.MuscleGroupsEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record ExerciseDTO(
		@NotBlank
		String name,
		ExerciseIntensity intensity,
		Set<MuscleGroupsEnum> muscles,
		ExerciseDifficulty difficulty

) {
}
