package edu.mateus.Gym.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "tb_exercise_plan")
public class ExercisePlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, name = "exercise_plan_id")
	private Long id;

	@Column(name = "plan_name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by_id", referencedColumnName = "user_id")
	private UserModel createdBy;

	@Column
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "tb_user_follow_plans",
			joinColumns = @JoinColumn(name = "exercise_plan_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<UserModel> followers;

	@ManyToMany
	@JoinTable(
			name = "exercise_in_plans",
			joinColumns = @JoinColumn(name = "exercise_plan_id"),
			inverseJoinColumns = @JoinColumn(name = "detail_id")
	)
	private List<ExerciseDetails> exercises;

}
