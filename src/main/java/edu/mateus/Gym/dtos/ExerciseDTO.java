package edu.mateus.Gym.dtos;

import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.enums.ExerciseType;
import edu.mateus.Gym.enums.Intensity;
import edu.mateus.Gym.enums.MuscleGroupsEnum;
import jakarta.persistence.Enumerated;
import lombok.NonNull;

import java.util.List;

public record ExerciseDTO(String name, Intensity intensity, ExerciseType type,List<MuscleGroupsEnum> muscles,
                          ExerciseDifficulty difficulty) {
}
