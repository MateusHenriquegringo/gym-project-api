package edu.mateus.Gym.Users.builder;


import edu.mateus.Gym.Users.dtos.RequestUserModelDTO;
import edu.mateus.Gym.Users.dtos.ResponseUserModelDTO;
import edu.mateus.Gym.Users.models.UserModel;
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
				.sex(requestUserModelDTO.sex())
				.password(requestUserModelDTO.password())
				.build();
	};

	public static ResponseUserModelDTO buildResponseDTO(UserModel model){
		return ResponseUserModelDTO.builder()
				.id(model.getId())
				.age(model.getAge())
				.sex(model.getSex())
				.name(model.getName())
				.height(model.getHeight())
				.weight(model.getWeight())
				.build();
	};
}
