package ioanna.main.model;

import ioanna.main.model.utils.Color;

public abstract class Piece {
	
	protected Color color;
	protected Location location;
	protected String symbol; //panta kefalaio
	

	
	public Piece(Color color, Location location, String symbol) {
		super();
		this.color = color;
		this.location = location;
		this.location.setPiece(this);
		this.symbol = symbol;
	}


	/**
	 * 	�������� ��� ������ ��� ��������� �� ��� ��� ����. ������ � ������ ����� ������ ��� �������������, ���������� �� ���� ��� ��������� ��� ��������, �������������� �������� �������
	 * ��� ����� ������� ���� ������ ����, �.��. �� ����������� ��������� �������� ��� �������� ���
	 * ����� InvalidMoveException (��. ��������) �� ��������� ��������������.
	 * @param newLocation
	 */
	abstract void moveToLocation(Location newLocation);
	
	
	@Override
	public String toString() {
		return color.equals(Color.BLACK)?symbol.toLowerCase():symbol;
	}

}
