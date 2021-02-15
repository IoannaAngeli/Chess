package ioanna.main.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ioanna.main.model.exceptions.InvalidMoveException;
import ioanna.main.model.utils.Color;

//This class represents the chessboard. Contains the game pieces.

public class Board {
	public Location[][] locations;
	List<Piece> whiteCaptured;
	List<Piece> blackCaptured;
	boolean isKingCaptured;
	//Game game;

	public Board() {
		locations = new Location[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				locations[i][j] = new Location(i, j);
			}
		}
		whiteCaptured = new ArrayList<>();
		blackCaptured = new ArrayList<>();
		isKingCaptured = false;
	}



	/**
	 * Creates the pieces that correspond to the beginning of the game, and 
	 * places them in their original positions.
	 */
	public void init() {
		// BLACK
		Rook r1 = new Rook(Color.BLACK, locations[0][0], this);
		Rook r2 = new Rook(Color.BLACK, locations[0][7], this);
		Knight n1 = new Knight(Color.BLACK, locations[0][1], this);
		Knight n2 = new Knight(Color.BLACK, locations[0][6], this);
		Bishop b1 = new Bishop(Color.BLACK, locations[0][2], this);
		Bishop b2 = new Bishop(Color.BLACK, locations[0][5], this);
		Queen q1 = new Queen(Color.BLACK, locations[0][3], this);
		King k1 = new King(Color.BLACK, locations[0][4], this);
		for (int i = 0; i < 8; i++) {
			new Pawn(Color.BLACK, locations[1][i], this);
		}

		// WHITE
		Rook r3 = new Rook(Color.WHITE, locations[7][0], this);
		Rook r4 = new Rook(Color.WHITE, locations[7][7], this);
		Knight n3 = new Knight(Color.WHITE, locations[7][1], this);
		Knight n4 = new Knight(Color.WHITE, locations[7][6], this);
		Bishop b3 = new Bishop(Color.WHITE, locations[7][2], this);
		Bishop b4 = new Bishop(Color.WHITE, locations[7][5], this);
		Queen q2 = new Queen(Color.WHITE, locations[7][4], this);
		King k2 = new King(Color.WHITE, locations[7][3], this);
		for (int i = 0; i < 8; i++) {
			new Pawn(Color.WHITE, locations[6][i], this);
		}
	}
	

	
	/**
	 * Returns the Location, given an input String.
	 * @param str
	 * @return
	 */
	
	public Location getLocation(String str)
	{
		Location currentLoc = new Location(str);
		return locations[currentLoc.getRow()][currentLoc.getCol()];
	}


	/**
	 * Returns the piece in a specific location.
	 * @param loc
	 * @return
	 */
	public Piece getPieceAt(Location loc) 
	{
		return locations[loc.getRow()][loc.getCol()].getPiece();
	}
	
	

	/**
	 * Calls the method which moves the piece that is in the 'from' position, 
	 * in the 'to' position, on the chessboard. When this method is called, 
	 * the validity of the movement has already been confirmed. 
	 * @param from
	 * @param to
	 * @throws InvalidMoveException
	 */
	public void movePiece(Location from, Location to) throws InvalidMoveException 
	{
		if(getPieceAt(to)==null)
		{
			movePieceWithoutCapturing(from, to);
		}
		else if (getPieceAt(from).color!=getPieceAt(to).color)
		{
			movePieceCapturing(from, to);
		}
		else 
		{
			throw new InvalidMoveException(InvalidMoveException.YOU_CAN_NOT_CAPTURE_YOUR_PIECE);
		}
	}
	
	
	/**
	 * Implements the movement of the piece that is in the 'from' position, 
	 * in the 'to' position, on the chessboard, when there is no (opponent) piece to capture.
	 * @param from
	 * @param to
	 */
	public void movePieceWithoutCapturing(Location from, Location to) 
	{
		to.setPiece(getPieceAt(from));
		from.setPiece(null);
	}


	/**
	 * Implements the movement of the piece that is in the 'from' position, 
	 * in the 'to' position, on the chessboard, when there is a (opponent) piece to capture. 
	 * @param from
	 * @param to
	 */

	public void movePieceCapturing(Location from, Location to) {
		//the captured piece
		Piece captured = getPieceAt(to);
		if(captured.color== Color.BLACK)
		{
			blackCaptured.add(captured);
		}
		else
		{
			whiteCaptured.add(captured);
		}
		//has no location on the board
		System.out.println(captured.toString() +"was captured.");
		if(captured.symbol.equals("K"))
		{
			System.out.println(" ============== End of Game. ==================");
			
		}
		captured.setLocation(null);
		
		to.setPiece(getPieceAt(from));
		from.setPiece(null);
	}

	/**
	 * Returns true if the path from 'from'position to 'to' position is free. 
	 * Used when checking the validity of the movement of the Rook and the Queen.
	 * The start and end positions are not checked (as the moving part is in the start position, 
	 * and the status of the end position is checked separately).
	 * When the method is called, it has already been confirmed that the from and to positions 
	 * are on the same line of the chessboard.
	 * @param from
	 * @param to
	 * @return
	 */

	public boolean freeHorizontalPath(Location from, Location to) 
	{
		// constant row- increasing column
		if(from.getCol()<to.getCol())
		{
			for(int i= from.getCol()+ 1; i< to.getCol(); i++)
			{
				if (locations[from.getRow()][i].getPiece()==null)
					{
					continue;
					}
				else 
				{
					return false;
				}
			}
		}
		// constant row- decreasing column
		else if(from.getCol()>to.getCol())
			for(int i= to.getCol()+ 1; i< from.getCol();i++)
			{
				if (locations[from.getRow()][i].getPiece()==null)
					{
					continue;
					}
				else 
				{
					return false;
				}
			}

		return true;
	
	}

	/**
	 * Returns true if the vertical path from 'from'position to 'to' position is free. 
	 * Used when checking the validity of the movement of the tower and the queen.
	 * The start and end positions are not checked (as the moving part is in the start position, 
	 * and the status of the end position is checked separately).
	 * When the method is called, it has already been confirmed that the 'from' and 'to' positions 
	 * are on the same line of the chessboard.
	 * @param from
	 * @param to
	 * @return
	 * @throws InvalidMoveException 
	 */

	public boolean freeVerticalPath(Location from, Location to) throws InvalidMoveException {
			if(from.getRow()<to.getRow())
			{
				for(int i= from.getCol()+ 1; i< to.getCol(); i++)
				{
					if (locations[i][from.getCol()].getPiece()==null)
						{
						continue;
						}
					else 
					{
						throw new InvalidMoveException(InvalidMoveException.OBSTACLE);
					}
				}
			}
			else if(from.getCol()>to.getCol())
				for(int i= to.getCol()+ 1; i< from.getCol();i++)
				{
					if (locations[i][from.getCol()].getPiece()==null)
						{
						continue;
						}
					else 
					{
						throw new InvalidMoveException(InvalidMoveException.OBSTACLE);
					}
				}

			return true;
	
	}

	/**
	 * Returns true if the diagonal path from 'from'position to 'to' position is free. 
	 * Examples of diagonal paths: a2c4, e7b4
	 * Used when checking the validity of the movement of the Bishop and the Queen.
	 * The start and end positions are not checked (as the moving part is in the start position, 
	 * and the status of the end position is checked separately).
	 * When the method is called, it has already been confirmed that the 'from' and 'to' positions 
	 * are on the same line of the chessboard.
	 * 
	 * @param from
	 * @param to
	 * @return
	 * @throws InvalidMoveException 
	 */

	public boolean freeDiagonalPath(Location from, Location to) throws InvalidMoveException {
		
		int row = from.getRow() -1;
		int col = from.getCol() +1;
		
		while(col<to.getCol()) {
			
			if(locations[row][col]==null)
			{
				row++;
				col++;
				continue;
			}
			else
			{
				throw new InvalidMoveException(InvalidMoveException.OBSTACLE);
			}		
		}
		return true;
	}

	/**
	 * Returns true if the anti-diagonal path from 'from'position to 'to' position is free. 
	 * Examples of anti-diagonal paths: b6g1, d3a6
	 * Used when checking the validity of the movement of the Bishop and the Queen.
	 * The start and end positions are not checked (as the moving part is in the start position, 
	 * and the status of the end position is checked separately).
	 * When the method is called, it has already been confirmed that the 'from' and 'to' positions 
	 * are on the same line of the chessboard.
	 * 
	 * @param from
	 * @param to
	 * @return
	 * @throws InvalidMoveException    
	 */

	public boolean freeAntidiagonalPath(Location from, Location to) throws InvalidMoveException{
				int row = from.getRow() +1;
				int col = from.getCol() +1;
				
				while(row<to.getRow()) {
					
					if(locations[row][col]==null)
					{
						row++;
						col++;
						continue;
					}
					else
					{
						throw new InvalidMoveException(InvalidMoveException.OBSTACLE);
					}					
				}
		return true;
	}

	
	/**
	 * Returns the representation of the chessboard in text form.
	 */
	public void printTheBoard() {

		System.out.println("     "+" abcdefgh");
		for (int i = 0; i < 8; i++) {
			System.out.print("     "+ (8 - i));
			for (int j = 0; j < 8; j++) {

				if (locations[i][j].getPiece() != null) {
					System.out.print(locations[i][j].getPiece().toString());
				} else {
					System.out.print(" ");
				}
			}
			System.out.print(8 - i);
			System.out.println();
		}
		System.out.println("     "+" abcdefgh");
	}

	
}
