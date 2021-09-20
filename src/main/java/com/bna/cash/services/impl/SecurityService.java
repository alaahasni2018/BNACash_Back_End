package com.bna.cash.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.bna.cash.entities.User;
import com.bna.cash.repositories.UserRepository;


@Component
public class SecurityService {
	
	@Autowired
	private UserRepository userRepository;

	public User getTheCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = new String();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return userRepository.findByLogin(username).get();
	}
}
