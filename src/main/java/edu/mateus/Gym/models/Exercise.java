package edu.mateus.Gym.models;

import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.enums.ExerciseType;
import edu.mateus.Gym.enums.Intensity;
import edu.mateus.Gym.enums.MuscleGroups;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="exercises")
public class Exercise {
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
	private Intensity intensity;

	@NonNull
	@Column(name = "exercise_type")
	@Enumerated(EnumType.STRING)
	private ExerciseType type;

	@NonNull
	@Column
	@ElementCollection(targetClass = MuscleGroups.class)
	@CollectionTable(name="muscles",joinColumns = @JoinColumn(name = "exercise_id"))
	@Enumerated(EnumType.STRING)
	//transformar em uma entidade
	private List<MuscleGroups> muscles;

	@Column
	@Enumerated(EnumType.STRING)
	@NonNull
	private ExerciseDifficulty difficulty;
}
