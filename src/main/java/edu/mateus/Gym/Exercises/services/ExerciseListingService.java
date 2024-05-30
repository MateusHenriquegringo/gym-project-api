package edu.mateus.Gym.Exercises.services;

import edu.mateus.Gym.Exercises.builder.response.ExerciseResponsePageDTOBuilder;
import edu.mateus.Gym.Exercises.dtos.PageDTO;
import edu.mateus.Gym.Exercises.dtos.request.FilterRequestDTO;
import edu.mateus.Gym.Exercises.dtos.request.PageFilter;
import edu.mateus.Gym.Exercises.dtos.response.ExerciseResponseDTO;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.specification.ExerciseSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseListingService {

    private final ExerciseSpecification exerciseSpecification;

    public PageDTO<ExerciseResponseDTO> getAllByFilter(PageFilter pageFilter, FilterRequestDTO filterRequestDTO) {

        Pageable pageable = PageRequest.of(pageFilter.getPage() - 1, pageFilter.getMaxItems());
        Page<ExerciseModel> exerciseModelList = exerciseSpecification.findBy(filterRequestDTO, pageable);
        return ExerciseResponsePageDTOBuilder.buildPageDTO(exerciseModelList);
    }
}