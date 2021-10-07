package com.bna.cash.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bna.cash.entities.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
	
	Optional<VerificationToken> findByToken(String token);
	
	
	

}
