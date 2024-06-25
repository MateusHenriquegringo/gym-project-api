package edu.mateus.Gym.Exercises.models;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Relation(collectionRelation = "exercises")
@Table(name="EXERCISE")
public class ExerciseModel extends RepresentationModel<ExerciseModel> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_id_seq")
	@SequenceGenerator(name = "exercise_id_seq", initialValue = 50, allocationSize = 1)
	@Column(name="exercise_id", unique = true)
	private Long id;

	@Column(unique = true)
	@NotBlank
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
