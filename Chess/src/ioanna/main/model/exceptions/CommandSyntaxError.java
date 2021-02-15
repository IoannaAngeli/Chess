package ioanna.main.model.exceptions;

public class CommandSyntaxError extends Exception {

	private static final long serialVersionUID = -2181442287885194391L;
	String message;

	public CommandSyntaxError(String string) {
		message = string;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
