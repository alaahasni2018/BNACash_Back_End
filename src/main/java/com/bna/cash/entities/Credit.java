package com.bna.cash.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Credit extends Operation {

//	   @Id
//	    @GeneratedValue(strategy = GenerationType.AUTO)
//	    private Long id;

	    private Date date_versemenent;
	    
}
