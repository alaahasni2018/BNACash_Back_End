package com.bna.cash.services.impl ;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bna.cash.entities.ImageModel;
import com.bna.cash.entities.User;
import com.bna.cash.entities.VerificationToken;
import com.bna.cash.enums.TypeUsers;
import com.bna.cash.repositories.CompteRepository;
import com.bna.cash.repositories.ImageRepository;
import com.bna.cash.repositories.UserRepository;
import com.bna.cash.repositories.VerificationTokenRepository;
import com.bna.cash.rest.dto.RegisterDto;
import com.bna.cash.rest.dto.UpdatePdwDto;
import com.bna.cash.rest.dto.UserDto;
import com.bna.cash.services.MailService;
import com.bna.cash.services.UserService;

import exceptions.BadRequestException;
import exceptions.DataNotFoundException;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	VerificationTokenRepository verificationTokenRepository;

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
		User user = new User() ; 
		BeanUtils.copyProperties(registerDto, user);
		// String randomPwd = RandomStringUtils.randomAlphanumeric(8);
		String encodedPwd = passwordEncoder.encode(user.getMdp());
		user.setMdp(encodedPwd);
		user.setType(TypeUsers.CLIENT);
		user.setEnabled(false);
		// mailService.sendMail("Nouveau mot de passe", randomPwd, registerDto.getEmail());
		userRepository.save(user);
		String token = generateVerificationToken(user);
		 mailService.sendMail("Confirmation d'enregistrement",
				"http://localhost:4200/login?token="+ token , registerDto.getEmail());

	}
	
	 private String generateVerificationToken(User user) {
	        String token = UUID.randomUUID().toString();
	        VerificationToken verificationToken=new VerificationToken();
	        verificationToken.setToken(token);
	        verificationToken.setUser(user);
	        verificationTokenRepository.save(verificationToken);
	        return token;
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
	public void verifyAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new DataNotFoundException("Invalid Token."));
        fetchUserAndEnable(verificationToken);
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        Long username = verificationToken.getUser().getId();
        User user = userRepository.findById(username).orElseThrow(() ->
                new DataNotFoundException("User with name "+ username+" not found."));
        user.setEnabled(true);
        userRepository.save(user);
        
    }


	
}





//if(StringUtils.isEmpty(registerDto.getNom())) {
//throw new BadRequestException("Champ obligatoire : Veuillez introduire votre nom");
//}
//if(StringUtils.isEmpty(registerDto.getPrenom())) {
//throw new BadRequestException("Champ obligatoire : Veuillez introduire votre prénom");
//}
//if(StringUtils.isEmpty(registerDto.getAdresse())) {
//throw new BadRequestException("Champ obligatoire : Veuillez introduire votre adresse");
//}
//if(StringUtils.isEmpty(registerDto.getTel())) {
//throw new BadRequestException("Champ obligatoire : Veuillez introduire votre Numéro de Téléphone");
//}
//if(StringUtils.isAlphanumericSpace(registerDto.getTel())) {
//throw new BadRequestException("Merci de saisir le bon numéro  ");
//}
//if(StringUtils.isEmpty(registerDto.getEmail())) {
//throw new BadRequestException("Champ obligatoire : Veuillez introduire votre adresse mail");
//}
//if(StringUtils.isEmpty(registerDto.getLogin())) {
//throw new BadRequestException("Champ obligatoire : Veuillez introduire votre Login");
//}
//
