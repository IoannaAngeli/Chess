package ioanna.main.model;

public class Location {

	private int row;
	private int column;
	private Piece piece;
	/**
	 * ������� �����������, ������� ��� ������ ������� ��� ������ ��� �����. �� ���
	 * ������������ �� ������������ ���� �������� ������� ��� ������ �� ��������
	 * ����� 0�7. ��� �� ������ ���� �������� ��� �� ��� ���������� ����� �������
	 * �����.
	 */
	public Location(int r, int c) {
		row = r;
		column = c;
	}

	/**
	 * ������� �����������, ������� ��� ������������ ��� ������ �e2� ��� ��� �������
	 * �������� ���������� ����� ��� ����� ��� ������������ ��� �� ������ ��� ��
	 * �����. �������� ��� � ������������ loc ���� ����� 2 (�������) ���� �������
	 * �����. �� ��������� ���������, � ������� �������� ��� �������� ��� �����
	 * InvalidLocationException (��. ��������).
	 * 
	 * @param loc
	 */
	public Location(String loc) {

//		a->0		'1'->1->7
//		b->1		'2'->2->6
//		c->2		'3'->3->5
//		d->3		'4'->4->4
//		e->4		'5'->5->3
//		f->5		'6'->6->2
//		g->6		'7'->7->1
//		h->7		'8'->8->0
		
		char columnChar = loc.charAt(0);
		char rowChar = loc.charAt(1);
//		System.out.println(rowChar - '0');
		row = rowChar - '0';
		row = 8-row;
		column = columnChar - 'a';
		
	}

	/**
	 * ����������� �� ������ ��� �� ����� ��� �����, ��� ����� 0�7.
	 * @return
	 */
	int getRow() {
		return row;

	}

	int getCol() {
		return column;
	}

	
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	/**
	 * ���������� ��� ���������� �������� ��� �����, �.�. �e2�.
	 */
	@Override
	public String toString() {
		//String rowString = String.valueOf(8-row);
		int columnAsciiValue=column+97;
		char c= (char)(columnAsciiValue); 
		
		return "" + c + (8-row); //ok edw typwnei ara den me noizei ti morfis 8a einai
	}

	
	

}
