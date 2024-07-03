package edu.mateus.Gym.services;

import edu.mateus.Gym.dtos.ExerciseDetailedDTO;
import edu.mateus.Gym.dtos.ExercisePlanRequestDTO;
import edu.mateus.Gym.models.ExerciseDetails;
import edu.mateus.Gym.models.ExerciseModel;
import edu.mateus.Gym.models.ExercisePlan;
import edu.mateus.Gym.repositorys.ExerciseDetailsRepository;
import edu.mateus.Gym.repositorys.ExercisePlanRepository;
import edu.mateus.Gym.repositorys.ExerciseRepository;
import edu.mateus.Gym.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExercisePlanService {

	@Autowired
	ExercisePlanRepository planRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ExerciseRepository exerciseRepository;

	@Autowired
	ExerciseDetailsRepository detailsRepository;

	@Transactional
	public void createExercisePlan(ExercisePlanRequestDTO plan, Long id) {

		List<ExerciseDetails> exercisesToSave = new ArrayList<>();

		for (ExerciseDetailedDTO detailed : plan.exercises()) {

			var model = exerciseRepository.findById(detailed.exerciseId()).orElseThrow();

			ExerciseDetails details = new ExerciseDetails();

			details.setModel(model);
			details.setSets(detailed.sets());
			details.setRepetitions(detailed.repetitions());

			exercisesToSave.add(details);
		}

		detailsRepository.saveAll(exercisesToSave);

		ExercisePlan planToSave = ExercisePlan.builder()
				.createdBy(userRepository.findById(id).orElseThrow())
				.exercises(exercisesToSave)
				.description(plan.description())
				.name(plan.name())
				.build();

		planRepository.save(planToSave);
	}

	public List<ExercisePlan> getAllPlans() {
		return planRepository.findAll();
	}

}
