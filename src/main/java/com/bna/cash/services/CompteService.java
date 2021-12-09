package com.bna.cash.services;

import java.util.List;

import com.bna.cash.rest.dto.CompteDto;
import com.bna.cash.rest.dto.RegisterDto;
import com.bna.cash.rest.dto.UserDto;

public interface CompteService {
	
	void addAccount ( CompteDto compteDto  );
	
	List<CompteDto> getAllAccounts();

	CompteDto getCompteById(Long id);

	void approuverCompte(Long compteId);
	
	void refuserComtpe(Long compteId);

}
