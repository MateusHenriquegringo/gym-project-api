package edu.mateus.Gym.dtos;


public record RequestUserModelDTO(
		String name,
		Integer age,
		Double weight,
		Double height,
		String password
) {
}
