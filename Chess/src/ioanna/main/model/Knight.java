package ioanna.main.model;

import ioanna.main.model.exceptions.InvalidMoveException;
import ioanna.main.model.utils.Color;

public class Knight extends Piece {

	public Knight(Color color, Location location, Board board) {
		super(color, location, "N", board);
	}

	@Override
	public void moveToLocation(Location newLocation) throws InvalidMoveException {
		if (isValidMove(newLocation)) {
			board.movePiece(location, newLocation);
		} else {
			throw new InvalidMoveException(InvalidMoveException.KNIGHT);
		}
	}

	@Override
	protected boolean isValidMove(Location locTo) {

		int twoSquaresBack = this.location.getRow() - 2;
		int twoSquaresFront = this.location.getRow() + 2;
		int twoSquaresRight = this.location.getCol() - 2;
		int twoSquaresLeft = this.location.getCol() + 2;
		int oneSquareBack = this.location.getRow() - 1;
		int oneSquareFront = this.location.getRow() + 1;
		int oneSquareRight = this.location.getCol() - 1;
		int oneSquareLeft = this.location.getCol() + 1;

		int targetRow = locTo.getRow();
		int targetCol = locTo.getCol();

		if (targetRow == twoSquaresBack && (targetCol == oneSquareLeft || targetCol == oneSquareRight)) {
			return true;
		} else if (targetRow == twoSquaresFront && (targetCol == oneSquareLeft || targetCol == oneSquareRight)) {
			return true;
		} else if (targetCol == twoSquaresLeft && (targetRow == oneSquareBack || targetRow == oneSquareFront)) {
			return true;
		} else if (targetCol == twoSquaresRight && (targetRow == oneSquareBack || targetRow == oneSquareFront)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toStringType() {
		return "Knight ";
	}

}
