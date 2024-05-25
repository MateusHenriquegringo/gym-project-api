package edu.mateus.Gym.Exercises.dtos;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.Intensity;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;

import java.util.List;

public record ExerciseDTO(String name, Intensity intensity, ExerciseType type, List<MuscleGroupsEnum> muscles,
                          ExerciseDifficulty difficulty) {
}
