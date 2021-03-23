package ioanna.main.model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File; // Import the File class
import java.io.FileNotFoundException;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors

import ioanna.main.model.exceptions.InvalidMoveException;
import ioanna.main.model.utils.Color;

public class Game {

	private Board board;
	private int turn;
	private boolean gameInAction;
	private ArrayList<String> validMoves;
	Scanner sc = new Scanner(System.in);

	public Game(Board board) {
		super();
		this.board = board;
		gameInAction = true;
		turn = 0;
		validMoves = new ArrayList<>();

	}

	public void play() {

		System.out.println("\nThis is the board of your chess game:\n");
		board.printTheBoard();
		printHelp();

		while (gameInAction == true) {
			if(board.isKingCaptured == true)
			{
				break;
			}

			if (turn == 0) {
				System.out.print("\nWhite plays. Please insert your move: ");
			} else {
				System.out.print("\nBlack plays. Please insert your move: ");
			}

			String round = sc.nextLine();
			handleInput(round);
		}
	}

	private void changeTurn() {
		turn = (turn + 1) % 2;
	}

	/**
	 * Handles player's input. Identifies if it is a command or a movement.
	 * 
	 * @param moveString
	 */

	public void handleInput(String moveString) {
		moveString = moveString.trim();
		Pattern patternCommand = Pattern.compile(":[hsox]", Pattern.CASE_INSENSITIVE);
		Pattern patternMovement = Pattern.compile("[a-h][1-8][a-h][1-8]", Pattern.CASE_INSENSITIVE);

		Matcher commandMatcher = patternCommand.matcher(moveString);
		Matcher movementMatcher = patternMovement.matcher(moveString);

		boolean commandMatchFound = commandMatcher.find();
		boolean movementMatchFound = movementMatcher.find();

		if (commandMatchFound) {
			System.out.println("\n             =========================");
				handleCommand(moveString);
		} else if (movementMatchFound && moveString.length() == 4) {
			System.out.println("=====>");
			handleMove(moveString);
		} else {
			System.out.println("\n!!! This is not a valid input. Please type a move or choose one of the available commands.\n"
					+ "(You can type':h' for the help Menu.)\n");
			
		}

	}

	public void handleCommand(String moveString){
		switch (moveString) {
		case ":h":
			printHelp();
			break;
		case ":s":
			saveGame();
			break;
		case ":o":
			openGame();
			break;
		case ":x":
			if (exitGame()) {
				this.gameInAction = false;
			}
			break;
		default:
			break;
			
		}
	}

	public void handleMove(String moveString) {

		Location from = board.getLocation(moveString.substring(0, 2));
		Location to = board.getLocation(moveString.substring(2, 4));

		Piece pieceToMove = from.getPiece();

		try {
			if (!thereIsPieceInLoc(from)) {
				throw new InvalidMoveException(InvalidMoveException.THERE_IS_NO_PIECE_THERE_TO_MOVE);
			}
			if (!checkIfPieceToMoveMatchesPlayer(from)) {
				throw new InvalidMoveException(InvalidMoveException.NOT_YOUR_PIECE);
			}

			pieceToMove.moveToLocation(to);
			validMoves.add(moveString);
			changeTurn();
		} catch (InvalidMoveException e) {
			System.out.println(e.getMessage());
		}

		board.printTheBoard();
	}

	/**
	 * Returns true if there is a piece in the 'from' position.
	 * 
	 * @throws InvalidMoveException
	 */

	private boolean thereIsPieceInLoc(Location loc) {
		if (board.getPieceAt(loc) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if the color of the piece matches player's color
	 * 
	 * @throws InvalidMoveException
	 */

	private boolean checkIfPieceToMoveMatchesPlayer(Location locTo) {
		if ((turn == 0 && board.getPieceAt(locTo).color.equals(Color.WHITE))
				|| (turn == 1 && board.getPieceAt(locTo).color.equals(Color.BLACK))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Asks for a name in order to save the active game.
	 */
	public void saveGame() {
		System.out.print("Please enter the name of this game: ");
		String gameName = sc.nextLine();
		gameName = gameName.trim();
		//System.out.println(validMoves.toString());
		File myObj = null;
		try {
			myObj = new File("C:\\Users\\Ioanna\\Desktop\\Chess\\" + gameName + ".txt");
			if (myObj.createNewFile()) {
				//System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("A game with this name already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			FileWriter myWriter = new FileWriter(myObj);
			for (int i = 0; i < validMoves.size(); i++) {
				myWriter.write(validMoves.get(i) + "\n");
			}

			myWriter.close();
			System.out.println("This game was saved as " + gameName + ".");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * Asks for the game to be opened.
	 */
	public void openGame() {
		// System.out.println("Game opened-not");
		System.out.print("Please write the name of the game you would like to open: ");
		String gameToOpen = sc.nextLine();

		ArrayList<String> savedMoves = readMovesFromFile(gameToOpen);
		for (int i = 0; i < savedMoves.size(); i++) {
			String move = savedMoves.get(i).toString();
			handleMove(move);
		}
		play();
	}

	private ArrayList<String> readMovesFromFile(String nameOfSavedGame) {
		
		ArrayList<String> result = new ArrayList<>();
		
        Scanner scanner;
		try {
			scanner = new Scanner(new File("C:\\Users\\Ioanna\\Desktop\\Chess\\"+nameOfSavedGame+".txt"));
			
			while (scanner.hasNextLine()) {
				result.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println("There is no saved game with this name. You can continue playing the current game.");
			//e.printStackTrace();
		}


		return result;
	}

	/**
	 * Since there is an active game, the player has to confirm if they want to stop
	 * the game. If the answer is yes, then the game stops.
	 * 
	 * @return
	 */
	public boolean exitGame() {
		System.out.print("Are you sure you want to exit this game? \nPlease type 'yes' or 'no': ");
		String answer = sc.nextLine().trim();
		if (answer.toLowerCase().equals("yes")) {
			System.out.println("Bye!");
			return true;
		} else if (answer.toLowerCase().equals("no")) {
			board.printTheBoard();
			return false;
		} else {
			System.out.println("You typed neither yes, nor no. You can keep playing.\n");
			board.printTheBoard();
			return false;
		}
	}
	
	

	/**
	 * It prints a text with rules on how to use this application
	 */
	public void printHelp() {
		System.out.println("                  ==============================================\n"
				+ "To move a piece you have to write the coordinates\n"
				+ "(the letter of the column and the number of the row) of its current position.\n"
				+ "Then, (without a space) you have to write the coordinates of the location you want to move this piece to.\n"
				+ "  ==> For example to move a pawn from the location b2 to location b3, you have to write ==>  b2b3.\n"
				+ "\nFurthermore, you can choose one of the following commands.\n" + ":h – Show the help menu\r\n"
				+ ":s – Save this game\n" + ":o – Open a game\n" + ":x – Exit\n"
				+ "                  ==============================================\n");
	}

}
