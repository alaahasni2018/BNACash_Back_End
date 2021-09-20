package com.bna.cash.rest.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;

    private String nom ;

    private String prenom;

    private String adresse;

    private Date dateNaissance;

    private String tel;

    private String email;
    
	private String login;


   
    

}
