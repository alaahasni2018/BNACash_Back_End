package com.bna.cash.rest.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DocumentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3391201677114832000L;

	private Long id;

	private String name;

	private String type;

	private byte[] content;
}
