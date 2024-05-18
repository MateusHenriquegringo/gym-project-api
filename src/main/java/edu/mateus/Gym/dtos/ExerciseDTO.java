package edu.mateus.Gym.dtos;

import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.enums.ExerciseType;
import edu.mateus.Gym.enums.Intensity;
import edu.mateus.Gym.enums.MuscleGroups;

import java.util.List;

public record ExerciseDTO(String name, Intensity intensity, ExerciseType type, List<MuscleGroups> muscleGroups,
                          ExerciseDifficulty difficulty) {
}
