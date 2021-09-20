package com.bna.cash.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bna.cash.entities.User;
import com.bna.cash.rest.dto.RegisterDto;
import com.bna.cash.rest.dto.UpdatePdwDto;
import com.bna.cash.rest.dto.UserDto;
import com.bna.cash.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/currentUser")
	public ResponseEntity<UserDto> getCurrentUser() {
		UserDto user = userService.getCurrentUser();
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
		userService.register(registerDto);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@PutMapping(value = "/updateMpd")
	public ResponseEntity<?> updateMdp(@RequestBody UpdatePdwDto updatePdwDto) {
		userService.updatePwd(updatePdwDto);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	} 
}
