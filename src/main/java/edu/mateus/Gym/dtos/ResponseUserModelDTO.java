package edu.mateus.Gym.dtos;

import lombok.Builder;

@Builder
public record ResponseUserModelDTO (
		Long id,
		String name,
		Integer age,
		Double weight,
		Double height
){};
