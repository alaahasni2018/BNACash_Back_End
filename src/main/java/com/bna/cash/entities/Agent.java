package com.bna.cash.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Agent {
     
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;

	    private String nom ;

	    private String prenom;

	    private String adresse;

	    private String num_gsm;

	    private String email;

	    private String mdp;

	    
		@ManyToOne
		@JoinColumn(name="COMPTE_ID")
		private Compte compte ;
		
				
//		@OneToOne(fetch = FetchType.LAZY,
//	            cascade =  CascadeType.ALL,
//	            mappedBy = "user")
//	    private User Carte_Bancaire;
	    
}
