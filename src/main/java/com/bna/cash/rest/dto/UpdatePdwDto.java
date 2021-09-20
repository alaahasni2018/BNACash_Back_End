 package com.bna.cash.rest.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UpdatePdwDto implements Serializable{
	
	private String ancienMdp ;
	
	private String nouveauMpd ; 
	
	private String confimMdp ; 
	
	

}
