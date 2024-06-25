package edu.mateus.Gym.Users.dtos;

import edu.mateus.Gym.Users.enums.SexEnum;


public record RequestUserModelDTO(
		String name,
		Integer age,
		SexEnum sex,
		Double weight,
		Double height,
		String password
) {
}
