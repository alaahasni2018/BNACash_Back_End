package com.bna.cash.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bna.cash.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findUserById(Long id) ; 
	
	Optional<User> findByLogin(String login);
	
	
	



}
