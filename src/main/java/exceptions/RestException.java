package exceptions;

import org.springframework.http.HttpStatus;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Global rest exception
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class RestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7114696519435576049L;
	
	/**
	 * The code
	 */
	private String code;
	
	/**
	 * The status
	 */
	private HttpStatus status;

}
