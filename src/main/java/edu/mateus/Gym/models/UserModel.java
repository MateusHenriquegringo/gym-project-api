package edu.mateus.Gym.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.mateus.Gym.tools.DecimalJsonSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "tb_users")
public class UserModel  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, name = "user_id")
	private Long id;

	@Column
	@NonNull
	private String name;

	@Column
	private Integer age;

	@Column
	@Email
	private String email;

	@Column
	@DecimalMax(value = "300.00", message = "weight must be smaller than 300KG")
	@DecimalMin(value = "35.00", message = "height must be bigger than 30KG")
	@JsonSerialize(using = DecimalJsonSerializer.class)
	private Double weight;

	@DecimalMax(value = "2.20", message = "height must be smaller than 2.20M")
	@DecimalMin(value = "1.10", message = "height must be bigger than 1.10M")
	@Column
	@JsonSerialize(using = DecimalJsonSerializer.class)
	private Double height;

	@Column
	@NonNull
	@Size(min = 8, message = "short password")
	private String password;


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "tb_users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Roles> roles;


}