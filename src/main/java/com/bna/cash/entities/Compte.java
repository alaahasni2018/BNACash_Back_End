package com.bna.cash.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.bna.cash.enums.TypeCompte;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Compte implements Serializable{

	  @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private Long RIB ;

	    private Date date_creation;

	    private String  solde;

	    @Enumerated(javax.persistence.EnumType.STRING)
	    private TypeCompte type;
	    
	    
	    @OneToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "Carte_id", nullable = false)
	    private Carte carte;
	

}
