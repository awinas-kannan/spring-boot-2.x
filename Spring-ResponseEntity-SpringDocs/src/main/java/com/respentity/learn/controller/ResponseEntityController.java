package com.respentity.learn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.respentity.learn.beans.User;
import com.respentity.learn.service.ResponseEntityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/responseentity/")
@Slf4j
public class ResponseEntityController {

	@Autowired
	private ResponseEntityService service;

	@GetMapping(path = "/users/{id}")
	@Operation(summary = "Get User based on User id ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success|OK", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "204", description = "Invalid User Id", content = @Content) })
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		log.debug("getUserById");
		Optional<User> user = service.getUserById(id);
		if (user.isPresent()) {
			System.out.println("user.isPresent() true");
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			System.out.println("user.isPresent() false");
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(path = "/users")
	public ResponseEntity<List<User>> getUsers() {
		log.debug("getUsers");
		return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
	}
}
