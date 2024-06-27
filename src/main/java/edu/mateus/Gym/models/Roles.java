package edu.mateus.Gym.models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_roles")
@Data
public class Roles {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer id;

	@Column()
	private String name;


	@AllArgsConstructor
	@Getter
	public enum Values {

		ADMIN(1),
		USER(2),
		TRAINER(3);

		Integer roleId;
	}

}
