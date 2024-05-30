package edu.mateus.Gym.Exercises.dtos.response;

import edu.mateus.Gym.Exercises.enums.Intensity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseResponseDTO {

    private String intensity;

}