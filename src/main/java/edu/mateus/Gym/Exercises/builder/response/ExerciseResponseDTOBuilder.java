package edu.mateus.Gym.Exercises.builder.response;

import edu.mateus.Gym.Exercises.dtos.PageDTO;
import edu.mateus.Gym.Exercises.dtos.response.ExerciseResponseDTO;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExerciseResponseDTOBuilder {

    public static List<ExerciseResponseDTO> buildList(List<ExerciseModel> exerciseModelList){
        List<ExerciseResponseDTO> responseDTOList = new ArrayList<>();

        for(ExerciseModel exerciseModel : exerciseModelList){
            responseDTOList.add(buildPageDTO(exerciseModel));
        };
        return responseDTOList;
    }
    private static ExerciseResponseDTO buildPageDTO(ExerciseModel exerciseModel) {
        return ExerciseResponseDTO
                .builder()
                .intensity(exerciseModel.getIntensity().name())
                .build();
    }

}