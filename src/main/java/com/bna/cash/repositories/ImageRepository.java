package com.bna.cash.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bna.cash.entities.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long>{
	
	//v	Optional<ImageModel> findByName(String name);

	Optional<ImageModel> findByName(String name);

}
