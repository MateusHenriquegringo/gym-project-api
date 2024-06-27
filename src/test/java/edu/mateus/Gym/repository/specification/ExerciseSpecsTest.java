package edu.mateus.Gym.repository.specification;

import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.enums.ExerciseIntensity;

import edu.mateus.Gym.enums.MuscleGroupsEnum;
import edu.mateus.Gym.models.ExerciseModel;
import edu.mateus.Gym.repositorys.ExerciseRepository;
import edu.mateus.Gym.repositorys.specification.ExerciseSpecs;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class ExerciseSpecsTest {

	@Resource
	ExerciseRepository repository;

	private List<ExerciseModel> exercises = List.of(
			ExerciseModel.builder()
					.name("exercise")
					.intensity(ExerciseIntensity.MEDIUM)
					.muscles(Set.of(MuscleGroupsEnum.CHEST,
					                     MuscleGroupsEnum.TRICEPS,
					                     MuscleGroupsEnum.BACK))
					.difficulty(ExerciseDifficulty.BEGINNER)
					.build(),

			ExerciseModel.builder()
					.name("exercise two")
					.intensity(ExerciseIntensity.MEDIUM)
					.muscles(Set.of(MuscleGroupsEnum.CHEST,
					                 MuscleGroupsEnum.TRICEPS))
					.difficulty(ExerciseDifficulty.BEGINNER)
					.build(),

			ExerciseModel.builder()
					.name("exercise three")
					.intensity(ExerciseIntensity.MEDIUM)
					.muscles(Set.of(MuscleGroupsEnum.CHEST,
					                 MuscleGroupsEnum.FOREARM))
					.difficulty(ExerciseDifficulty.EXPERIENCED)
					.build());


	@BeforeEach
	public void setUp() {
		repository.saveAll(exercises);
	}

	@AfterEach
	public void clean(){
		repository.deleteAll();
	}

	@Test
	@DisplayName("should return exercises with only difficulty defined")
	void specShouldReturnByDifficulty() {

		ExerciseDifficulty difficulty = ExerciseDifficulty.EXPERIENCED;

		Specification<ExerciseModel> spec = Specification.where(
				ExerciseSpecs.hasAllMuscleGroups(null).and(ExerciseSpecs.hasDifficulty(difficulty)));


		var output = repository.findAll(spec);

		assertFalse(output.isEmpty());
		output.forEach(exerciseModel -> assertEquals(difficulty, exerciseModel.getDifficulty()));
	}


	@Test
	@DisplayName("should return exercises with only muscles filter defined")
	void specShouldReturnByMuscles() {

		Set<MuscleGroupsEnum> muscles = Set.of(MuscleGroupsEnum.TRICEPS, MuscleGroupsEnum.CHEST);

		Specification<ExerciseModel> spec = Specification.where(
				ExerciseSpecs.hasAllMuscleGroups(muscles).and(ExerciseSpecs.hasDifficulty(null)));


		var output = repository.findAll(spec);

		output.forEach(exerciseModel -> assertTrue(exerciseModel.getMuscles().containsAll(muscles)));
		assertFalse(output.isEmpty());
	}


	@Test
	@DisplayName("should NOT return exercises by a non existent list of muscles defined")
	void specShouldNotEmptyByNonExistentMusclesList() {

		Set<MuscleGroupsEnum> muscles = Set.of(MuscleGroupsEnum.TRICEPS, MuscleGroupsEnum.CHEST,
		                                         MuscleGroupsEnum.ABDOMEN);

		Specification<ExerciseModel> spec = Specification.where(
				ExerciseSpecs.hasAllMuscleGroups(muscles).and(ExerciseSpecs.hasDifficulty(null)));


		var output = repository.findAll(spec);

		assertTrue(output.isEmpty());
	}


	@Test
	@DisplayName("should return exercises with muscles list filter also difficulty filter")
	void specShouldReturnByMusclesAndDifficulty() {

		Set<MuscleGroupsEnum> muscles = Set.of(MuscleGroupsEnum.TRICEPS, MuscleGroupsEnum.CHEST);
		ExerciseDifficulty difficulty = ExerciseDifficulty.BEGINNER;


		Specification<ExerciseModel> spec = Specification.where(
				ExerciseSpecs.hasAllMuscleGroups(muscles).and(ExerciseSpecs.hasDifficulty(difficulty)));


		var output = repository.findAll(spec);

		output.forEach(exerciseModel -> assertTrue(
				exerciseModel.getMuscles().containsAll(muscles) && exerciseModel.getDifficulty().equals(difficulty)));
		assertFalse(output.isEmpty());
	}

}
