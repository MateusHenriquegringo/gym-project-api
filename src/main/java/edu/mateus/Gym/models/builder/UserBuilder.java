package edu.mateus.Gym.models.builder;


import edu.mateus.Gym.dtos.RequestUserModelDTO;
import edu.mateus.Gym.dtos.ResponseUserModelDTO;
import edu.mateus.Gym.models.UserModel;
import jakarta.validation.Valid;
import lombok.AccessLevel;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBuilder {
	public static UserModel buildUserModel(@Valid RequestUserModelDTO requestUserModelDTO){
		return UserModel.builder()
				.name(requestUserModelDTO.name())
				.age(requestUserModelDTO.age())
				.height(requestUserModelDTO.height())
				.weight(requestUserModelDTO.weight())
				.password(requestUserModelDTO.password())
				.build();
	}

	public static ResponseUserModelDTO buildResponseDTO(UserModel model){
		return ResponseUserModelDTO.builder()
				.id(model.getId())
				.age(model.getAge())
				.name(model.getName())
				.height(model.getHeight())
				.weight(model.getWeight())
				.build();
	}
}
