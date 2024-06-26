package edu.mateus.Gym.services;

import edu.mateus.Gym.models.builder.UserBuilder;
import edu.mateus.Gym.dtos.RequestUserModelDTO;
import edu.mateus.Gym.dtos.ResponseUserModelDTO;
import edu.mateus.Gym.models.UserModel;
import edu.mateus.Gym.repositorys.UserRepository;
import edu.mateus.Gym.exceptions.FieldNotValidException;
import edu.mateus.Gym.exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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


	public ResponseUserModelDTO createUser(@Valid RequestUserModelDTO requestUsermodelDTO) {

		try {
			var created = repository.save(UserBuilder.buildUserModel(requestUsermodelDTO));
			return UserBuilder.buildResponseDTO(created);
		} catch (ConstraintViolationException exception) {
			throw new FieldNotValidException();
		}

	}


	public ResponseUserModelDTO getUserById(Long id) {

		return UserBuilder.buildResponseDTO(
				repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found")));

	}


	public ResponseUserModelDTO editUser(RequestUserModelDTO userDTO, Long id) {

		var userToEdit = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));

		try {
			BeanUtils.copyProperties(userDTO, userToEdit, "id");
			UserModel updatedUser = repository.save(userToEdit);
			return UserBuilder.buildResponseDTO(updatedUser);

		} catch (ConstraintViolationException exception) {
			throw new FieldNotValidException();
		}
	}


	public void deleteUser(Long id) {
		repository.deleteById(id);
	}

}
