package com.bna.cash.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
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

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private String RIB ;

	    private Date dateCreation;

	    private String  solde;
	    
	    public String proprietaire ; 
	    
	    private String photo ; 
	    
	    private String cinRecto ;
	    
	    private String cinVerso ; 
	    
	    private String signature ; 
	    
	    @Enumerated(EnumType.STRING)
		private com.bna.cash.enums.Status status;

	    @Enumerated(javax.persistence.EnumType.STRING)
	    private TypeCompte type;
	    
	    
//	    @OneToOne(fetch = FetchType.LAZY, optional = false)
//	    @JoinColumn(name = "Carte_id")
//	    private Carte carte;
	    
	    
	    @ManyToOne
		@JoinColumn(name="REQUESTED_BY_ID")
		private User ApprouvedBy ;
		
		@ManyToOne
		@JoinColumn(name="VALIDATED_BY_ID")
		private User requestedBy ;
	    
		@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private Set<ImageModel> files ; 
		

	

}
