package ioanna.main.model;

import ioanna.main.model.exceptions.InvalidMoveException;
import ioanna.main.model.utils.Color;

public class King extends Piece {

	public King(Color color, Location location, Board board) {
		super(color, location, "K", board);
	}

	@Override
	public void moveToLocation(Location newLocation) throws InvalidMoveException {
		if (isValidMove(newLocation)) {
			board.movePiece(location, newLocation);
		} else {
			throw new InvalidMoveException(InvalidMoveException.KING);
		}

	}

	@Override
	public boolean isValidMove(Location locTo) {

		if (location.isVerticalTo(locTo)
				&& ((locTo.getRow() == 1 + location.getRow()) || (locTo.getRow() == location.getRow() - 1))) {
			return true;
		} else if (location.isHorizontalTo(locTo)
				&& (locTo.getCol() == 1 + location.getCol() || locTo.getCol() == location.getCol() - 1)) {
			return true;
		} else if (location.isAntiDiagonalTo(locTo)
				&& (locTo.getCol() == 1 + location.getCol() || locTo.getCol() == location.getCol() - 1)) {
			return true;
		} else if (location.isDiagonalTo(locTo)
				&& (locTo.getCol() == 1 + location.getCol() || locTo.getCol() == location.getCol() - 1)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toStringType() {
		return "King ";
	}

}
