package edu.mateus.Gym.controllers;

import edu.mateus.Gym.dtos.RequestUserModelDTO;
import edu.mateus.Gym.dtos.ResponseUserModelDTO;
import edu.mateus.Gym.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;


	@GetMapping
	public ResponseEntity<List<ResponseUserModelDTO>> getAllUsers() {

		return ResponseEntity.status(HttpStatus.OK)
				.body(userService.getAllUser());
	}


	@PostMapping
	public ResponseEntity<ResponseUserModelDTO> createUser(
			@Valid @RequestBody RequestUserModelDTO requestUserModelDTO) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.createUser(requestUserModelDTO));
	}


	@GetMapping("/{id}")
	public ResponseEntity<ResponseUserModelDTO> findUserById(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(userService.getUserById(id));
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {

		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}


	@PutMapping("/{id}")
	public ResponseEntity<ResponseUserModelDTO> updateUserById(@PathVariable Long id,
	                                                           @RequestBody @Valid RequestUserModelDTO userDTO) {

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.editUser(userDTO, id));
	}

}
