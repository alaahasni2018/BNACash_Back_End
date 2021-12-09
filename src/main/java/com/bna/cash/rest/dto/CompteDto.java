package com.bna.cash.rest.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.bna.cash.entities.Carte;
import com.bna.cash.entities.User;

import lombok.Data;

@Data
public class CompteDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date dateCreation;
        
    private String photo ; 
    
    private String cinRecto ;
    
    private String cinVerso ; 
    
    private String signature ; 

	private UserDto requestedBy;
	
	private UserDto validatedBy;
	
	private List<DocumentDto> documents;

}
