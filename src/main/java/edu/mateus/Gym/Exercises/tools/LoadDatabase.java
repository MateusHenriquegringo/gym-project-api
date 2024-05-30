package edu.mateus.Gym.Exercises.tools;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.Intensity;
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
					new ExerciseModel("Corrida", Intensity.ALTA, ExerciseType.CARDIO,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS, MuscleGroupsEnum.PANTURRILHA,
					                                MuscleGroupsEnum.GLUTEOS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Agachamento com barra", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS, MuscleGroupsEnum.GLUTEOS,
					                                MuscleGroupsEnum.LOMBAR), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Flexão de braço", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.TRICEPS,
					                                MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Levantamento terra", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO_OLIMPICO,
					                  Arrays.asList(MuscleGroupsEnum.LOMBAR, MuscleGroupsEnum.GLUTEOS,
					                                MuscleGroupsEnum.QUADRICEPS), ExerciseDifficulty.EXPERIENTE),
					new ExerciseModel("Pular corda", Intensity.ALTA, ExerciseType.CARDIO,
					                  Arrays.asList(MuscleGroupsEnum.PANTURRILHA, MuscleGroupsEnum.QUADRICEPS,
					                                MuscleGroupsEnum.GLUTEOS), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Puxada frontal", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DORSAL, MuscleGroupsEnum.BICEPS,
					                                MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Burpee", Intensity.ALTA, ExerciseType.PLIOMETRIA,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS, MuscleGroupsEnum.GLUTEOS,
					                                MuscleGroupsEnum.PEITORAL), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Alongamento de isquiotibiais", Intensity.BAIXA, ExerciseType.ALONGAMENTO,
					                  List.of(MuscleGroupsEnum.ISQUIOTIBIAIS), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Supino reto", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.TRICEPS,
					                                MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Abdominal crunch", Intensity.MEDIA, ExerciseType.FORCA,
					                  List.of(MuscleGroupsEnum.ABDOMEN), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Elevação de panturrilha", Intensity.MEDIA, ExerciseType.FORCA,
					                  List.of(MuscleGroupsEnum.PANTURRILHA), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Remada curvada", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DORSAL, MuscleGroupsEnum.BICEPS,
					                                MuscleGroupsEnum.LOMBAR), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Desenvolvimento de ombro", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.DELTOIDES, MuscleGroupsEnum.TRICEPS),
					                  ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Afundo", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS, MuscleGroupsEnum.GLUTEOS,
					                                MuscleGroupsEnum.ISQUIOTIBIAIS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Rosca direta", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.BICEPS, MuscleGroupsEnum.ANTEBRACO),
					                  ExerciseDifficulty.INICIANTE),

					new ExerciseModel("Flexão de braço inclinada", Intensity.BAIXA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.TRICEPS,
					                                MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Stiff", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.ISQUIOTIBIAIS, MuscleGroupsEnum.LOMBAR),
					                  ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Rosca alternada", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.BICEPS, MuscleGroupsEnum.ANTEBRACO),
					                  ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Tríceps testa", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.TRICEPS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Cadeira extensora", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Elevação lateral", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Afundo no Smith", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS, MuscleGroupsEnum.GLUTEOS,
					                                MuscleGroupsEnum.ISQUIOTIBIAIS), ExerciseDifficulty.EXPERIENTE),
					new ExerciseModel("Crucifixo", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.PEITORAL), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Máquina adutora", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.ADUTORES), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Encolhimento de ombros", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.TRAPEZIO), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Gêmeos sentado", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.PANTURRILHA), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Crunch com bola", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.ABDOMEN), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Remada baixa", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DORSAL), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Extensão de tríceps com corda", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.TRICEPS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Elevação frontal", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Elevação de pernas suspensa", Intensity.BAIXA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.ABDOMEN), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Máquina flexora", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.ISQUIOTIBIAIS), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Rosca scott", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.BICEPS), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Mergulho", Intensity.ALTA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.PEITORAL, MuscleGroupsEnum.TRICEPS),
					                  ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Crunch na máquina", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.ABDOMEN), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Tríceps na polia", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.TRICEPS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Elevação de calcanhares", Intensity.BAIXA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.PANTURRILHA), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Voador inverso", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Máquina abdutora", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.ADUTORES, MuscleGroupsEnum.ADUTORES),
					                  ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Rosca punho", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.ANTEBRACO), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Cadeira flexora", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.ISQUIOTIBIAIS), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Crunch na bola", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.ABDOMEN), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Puxada neutra", Intensity.ALTA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.DORSAL, MuscleGroupsEnum.BICEPS),
					                  ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Tríceps coice", Intensity.MEDIA, ExerciseType.FORCA,
					                  Arrays.asList(MuscleGroupsEnum.TRICEPS), ExerciseDifficulty.INTERMEDIARIO),
					new ExerciseModel("Agachamento hack", Intensity.ALTA, ExerciseType.LEVANTAMENTO_PESO,
					                  Arrays.asList(MuscleGroupsEnum.QUADRICEPS, MuscleGroupsEnum.GLUTEOS,
					                                MuscleGroupsEnum.ISQUIOTIBIAIS), ExerciseDifficulty.EXPERIENTE),
					new ExerciseModel("Alongamento de quadríceps", Intensity.BAIXA, ExerciseType.ALONGAMENTO,
					                  List.of(MuscleGroupsEnum.QUADRICEPS), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Alongamento de panturrilha", Intensity.BAIXA, ExerciseType.ALONGAMENTO,
					                  List.of(MuscleGroupsEnum.PANTURRILHA), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Alongamento de ombros", Intensity.BAIXA, ExerciseType.ALONGAMENTO,
					                  List.of(MuscleGroupsEnum.DELTOIDES), ExerciseDifficulty.INICIANTE),
					new ExerciseModel("Torção da coluna vertebral", Intensity.BAIXA, ExerciseType.ALONGAMENTO,
					                  List.of(MuscleGroupsEnum.LOMBAR), ExerciseDifficulty.INICIANTE)

			);
		} else {
			return null;
		}
	}

}
