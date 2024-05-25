package edu.mateus.Gym.Exercises.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExerciseType {
		CARDIO("cardio"),
		LEVANTAMENTO_PESO_OLIMPICO("levantamento de peso olimpico"),
		PLIOMETRIA("pliometria"),
		LEVANTAMENTO_PESO("levantamento de peso"),
		FORCA("força"),
		ALONGAMENTO("alongamento");

	private final String name;

}
