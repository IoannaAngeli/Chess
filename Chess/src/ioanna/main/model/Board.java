package ioanna.main.model;

import java.util.Arrays;

import ioanna.main.model.utils.Color;

public class Board {
	public Location[][] locations;

	public Board() {
		locations = new Location[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				locations[i][j] = new Location(i, j);
			}
		}
	}

//	� ����� ���� �������������� �� ��������. �������� �� �������� ��� ���������� � ���� � ���
//	������ ������������ ����� �� ���� ������ ��� ���������� ��� ������ �� �������� ����� ��������
//	(Piece). �������� ��� ��� � ����� ��� ��������� ��� ��� ��������� ����� ��������� (� ��������
//	�������� �� ��������, ���� ��� �� �������� ��������� �� �������� ���� ����� ����������), ���� ���
//	����� ���������� ��� ��� ��������� ��� ����������.
//	� ����� �������� ����������� ��� ��������� �������� ��� ����� ����� �� ��� �������� ��� ��������� 
//  ���� ��� ��������:

	/**
	 * ���������� �� �������� ��� ������������ ���� ���� ��� ���������� ��� ��
	 * ��������� ���� ������� ������ ����.
	 */
	public void init() {
		// BLACK
		Rook r1 = new Rook(Color.BLACK, locations[0][0]);
		Rook r2 = new Rook(Color.BLACK, locations[0][7]);
		Knight n1 = new Knight(Color.BLACK, locations[0][1]);
		Knight n2 = new Knight(Color.BLACK, locations[0][6]);
		Bishop b1 = new Bishop(Color.BLACK, locations[0][2]);
		Bishop b2 = new Bishop(Color.BLACK, locations[0][5]);
		Queen q1 = new Queen(Color.BLACK, locations[0][3]);
		King k1 = new King(Color.BLACK, locations[0][4]);
		for(int i=0; i<8; i++)
		{
			new Pawn(Color.BLACK, locations[1][i]);
		}

		// WHITE
		Rook r3 = new Rook(Color.WHITE, locations[7][0]);
		Rook r4 = new Rook(Color.WHITE, locations[7][7]);
		Knight n3 = new Knight(Color.WHITE, locations[7][1]);
		Knight n4 = new Knight(Color.WHITE, locations[7][6]);
		Bishop b3 = new Bishop(Color.WHITE, locations[7][2]);
		Bishop b4 = new Bishop(Color.WHITE, locations[7][5]);
		Queen q2 = new Queen(Color.WHITE, locations[7][4]);
		King k2 = new King(Color.WHITE, locations[7][3]);
		for(int i=0; i<8; i++)
		{
			new Pawn(Color.WHITE, locations[6][i]);
		}

	}

//	Piece getPieceAt(Location loc)

	/**
	 * ���������� �� ������� ��� ��������� ��� ���� loc.
	 */
//	Piece getPieceAt(Location loc) 
//	{
//		return new Piece();
//	}

	/**
	 * �������� ��� �������� ��� ������ ��� ��������� ��� ��������� ��� ���� from,
	 * ��� ���� to. ���� �������� ���� � �������, ���� ��� ������������ � ����������
	 * ��� �������. ��� ���� to ��� ������� (��������) �������, ��� ��� �������
	 * ����������� ���������.
	 */
	void movePiece(Location from, Location to) {
		from.getCol();
		from.getRow();
	}

	/**
	 * �������� ��� �������� ��� ������ ��� ��������� ��� ��������� ��� ���� from,
	 * ��� ���� to. ���� �������� ���� � �������, ���� ��� ������������ � ����������
	 * ��� �������. ��� ���� to ������� (��������) �������, �� ����� �������������
	 * ��� �� ��������.
	 */

	void movePieceCapturing(Location from, Location to) {

	}

	/**
	 * ���������� true �� � �������� ��� �� ���� from ��� ���� to ����� ��������.
	 * ��������������� ���� ��� ������ ����������� ��� ������� ��� ������ ��� ���
	 * ����������. � ������ ��� � ������ ���� ��� ���������� (����� ���� ������ ����
	 * ��������� �� ������� ��� ��������, ��� � ��������� ��� ������� �����
	 * ��������� �������). ���� �������� � �������, ���� ��� ������������ ��� ��
	 * ������ from ��� to ���������� ���� ���� ������ ��� ���������.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */

	boolean freeHorizontalPath(Location from, Location to) {
		return true;

	}

	/**
	 * ���� � ����������� �������, ���� ��� ��� ������ ������� ���������. �������
	 * ����������� ���- ������ ��� ��� ������ ��� ��� ������ ����.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */

	boolean freeVerticalPath(Location from, Location to) {
		return true;
	}

	/**
	 * ���� � ����������� �������, ���� ��� ��� ������ ��������� ��������� ���
	 * ������ �/�, �.�. a2c4 � e7b4. ������� ����������� ��������� ��� ��� ������
	 * ��� ��� ������ ����.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */

	boolean freeDiagonalPath(Location from, Location to) {
		return true;
	}

	/**
	 * ���� � ����������� �������, ���� ��� ��� ������ ��������� ��������� ���
	 * ������ �\�, �.�. b6g1 � d3a6. ������� ����������� ��������� ��� ��� ������
	 * ��� ��� ������ ����.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */

	boolean freeAntidiagonalPath(Location from, Location to) {
		return true;
	}

	/**
	 * ���������� ��� ������������ ��� ��������� �� ����� ��������, ���� ��������
	 * ��� ����� 1 (��������).
	 */

//	@Override
//	public String toString() {
//		System.out.println("here");
//		return Arrays.toString(list);
//		//return "Board [locations=" + Arrays.toString(locations) + "]";
//	}

	public void printTheBoard() {
		
		System.out.println(" abcdefgh");
		for (int i = 0; i < 8; i++) {
			System.out.print(8-i);
			for (int j = 0; j < 8; j++) {
				
				if (locations[i][j].getPiece() != null) {
					System.out.print(locations[i][j].getPiece().toString());
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.print(8-i);
			System.out.println();
		}
		System.out.println(" abcdefgh");
	}

}
