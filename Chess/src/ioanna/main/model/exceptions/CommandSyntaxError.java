package ioanna.main.model.exceptions;

public class CommandSyntaxError extends Exception {

	private static final long serialVersionUID = -2181442287885194391L;

	public static final String FALSE_COMMAND = "\n! ! ! The command you chose is not correct."
			+ "\nYou can choose one of the following commands.\n" 
			+ ":h – Show the help menu \n"
			+ ":s – Save this game\n\" "
			+ ":o – Open a game\n\" "
			+ ":x – Exit\n";

	public CommandSyntaxError(String string) {
		super(string);
		
	}

}
