package com.bna.cash.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bna.cash.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long>{

	
	
}
