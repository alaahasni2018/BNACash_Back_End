package com.bna.cash.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bna.cash.entities.User;
import com.bna.cash.repositories.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByLogin(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
//		if(user.get().getFlagActif() != null && user.get().getFlagActif()) {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.get().getType().name()));
//			List<Profile> listProfil = profileService.findListProfilofUserLogin(username);
//			for (int i = 0; i < listProfil.size(); i++) {
//				authorities.add(new SimpleGrantedAuthority(listProfil.get(i).getId().getRoleId()));
//			}
		return new org.springframework.security.core.userdetails.User(user.get().getLogin(), user.get().getMdp(),
				authorities);
//		}
	}

}
