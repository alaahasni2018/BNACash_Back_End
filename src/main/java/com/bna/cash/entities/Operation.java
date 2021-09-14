package com.bna.cash.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long num_operation;

    private Date date_operation;

    private String  montant;

    private String type_operation;
    
    
	@ManyToOne
	@JoinColumn(name="COMPTE_ID")
	private Compte compte ;
    
    
}
