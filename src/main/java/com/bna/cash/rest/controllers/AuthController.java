package com.bna.cash.rest.controllers;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bna.cash.repositories.UserRepository;
import com.bna.cash.rest.dto.LoginRequest;
import com.bna.cash.rest.dto.LoginResponse;
import com.bna.cash.rest.dto.SignupRequest;
import com.bna.cash.security.config.JWTAuthorizationFilter;

import exceptions.BadRequestException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.userdetails.User;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
		try {
			if (loginRequest.getLogin().isEmpty() || loginRequest.getPassword().isEmpty()) {
				throw new BadRequestException("MISSING_REQUIRED_DATA_LOGIN_PASSWORD");
			}

			String jwt = null;
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
				User userPrincipal =(User) authentication.getPrincipal();
				Optional<com.bna.cash.entities.User> dbuser = userRepository.findByLogin(userPrincipal.getUsername());
				jwt = Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date())
						.setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
						.signWith(SignatureAlgorithm.HS256, JWTAuthorizationFilter.SECRET)
						.claim("nom", dbuser.get().getNom() + " " + dbuser.get().getPrenom())
						.claim("roles", userPrincipal.getAuthorities()).compact();
				LoginResponse response = new LoginResponse();
				response.setJwt(jwt);
			return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
		} catch (AuthenticationException e) {
			throw new BadRequestException("AUTHENTICATION_FAILED");
		}
	}
	
	
}
