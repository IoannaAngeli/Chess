package ioanna.main.model;

import ioanna.main.model.exceptions.InvalidMoveException;
import ioanna.main.model.utils.Color;

public class Bishop extends Piece {

	public Bishop(Color color, Location location, Board board) {
		super(color, location, "B", board);
	}

	@Override
	public void moveToLocation(Location newLocation) throws InvalidMoveException {
		if (isValidMove(newLocation)) {
			board.movePiece(location, newLocation);
		} else {
			throw new InvalidMoveException(InvalidMoveException.BISHOP);
		}
	}

	@Override
	public boolean isValidMove(Location locTo) throws InvalidMoveException {
		if (location.isDiagonalTo(locTo) && board.freeDiagonalPath(location, locTo)) {
			return true;
		} else if (location.isAntiDiagonalTo(locTo) && board.freeAntidiagonalPath(location, locTo)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toStringType() {
		return "Bishop ";
	}

}
