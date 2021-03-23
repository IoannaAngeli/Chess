package ioanna.main.model.exceptions;

public class InvalidMoveException extends Exception {

	private static final long serialVersionUID = 107658340781114333L;


	public static final String YOU_CAN_NOT_CAPTURE_YOUR_PIECE = "! ! ! Wrong play! You moved your piece to another square where piece of yours is resting!\n";
	public static final String THERE_IS_NO_PIECE_THERE_TO_MOVE = "\n! ! ! There is no piece of yours in this square.\n";
	public static final String NOT_YOUR_PIECE = "\n ! ! ! You tried to move your opponent's piece.\n";

	public static final String PAWN = "\n! ! ! Pawns move and capture in different ways: they move forward, but capture one square diagonally in front of them. "
			+ "\n! ! ! Pawns can only move forward one square at a time, "
			+ "\n! ! ! except for their very first move where they can move forward two squares. "
			+ "\n! ! ! If there is another piece directly in front of a pawn, it cannot move past or capture that piece.\n";
	public static final String PAWN_NOT_FIRSTMOVE = "\n! ! !The pawn can move forward two squares only for their very first move \n";
	public static final String PAWN_CAPTURE_ONLY_DIAGONALLY = "\n! ! ! The pawn can capture a piece only by moving one square diagonally in front of them. \n";
	public static final String PAWN_ONLY_FORWARD = "\n! ! ! Pawns can move forward only.They can never move or capture backwards.\n";

	public static final String KING = "\n! ! ! The king can only move one square in any direction - up, down, to the sides, and diagonally.\n";

	public static final String QUEEN = "\n! ! ! The queen can move in any one straight direction - "
			+ "\n! ! ! forward, backward, sideways, or diagonally - as far as possible "
			+ "\n! ! ! as long as she does not move through any of her own pieces.\n";

	public static final String BISHOP = "\n! ! ! The bishop may move as far as it wants, but only diagonally.\n";

	public static final String ROOK = "\n! ! ! The rook may move as far as it wants, but only forward, backward, and to the sides.\n";

	public static final String KNIGHT = "\n! ! ! The knight can move two squares in one direction, "
			+ "and then one more move at a 90 degree angle, just like the shape of an “L”\n";

	public static final String OBSTACLE = "\n! ! ! The path you chose is not free.\n";

	public InvalidMoveException(String string) {
		super(string);
	}

}
