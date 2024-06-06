package edu.mateus.Gym.Exercises.tools;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import edu.mateus.Gym.Exercises.models.ExerciseModel;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class LoadDatabase {


	private boolean loaded = false;

	@Getter()
	private List<ExerciseModel> exercises = loadDatabase();


	private List<ExerciseModel> loadDatabase() {

		if (!this.loaded) {
			loaded = true;
			return Arrays.asList(
					new ExerciseModel("Corrida", ExerciseIntensity.ALTA, ExerciseType.CARDIO,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS, MuscleGroupsEnum.PANTURRILHA,
					                                MuscleGroupsEnum.GLUTEOS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Agachamento com barra", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS, MuscleGroupsEnum.GLUTEOS,
					                                MuscleGroupsEnum.LOMBAR), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Flexão de braço", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.TRICEPS,
					                                MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Levantamento terra", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO_OLIMPICO,
					                  Arrays.asList(MuscleGroupsEnum.LOMBAR, MuscleGroupsEnum.GLUTEOS,
					                                MuscleGroupsEnum.QUADRICEPS), ExerciseDifficulty.EXPERIENTE),
					new ExerciseModel("Pular corda", ExerciseIntensity.ALTA, ExerciseType.CARDIO,
					                  Arrays.asList(MuscleGroupsEnum.PANTURRILHA, MuscleGroupsEnum.QUADRICEPS,
					                                MuscleGroupsEnum.GLUTEOS), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Puxada frontal", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DORSAL, MuscleGroupsEnum.BICEPS,
					                                MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Burpee", ExerciseIntensity.ALTA, ExerciseType.PLIOMETRIA,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS, MuscleGroupsEnum.GLUTEOS,
					                                MuscleGroupsEnum.PEITORAL), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Alongamento de isquiotibiais", ExerciseIntensity.BAIXA, ExerciseType.ALONGAMENTO,
					                  List.of(MuscleGroupsEnum.ISQUIOTIBIAIS), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Supino reto", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.TRICEPS,
					                                MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Abdominal crunch", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  List.of(MuscleGroupsEnum.ABDOMEN), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Elevação de panturrilha", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  List.of(MuscleGroupsEnum.PANTURRILHA), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Remada curvada", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DORSAL, MuscleGroupsEnum.BICEPS,
					                                MuscleGroupsEnum.LOMBAR), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Desenvolvimento de ombro", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.DELTOIDES, MuscleGroupsEnum.TRICEPS),
					                  ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Afundo", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS, MuscleGroupsEnum.GLUTEOS,
					                                MuscleGroupsEnum.ISQUIOTIBIAIS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Rosca direta", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.BICEPS, MuscleGroupsEnum.ANTEBRACO),
					                  ExerciseDifficulty.INICIANTE),

					new ExerciseModel("Flexão de braço inclinada", ExerciseIntensity.BAIXA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.TRICEPS,
					                                MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Stiff", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.ISQUIOTIBIAIS, MuscleGroupsEnum.LOMBAR),
					                  ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Rosca alternada", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.BICEPS, MuscleGroupsEnum.ANTEBRACO),
					                  ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Tríceps testa", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.TRICEPS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Cadeira extensora", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Elevação lateral", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Afundo no Smith", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS, MuscleGroupsEnum.GLUTEOS,
					                                MuscleGroupsEnum.ISQUIOTIBIAIS), ExerciseDifficulty.EXPERIENTE),
					new ExerciseModel("Crucifixo", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.PEITORAL), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Máquina adutora", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.ADUTORES), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Encolhimento de ombros", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.TRAPEZIO), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Gêmeos sentado", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.PANTURRILHA), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Crunch com bola", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.ABDOMEN), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Remada baixa", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DORSAL), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Extensão de tríceps com corda", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.TRICEPS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Elevação frontal", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Elevação de pernas suspensa", ExerciseIntensity.BAIXA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.ABDOMEN), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Máquina flexora", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.ISQUIOTIBIAIS), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Rosca scott", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.BICEPS), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Mergulho", ExerciseIntensity.ALTA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.TRICEPS),
					                  ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Crunch na máquina", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.ABDOMEN), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Tríceps na polia", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.TRICEPS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Elevação de calcanhares", ExerciseIntensity.BAIXA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.PANTURRILHA), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Voador inverso", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Máquina abdutora", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.ADUTORES, MuscleGroupsEnum.ADUTORES),
					                  ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Rosca punho", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.ANTEBRACO), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Cadeira flexora", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.ISQUIOTIBIAIS), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Crunch na bola", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.ABDOMEN), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Puxada neutra", ExerciseIntensity.ALTA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DORSAL, MuscleGroupsEnum.BICEPS),
					                  ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Tríceps coice", ExerciseIntensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.TRICEPS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Agachamento hack", ExerciseIntensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS, MuscleGroupsEnum.GLUTEOS,
					                                MuscleGroupsEnum.ISQUIOTIBIAIS), ExerciseDifficulty.EXPERIENTE),
					new ExerciseModel("Alongamento de quadríceps", ExerciseIntensity.BAIXA, ExerciseType.ALONGAMENTO,
					                  List.of(MuscleGroupsEnum.QUADRICEPS), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Alongamento de panturrilha", ExerciseIntensity.BAIXA, ExerciseType.ALONGAMENTO,
					                  List.of(MuscleGroupsEnum.PANTURRILHA), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Alongamento de ombros", ExerciseIntensity.BAIXA, ExerciseType.ALONGAMENTO,
					                  List.of(MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Torção da coluna vertebral", ExerciseIntensity.BAIXA, ExerciseType.ALONGAMENTO,
					                  List.of(MuscleGroupsEnum.LOMBAR), ExerciseDifficulty.INICIANTE)

			);
		} else {
			return null;
		}
	}

}
