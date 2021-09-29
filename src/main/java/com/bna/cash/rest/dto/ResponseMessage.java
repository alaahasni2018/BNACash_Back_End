package com.bna.cash.rest.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseMessage implements Serializable {


	private String message ; 
	
	
	public ResponseMessage(String string) {
	}

}
