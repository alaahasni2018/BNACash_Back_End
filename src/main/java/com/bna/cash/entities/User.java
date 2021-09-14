package com.bna.cash.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom ;

    private String prenom;

    private String adresse;

    private Date date_naissance;

    private String num_gsm;

    private String email;

    private String mdp;
    
	@ManyToOne
	@JoinColumn(name="COMPTE_ID")
	private Compte compte ;
    
	
	@ManyToOne
	@JoinColumn(name="OPERATION_ID")
	private Operation operation ;
    
	
	
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
