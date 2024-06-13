package edu.mateus.Gym.Users.services;

import edu.mateus.Gym.Users.builder.UserBuilder;
import edu.mateus.Gym.Users.dtos.RequestUserModelDTO;
import edu.mateus.Gym.Users.dtos.ResponseUserModelDTO;
import edu.mateus.Gym.Users.exeptions.EmailNotValidException;
import edu.mateus.Gym.Users.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;


	public List<ResponseUserModelDTO> getAllUser() {

		List<ResponseUserModelDTO> users = new ArrayList<>();

		repository.findAll().forEach(user -> users.add(UserBuilder.buildResponseDTO(user)));

		return users;
	}


	;


	public void createUser(RequestUserModelDTO requestUsermodelDTO) {

		try {
			repository.save(UserBuilder.buildUserModel(requestUsermodelDTO));
		} catch (ConstraintViolationException exception) {
			throw new EmailNotValidException();
		}


	}


	;

}
