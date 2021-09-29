package com.bna.cash.services.impl ;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bna.cash.entities.Compte;
import com.bna.cash.entities.ImageModel;
import com.bna.cash.entities.User;
import com.bna.cash.enums.TypeUsers;
import com.bna.cash.repositories.CompteRepository;
import com.bna.cash.repositories.ImageRepository;
import com.bna.cash.repositories.UserRepository;
import com.bna.cash.rest.dto.RegisterDto;
import com.bna.cash.rest.dto.UpdatePdwDto;
import com.bna.cash.rest.dto.UserDto;
import com.bna.cash.services.MailService;
import com.bna.cash.services.UserService;

import exceptions.BadRequestException;



@Service
public class UserServiceImpl implements UserService{
	

	@Autowired
	CompteRepository compteRepository ;

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MailService mailService;
	@Override
	public UserDto getCurrentUser() {
		User connectedUser = securityService.getTheCurrentUser();
		UserDto  user = new UserDto();
		BeanUtils.copyProperties(connectedUser, user);
		System.out.println(connectedUser);
		return user;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> usersDto = new ArrayList<>();
		List<User> users = userRepository.findAll();
		for(User user : users) {
			UserDto  userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			usersDto.add(userDto);
		}
		return usersDto;
	}

	@Override
	public void register(RegisterDto registerDto) {
		
		if(StringUtils.isEmpty(registerDto.getNom())) {
			throw new BadRequestException("Champ obligatoire : Veuillez introduire votre nom");
		}
		if(StringUtils.isEmpty(registerDto.getPrenom())) {
			throw new BadRequestException("Champ obligatoire : Veuillez introduire votre prénom");
		}
		if(StringUtils.isEmpty(registerDto.getAdresse())) {
			throw new BadRequestException("Champ obligatoire : Veuillez introduire votre adresse");
		}
		if(StringUtils.isEmpty(registerDto.getTel())) {
			throw new BadRequestException("Champ obligatoire : Veuillez introduire votre Numéro de Téléphone");
		}
		if(StringUtils.isAlphanumericSpace(registerDto.getTel())) {
			throw new BadRequestException("Merci de saisir le bon numéro  ");
		}
		if(StringUtils.isEmpty(registerDto.getEmail())) {
			throw new BadRequestException("Champ obligatoire : Veuillez introduire votre adresse mail");
		}
		if(StringUtils.isEmpty(registerDto.getLogin())) {
			throw new BadRequestException("Champ obligatoire : Veuillez introduire votre Login");
		}
		
		User user = new User() ; 
		BeanUtils.copyProperties(registerDto, user);
		String randomPwd = RandomStringUtils.randomAlphanumeric(8);
		String encodedPwd = passwordEncoder.encode(randomPwd);
		user.setMdp(encodedPwd);
		user.setType(TypeUsers.CLIENT);
		mailService.sendMail("Nouveau mot de passe", randomPwd, registerDto.getEmail());
		userRepository.save(user);
	}

	@Override
	public void updatePwd(UpdatePdwDto updatePdwDto) {
		
		if(StringUtils.isEmpty(updatePdwDto.getNouveauMpd())) {
			throw new BadRequestException("Missing new password");
		}
		
		if(StringUtils.isEmpty(updatePdwDto.getAncienMdp())) {
			throw new BadRequestException("Missing old password");
		}
		
		if(StringUtils.isEmpty(updatePdwDto.getConfimMdp())) {
			throw new BadRequestException("Missing confirm password");
		}
		
		if (!updatePdwDto.getNouveauMpd().equals(updatePdwDto.getConfimMdp())) {
			throw new BadRequestException("Invalid Password");
		}
		User connectedUser = securityService.getTheCurrentUser();
		if(!passwordEncoder.matches(updatePdwDto.getAncienMdp(), connectedUser.getMdp())) {
			throw new BadRequestException("Invalid old Password");
		}
		
		if(updatePdwDto.getNouveauMpd().length()<8) {
			throw new BadRequestException("Invalid new Password");
		}
		String encodedPwd = passwordEncoder.encode(updatePdwDto.getNouveauMpd());
		connectedUser.setMdp(encodedPwd);
		userRepository.save(connectedUser);
	}
	
	
	
	@Override
	public void uploadFile(ImageModel imageModel) {
		imageRepository.save(imageModel)	;
		}

	@Override
	public Compte getCompte(String codeCompte) {
		// TODO Auto-generated method stub
		return null;
	}

	
//	public Compte getCompte(String codeCompte) {
//		
//		Compte compte = compteRepository.findAll();
//
//		return compte;
//	}
	
//	Compte compte=compteRepository.findOne(codeCompte);
//	 if (compte==null) throw new RuntimeException("Compte introuvable"); // c'est une exception non surveiller
//	return compte;
	
}
