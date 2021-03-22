package ioanna.main.model;

import ioanna.main.model.exceptions.InvalidMoveException;
import ioanna.main.model.utils.Color;

public class Knight extends Piece{

	public Knight(Color color, Location location, Board board) {
		super(color, location,"N", board);
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
          

		int twoMovesBack = this.location.getRow() - 2;
		int twoMovesFront = this.location.getRow() + 2;
		int twoMovesRight = this.location.getCol() - 2;
		int twoMovesLeft = this.location.getCol() + 2;
		int oneMoveBack = this.location.getRow() - 1;
		int oneMoveFront = this.location.getRow() + 1;
		int oneMoveRight = this.location.getCol() - 1;
		int oneMoveLeft = this.location.getCol() + 1;
		
		if(locTo.getRow()==twoMovesBack && (locTo.getCol()==oneMoveLeft||locTo.getCol()==oneMoveRight))
		{
			return true;
		}
		else if (locTo.getRow()==twoMovesFront && (locTo.getCol()==oneMoveLeft||locTo.getCol()==oneMoveRight))
		{
			return true;
		}
		else if (locTo.getRow()==twoMovesLeft && (locTo.getCol()==oneMoveBack||locTo.getCol()==oneMoveFront))
		{
			return true;
		}
		else if (locTo.getRow()==twoMovesRight && (locTo.getCol()==oneMoveBack||locTo.getCol()==oneMoveFront))
		{
			return true;
		}
		else
			{
			return false;
			}
	}
	

	public String toStringType() {
		return "Knight ";
	}

}
