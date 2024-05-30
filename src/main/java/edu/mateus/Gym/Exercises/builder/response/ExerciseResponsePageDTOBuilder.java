package edu.mateus.Gym.Exercises.builder.response;

import edu.mateus.Gym.Exercises.dtos.PageDTO;
import edu.mateus.Gym.Exercises.dtos.response.ExerciseResponseDTO;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExerciseResponsePageDTOBuilder {

    public static PageDTO<ExerciseResponseDTO> buildPageDTO(Page<ExerciseModel>  exerciseModelPage) {
        return PageDTO.<ExerciseResponseDTO>
                        builder()
                .pageNum(exerciseModelPage.getNumber() + 1)
                .totalItems(exerciseModelPage.getTotalElements())
                .totalPages(exerciseModelPage.getTotalPages())
                .pageItemsNum(exerciseModelPage.getNumberOfElements())
                .data(ExerciseResponseDTOBuilder.buildList(exerciseModelPage.getContent()))
                .build();
    }
}