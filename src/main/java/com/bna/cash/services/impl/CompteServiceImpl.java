package com.bna.cash.services.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bna.cash.entities.Compte;
import com.bna.cash.entities.User;
import com.bna.cash.enums.TypeCompte;
import com.bna.cash.enums.TypeUsers;
import com.bna.cash.repositories.CompteRepository;
import com.bna.cash.repositories.ImageRepository;
import com.bna.cash.rest.dto.CompteDto;
import com.bna.cash.rest.dto.UserDto;
import com.bna.cash.services.CompteService;
import com.bna.cash.services.UserService;



@Service
public class CompteServiceImpl implements CompteService{

	@Autowired
	private CompteService compteService;
	
	@Autowired
	private CompteRepository compteRepository ;
	
	@Autowired
	private ImageRepository imgImageRepository ;

	@Autowired
	private SecurityService securityService; 
	
	@Autowired
	private UserService userService ; 
	
	@Override
	public void addAccount(CompteDto compteDto) {
		Compte cpt = new Compte();
		//String Uploadimg = imgImageRepository.get ; 
		User user = securityService.getTheCurrentUser();
		BeanUtils.copyProperties(compteDto , cpt);
		userService.uploadFile(null);
		cpt.setStatus(com.bna.cash.enums.Status.WAITING);
		compteRepository.save(cpt) ; 
	}
	
	
	@Override
	public List<CompteDto> getAllAccounts() {
		List<CompteDto> compteDtos = new ArrayList<>();
		List<Compte> comptes = compteRepository.findAll();
		for(Compte compte : comptes) {
			CompteDto  compteDto = new CompteDto();
			BeanUtils.copyProperties(compte, compteDto);
			//compteDto.add(compteDto);
		}
		return compteDtos;
	}
	

}
