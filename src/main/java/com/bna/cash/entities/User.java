package com.bna.cash.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.bna.cash.enums.TypeCompte;
import com.bna.cash.enums.TypeUsers;

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

    private Date dateNaissance;

    private String tel;

    private String email;
    
	private String login;

    private String mdp;
    
    @Enumerated(javax.persistence.EnumType.STRING)
    private TypeUsers type;
    
//	@ManyToOne
//	@JoinColumn(name="COMPTE_ID")
//	private Compte compte ;
    
	@OneToMany(mappedBy = "user")
	private List<Compte> comptes;
	
	@ManyToOne
	@JoinColumn(name="OPERATION_ID")
	private Operation operation ;
    
	

    
}
