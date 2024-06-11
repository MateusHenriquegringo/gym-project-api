package edu.mateus.Gym.Users.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.mateus.Gym.Users.enums.Roles;
import edu.mateus.Gym.Users.enums.SexEnum;
import edu.mateus.Gym.Users.tools.DecimalJsonSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "USERS")
public class UserModel  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, name = "user_id")
	private Long id;

	@Column
	@NonNull
	private String name;

	@Column
	@NonNull
	private Integer age;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private SexEnum sex;

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

	@NonNull
	@Column(unique = true)
	@Email
	private String email;

	@Column
	@NonNull
	@Size(min = 8, message = "short password")
	private String password;

	@Column
	@Enumerated(EnumType.STRING)
	private Roles roles;


}