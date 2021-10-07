package com.bna.cash.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class ImageModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String type;
	
	@Column(name = "picByte", length = 1000)
	
	private String path;

	public ImageModel(String name, String type, String path) {
		this.name = name;
		this.type = type;
		this.path = path;
	}

	public ImageModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    @ManyToOne
    @JoinColumn(name="file_request")
    private Compte account;
	
	
//	@ManyToOne
//	@JoinColumn(name="REQUESTED_BY_ID")
//	private User ApprouvedBy ;
//	
	
	
}
