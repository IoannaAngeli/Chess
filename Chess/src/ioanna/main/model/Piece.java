package ioanna.main.model;

import ioanna.main.model.exceptions.InvalidMoveException;
import ioanna.main.model.utils.Color;

public abstract class Piece {
	
	protected Color color;
	protected Location location;
	protected Board board;
	protected String symbol; 
	
	public Piece(Color color, Location location, String symbol, Board board) {
		super();
		this.color = color;
		this.location = location;
		this.location.setPiece(this);
		this.symbol = symbol;
		this.board = board;
	}
	
	/**
	 * Implements the movement of the piece in a new position. 
	 * If the move is valid, it performs it, changing the position of the piece on the chessboard, 
	 * removing an opponent piece that may be in the final position, etc.
	 * @param newLocation
	 * @throws InvalidMoveException
	 */
	public abstract void moveToLocation(Location newLocation) throws InvalidMoveException;
	
	/**
	 * Checks if the movement of the chosen piece is valid.
	 * @throws InvalidMoveException 
	 */
	protected abstract boolean isValidMove(Location To) throws InvalidMoveException;
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return color.equals(Color.BLACK)?symbol.toLowerCase():symbol;
	}

}
