package edu.mateus.Gym;

import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import edu.mateus.Gym.Exercises.tools.LoadDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);

	}

	@Bean
	List<ExerciseModel> loadDatabase(ExerciseRepository repository){
		return repository.saveAll(LoadDatabase.getExercises());
	}


}
