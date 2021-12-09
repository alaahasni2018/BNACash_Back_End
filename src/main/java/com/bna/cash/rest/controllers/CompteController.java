package com.bna.cash.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bna.cash.entities.Compte;
import com.bna.cash.repositories.CompteRepository;
import com.bna.cash.rest.dto.CompteDto;
import com.bna.cash.services.CompteService;

@RestController
@RequestMapping(value = "/comptes")
public class CompteController {
	@Autowired
	CompteService compteService ; 
	
	@Autowired
	CompteRepository compteRepository ; 
	
	@GetMapping
	public List<Compte> getComptes(){
		return (List<Compte>) compteRepository.findAll();
	}
	
	@GetMapping(value = "/{compteId}")
	public CompteDto getCompteById(@PathVariable Long compteId){
		return compteService.getCompteById(compteId);
	}
	
	
	@PostMapping
	public ResponseEntity<?> addAccount(@RequestBody CompteDto compteDto){
		compteService.addAccount(compteDto);
		return ResponseEntity.status(HttpStatus.OK).body(compteDto);
	}
	
	@PutMapping(value = "/approuver/{compteId}")
	public ResponseEntity<?> approuverCompte(@PathVariable Long compteId){
		compteService.approuverCompte(compteId);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@PutMapping(value = "/refuser/{compteId}")
	public ResponseEntity<?> refuserCompte(@PathVariable Long compteId){
		compteService.refuserComtpe(compteId);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	


}
