package edu.mateus.Gym.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_exercise_details")
public class ExerciseDetails {

	@Id
	@Column(name = "detail_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne
	private ExerciseModel model;

	@Column
	private Long repetitions;

	@Column
	private Long sets;
}
