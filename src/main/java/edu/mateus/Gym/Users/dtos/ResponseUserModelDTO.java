package edu.mateus.Gym.Users.dtos;

import edu.mateus.Gym.Users.enums.SexEnum;
import lombok.Builder;

@Builder
public record ResponseUserModelDTO (
		String name,
		Integer age,
		SexEnum sex,
		Double weight,
		Double height,
		String email
){};
