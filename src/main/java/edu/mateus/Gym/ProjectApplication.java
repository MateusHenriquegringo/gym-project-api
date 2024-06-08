package edu.mateus.Gym;

import edu.mateus.Gym.Exercises.models.ExerciseModel;
import edu.mateus.Gym.Exercises.repositorys.ExerciseRepository;
import edu.mateus.Gym.Exercises.tools.LoadDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProjectApplication {

	@Autowired
	LoadDatabase loader;


	public static void main(String[] args) {

		SpringApplication.run(ProjectApplication.class, args);

	}


}
