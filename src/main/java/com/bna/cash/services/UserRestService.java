package com.bna.cash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bna.cash.entities.User;
import com.bna.cash.repositories.UserRepository;

@RestController
public class UserRestService {
	
	@Autowired
	private UserRepository userRepository ; 

	@RequestMapping(value="/addUser")
	public User save(User u) {
		return userRepository.save(u);
	}
	@RequestMapping(value="/findUsers")
	public java.util.List<User> findall(){
		return userRepository.findAll();
	}
}
