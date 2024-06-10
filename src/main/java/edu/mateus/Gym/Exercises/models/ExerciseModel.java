package edu.mateus.Gym.Exercises.models;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@Entity
@Table(name="EXERCISE")
public class ExerciseModel extends RepresentationModel<ExerciseModel> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="exercise_id", unique = true)
	private Long id;

	@Column(unique = true)
	@NonNull
	private String name;

	@Column
	@NonNull
	@Enumerated(EnumType.STRING)
	private ExerciseIntensity intensity;

	@NonNull
	@Column(name = "exercise_type")
	@Enumerated(EnumType.STRING)
	private ExerciseType type;


	@NonNull
	@Column
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = MuscleGroupsEnum.class)
	@CollectionTable(name = "MUSCLES")
	private List<MuscleGroupsEnum> muscles;

	@Column
	@Enumerated(EnumType.STRING)
	@NonNull
	private ExerciseDifficulty difficulty;

}
