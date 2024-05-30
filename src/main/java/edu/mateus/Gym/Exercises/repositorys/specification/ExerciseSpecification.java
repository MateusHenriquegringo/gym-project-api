package edu.mateus.Gym.Exercises.repositorys.specification;

import edu.mateus.Gym.Exercises.dtos.request.FilterRequestDTO;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import edu.mateus.Gym.Exercises.repositorys.specification.handler.ExercisePredicatesHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseSpecification {

    private final ExerciseRepository exerciseRepository;
    private final ExercisePredicatesHandler exercisePredicatesHandler;

    public Page<ExerciseModel> findBy(FilterRequestDTO controllerFilter, Pageable pageable) {
        Specification<ExerciseModel> specification = (root, criteriaQuery, criteriaBuilder) ->
                criteriaQuery
                        .where(exercisePredicatesHandler.getPredicatesByFilter(controllerFilter, root, criteriaQuery, criteriaBuilder))
                        .getRestriction();
        return exerciseRepository.findAll(specification, pageable);
    }
}