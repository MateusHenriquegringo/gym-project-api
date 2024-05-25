package edu.mateus.Gym.Users.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.mateus.Gym.Users.enums.SexEnum;
import edu.mateus.Gym.Users.tools.DecimalJsonSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.IOException;


@Entity
@Data
@Table(name = "users")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, name = "user_id")
	private Long id;

	@Column
	@NotBlank
	private String name;

	@Column
	@NotBlank
	private Integer age;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private SexEnum sex;

	@Column
	@DecimalMax(value = "300.00", message = "peso deve ser menor que 300KG")
	@DecimalMin(value = "35.00", message = "peso deve ser maior que 30KG")
	@JsonSerialize(using = DecimalJsonSerializer.class)
	private Double weight;

	@DecimalMax(value = "2.20", message = "peso deve ser menor que 2.20M")
	@DecimalMin(value = "1.10", message = "peso deve ser maior que 1.30M")
	@Column
	@JsonSerialize(using = DecimalJsonSerializer.class)
	private Double height;


}