package edu.mateus.Gym.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MuscleGroupsEnum {
	ABDOMEN("Abdomen"),
	ADUTORES("Adutores"),
	ANTEBRACO("Antebraço"),
	ISQUIOTIBIAIS("Isquiotibiais"),
	LOMBAR("Lombar"),
	QUADRICEPS("Quadríceps"),
	POSTERIOR_DA_COXA("Posterior da Coxa"),
	PANTURRILHA("Panturrilha"),
	GLUTEOS("Glúteos"),
	PEITORAL("Peitoral"),
	DORSAL("Dorsal"),
	DELTOIDES("Deltoides"),
	BICEPS("Bíceps"),
	TRICEPS("Tríceps"),
	PESCOCO("Pescoço"),
	TRAPEZIO("Trapézio");

	private final String name;
}
