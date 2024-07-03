package edu.mateus.Gym.dtos;


import java.util.List;

public record ExercisePlanRequestDTO (
		String name,
		String description,
		List<ExerciseDetailedDTO> exercises
){};
