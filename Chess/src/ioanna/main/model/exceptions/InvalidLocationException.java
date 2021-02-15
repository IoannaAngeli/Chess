package ioanna.main.model.exceptions;

public class InvalidLocationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	
	public InvalidLocationException(String string) {
		message = string;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
