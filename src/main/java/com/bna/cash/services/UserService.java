package com.bna.cash.services;


import java.util.List;

import com.bna.cash.entities.Compte;
import com.bna.cash.entities.ImageModel;
import com.bna.cash.entities.User;
import com.bna.cash.rest.dto.RegisterDto;
import com.bna.cash.rest.dto.UpdatePdwDto;
import com.bna.cash.rest.dto.UserDto;


public interface UserService {

	UserDto getCurrentUser();
	
	List<UserDto> getAllUsers();
	
	void register ( RegisterDto registerDto);
	
	void updatePwd (UpdatePdwDto updatePdwDto);

	void uploadFile(ImageModel imageModel);
		
	public void verifyAccount(String token) ;


}
