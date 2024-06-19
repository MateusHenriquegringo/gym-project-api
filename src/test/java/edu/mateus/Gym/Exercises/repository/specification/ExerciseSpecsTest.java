package edu.mateus.Gym.Exercises.repository.specification;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import edu.mateus.Gym.Exercises.repositorys.specification.ExerciseSpecs;
import jakarta.annotation.Resource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class ExerciseSpecsTest {

	@Resource
	ExerciseRepository repository;

	private List<ExerciseModel> exercises = List.of(
			ExerciseModel.builder()
					.name("exercise")
					.type(ExerciseType.CARDIO)
					.intensity(ExerciseIntensity.MEDIA)
					.muscles(List.of(MuscleGroupsEnum.PEITORAL,
					                 MuscleGroupsEnum.TRICEPS,
					                 MuscleGroupsEnum.LOMBAR))
					.difficulty(ExerciseDifficulty.INICIANTE)
					.build(),

			ExerciseModel.builder()
					.name("exercise two")
					.type(ExerciseType.CARDIO)
					.intensity(ExerciseIntensity.MEDIA)
					.muscles(List.of(MuscleGroupsEnum.PEITORAL,
					                 MuscleGroupsEnum.TRICEPS))
					.difficulty(ExerciseDifficulty.INICIANTE)
					.build(),

			ExerciseModel.builder()
					.name("exercise three")
					.type(ExerciseType.CARDIO)
					.intensity(ExerciseIntensity.MEDIA)
					.muscles(List.of(MuscleGroupsEnum.PEITORAL,
					                 MuscleGroupsEnum.ANTEBRACO))
					.difficulty(ExerciseDifficulty.EXPERIENTE)
					.build());


	@BeforeEach
	public void setUp() {
		repository.saveAll(exercises);
	}


	@Test
	@DisplayName("should return exercises with only difficulty defined")
	void specShouldReturnByDifficulty() {

		ExerciseDifficulty difficulty = ExerciseDifficulty.EXPERIENTE;

		Specification<ExerciseModel> spec = Specification.where(
				ExerciseSpecs.hasAllMuscleGroups(null).and(ExerciseSpecs.hasDifficulty(difficulty)));


		var output = repository.findAll(spec);

		assertFalse(output.isEmpty());
		output.forEach(exerciseModel -> assertEquals(difficulty, exerciseModel.getDifficulty()));
	}


	@Test
	@DisplayName("should return exercises with only muscles filter defined")
	void specShouldReturnByMuscles() {

		List<MuscleGroupsEnum> muscles = List.of(MuscleGroupsEnum.TRICEPS, MuscleGroupsEnum.PEITORAL);

		Specification<ExerciseModel> spec = Specification.where(
				ExerciseSpecs.hasAllMuscleGroups(muscles).and(ExerciseSpecs.hasDifficulty(null)));


		var output = repository.findAll(spec);

		output.forEach(exerciseModel -> assertTrue(exerciseModel.getMuscles().containsAll(muscles)));
		assertFalse(output.isEmpty());
	}


	@Test
	@DisplayName("should NOT return exercises by a non existent list of muscles defined")
	void specShouldNotEmptyByNonExistentMusclesList() {

		List<MuscleGroupsEnum> muscles = List.of(MuscleGroupsEnum.TRICEPS, MuscleGroupsEnum.PEITORAL,
		                                         MuscleGroupsEnum.ABDOMEN);

		Specification<ExerciseModel> spec = Specification.where(
				ExerciseSpecs.hasAllMuscleGroups(muscles).and(ExerciseSpecs.hasDifficulty(null)));


		var output = repository.findAll(spec);

		assertTrue(output.isEmpty());
	}


	@Test
	@DisplayName("should return exercises with muscles list filter also difficulty filter")
	void specShouldReturnByMusclesAndDifficulty() {

		List<MuscleGroupsEnum> muscles = List.of(MuscleGroupsEnum.TRICEPS, MuscleGroupsEnum.PEITORAL);
		ExerciseDifficulty difficulty = ExerciseDifficulty.INICIANTE;


		Specification<ExerciseModel> spec = Specification.where(
				ExerciseSpecs.hasAllMuscleGroups(muscles).and(ExerciseSpecs.hasDifficulty(difficulty)));


		var output = repository.findAll(spec);

		output.forEach(exerciseModel -> assertTrue(
				exerciseModel.getMuscles().containsAll(muscles) && exerciseModel.getDifficulty().equals(difficulty)));
		assertFalse(output.isEmpty());
	}

}
