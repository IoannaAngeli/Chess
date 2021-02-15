package ioanna.main.model.exceptions;

public class InvalidMoveException extends Exception {

	
//	3. Μη ύπαρξη κομματιού στην αρχική θέση.
//	4. Κίνηση λάθος χρώματος κομματιού, π.χ. κίνηση μαύρου κομματιού όταν παίζουν τα άσπρα.
//	5. Μη επιτρεπόμενη κίνηση για το κομμάτι, π.χ. κίνηση για πιόνι d2d5. Σε αυτή την περίπτωση πρέπει
//	να φαίνεται και ο τύπος του κομματιού που κινείται, και είναι επιθυμητό το μήνυμα να παρέχει
//	όσο το δυνατόν περισσότερες πληροφορίες για το σφάλμα της κίνησης, π.χ. «Ο πύργος μπορεί να
//	κινηθεί μόνο οριζόντια ή κατακόρυφα».
	private static final long serialVersionUID = 107658340781114333L;
	
	public static final String YOU_CAN_NOT_CAPTURE_YOUR_PIECE = 
			"Wrong play! You moved your piece to another square where piece of yours is resting!";
	public static final String THERE_IS_NO_PIECE_THERE_TO_MOVE = 
			"\nThere is no piece of yours in this square.";
	public static final String NOT_YOUR_PIECE = 
			"\nYou tried to move your opponent's piece.";
	
	public static final String LA8OS_KINISI = 
			"\nTo piono sou den mporei na kini8ei etsi";
	public static final String PAWN =  
			"\nPawns move and capture in different ways: they move forward, but capture one square diagonally in front of them. "
			+ "Pawns can only move forward one square at a time, "
			+ "except for their very first move where they can move forward two squares. "
			+ "If there is another piece directly in front of a pawn he cannot move past or capture that piece.";
	public static final String PAWN_NOT_FIRSTMOVE = 
			"\nThe pawn can move forward two squares only for their very first move ";
	public static final String PAWN_CAPTURE_ONLY_DIAGONALLY = 
			"\nThe pawn can capture a piece only by moving one square diagonally in front of them. ";
	public static final String PAWN_ONLY_FORWARD = 
			"\nPawns can move forward only.They can never move or capture backwards.";
	public static final String KING = 
			"\nThe king can only move one square in any direction - up, down, to the sides, and diagonally.";
	public static final String QUEEN = 
			"\nThe queen can move in any one straight direction - "
			+ "forward, backward, sideways, or diagonally - as far as possible "
			+ "as long as she does not move through any of her own pieces.";
	public static final String BISHOP = 
			"\nThe bishop may move as far as it wants, but only diagonally.";
	public static final String ROOK = 
			"\nThe rook may move as far as it wants, but only forward, backward, and to the sides.";
	public static final String KNIGHT = 
			"\nThe knight can move two squares in one direction, "
			+ "and then one more move at a 90 degree angle, just like the shape of an “L”";
	public static final String OBSTACLE = 
			"\nThe path you chose is not free.";

	public InvalidMoveException(String string) {
		super(string);
	}
	
	
//	public InvalidMoveException(String string) {
//		message = string;
//	}
//	@Override
//	public String getMessage() {
//		return message;
//	}

}
