package edu.mateus.Gym.controllers;

import edu.mateus.Gym.dtos.ExercisePlanRequestDTO;
import edu.mateus.Gym.models.ExercisePlan;
import edu.mateus.Gym.services.ExercisePlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/plans")
public class ExercisePlanController {

	@Autowired
	ExercisePlanService service;


	@PostMapping("/{id}/create")
	public ResponseEntity<Void> createExercisePlan(@Valid @RequestBody ExercisePlanRequestDTO plan,
	                                               @PathVariable Long id) {

		service.createExercisePlan(plan, id);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<List<ExercisePlan>> getAllPlans(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllPlans());
	}
}
