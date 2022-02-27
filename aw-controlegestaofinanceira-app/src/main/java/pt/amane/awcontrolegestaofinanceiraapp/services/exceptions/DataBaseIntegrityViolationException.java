package pt.amane.awcontrolegestaofinanceiraapp.services.exceptions;

public class DataBaseIntegrityViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataBaseIntegrityViolationException(String message) {
		super(message);
	}
	
	

}
