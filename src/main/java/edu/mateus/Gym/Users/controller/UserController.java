package edu.mateus.Gym.Users.controller;

import edu.mateus.Gym.Users.dtos.RequestUserModelDTO;
import edu.mateus.Gym.Users.dtos.ResponseUserModelDTO;
import edu.mateus.Gym.Users.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.PublicKey;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;


	@GetMapping
	public ResponseEntity<List<ResponseUserModelDTO>> getAllUsers() {

		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
	}


	@PostMapping
	public ResponseEntity<ResponseUserModelDTO> createUser(@Valid @RequestBody RequestUserModelDTO requestUserModelDTO) {
		userService.createUser(requestUserModelDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
