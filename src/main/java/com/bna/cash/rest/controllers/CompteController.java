package com.bna.cash.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bna.cash.entities.Compte;
import com.bna.cash.repositories.CompteRepository;
import com.bna.cash.rest.dto.CompteDto;
import com.bna.cash.services.CompteService;

@RestController
@RequestMapping(value = "/account")
public class CompteController {
	@Autowired
	CompteService compteService ; 
	
	@Autowired
	CompteRepository compteRepository ; 
	
	@GetMapping(value ="/comptes")
	public List<Compte> getComptes(){
		return (List<Compte>) compteRepository.findAll();
	}
	
	
	@PostMapping(value = "/compte")
	public ResponseEntity<?> addAccount(@RequestBody CompteDto compteDto){
		compteService.addAccount(compteDto);
		return ResponseEntity.status(HttpStatus.OK).body(compteDto);
		
	}
	
	
	


}
