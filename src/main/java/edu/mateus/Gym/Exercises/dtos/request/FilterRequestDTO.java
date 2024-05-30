package edu.mateus.Gym.Exercises.dtos.request;


import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequestDTO {

    private ExerciseDifficulty difficulty;

}