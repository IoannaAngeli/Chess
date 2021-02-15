package ioanna.main.model;

public class Location {

	private int row;
	private int column;
	private Piece piece;
	/**
	 * Constructor, accepts the row and column number of the position. 
	 * It saves the row and column numbers as integers 0–7. 
	 * For this method, we assume that both parameters have valid values.
	 */
	public Location(int r, int c) {
		row = r;
		column = c;
	}

	/**
	 * Μέθοδος δημιουργίας, δέχεται μία συμβολοσειρά της μορφής «e2» και την αναλύει
	 * δίνοντας κατάλληλες τιμές στα πεδία του αντικειμένου για τη γραμμή και τη
	 * στήλη. Υποθέστε ότι η συμβολοσειρά loc έχει μήκος 2 (ακριβώς) αλλά άγνωστη
	 * μορφή. Σε περίπτωση σφάλματος, η μέθοδος προκαλεί μία εξαίρεση του τύπου
	 * InvalidLocationException (βλ. παρακάτω).
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
	 * Returns the row of the board as 0-7
	 * @return
	 */
	int getRow() {
		return row;
	}

	/**
	 * Returns the column of the board as 0-7
	 * @return
	 */
	int getCol() {
		return column;
	}

	
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
		if(piece!= null)
		{
			piece.setLocation(this);
		}
	}
	
	
	/**
	 * returns true if the beginning location is vertical to 'to' location
	 * @param to
	 * @return
	 */
	public boolean isVerticalTo(Location to)
	{
		return this.column == to.column;
	}
	
	/**
	 * returns true if the beginning location is horizontal to 'to' location
	 * @param to
	 * @return
	 */
	public boolean isHorizontalTo(Location to)
	{
		return this.row == to.row;
	}
	
	/**
	 * returns true if the beginning location is diagonal to 'to' location
	 * @param to
	 * @return
	 */
	public boolean isDiagonalTo(Location to)
	{
		return (this.row + this.column) == (to.row + to.column);
	}
	
	
	/**
	 * returns true if the beginning location is anti-diagonal to 'to' location
	 * @param to
	 * @return
	 */
	public boolean isAntiDiagonalTo(Location to)
	{	
		return (this.row-to.row) == this.column - to.column;
	}

	/**
	 * Returns the location as a letter and a number eg. «e2»
	 */
	@Override
	public String toString() {
		//String rowString = String.valueOf(8-row);
		int columnAsciiValue=column+97;
		char c= (char)(columnAsciiValue); 
		
		return "" + c + (8-row); //ok edw typwnei ara den me noizei ti morfis 8a einai
	}

	
	

}
