package exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception to throw when data requested is not found
 *
 */
public class DataNotFoundException extends RestException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3470557818776201155L;

	/**
	 * @param code
	 */
	public DataNotFoundException(String code) {
		super(code, HttpStatus.NOT_FOUND);
	}

}
