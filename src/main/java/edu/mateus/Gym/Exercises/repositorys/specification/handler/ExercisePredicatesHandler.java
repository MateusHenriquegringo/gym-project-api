package edu.mateus.Gym.Exercises.repositorys.specification.handler;


import edu.mateus.Gym.Exercises.dtos.request.FilterRequestDTO;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.models.ExerciseModel_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExercisePredicatesHandler {

    public Predicate[] getPredicatesByFilter(FilterRequestDTO controllerFilter,
                                             Root<ExerciseModel> root,
                                             CriteriaQuery<?> cq,
                                             CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(controllerFilter.getDifficulty())) {
            predicates.add(cb.equal(root.get(ExerciseModel_.difficulty), controllerFilter.getDifficulty()));
        }

        return predicates.toArray(new Predicate[0]);
    }
}