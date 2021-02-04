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
	 * 	Υλοποιεί την κίνηση του κομματιού σε μία νέα θέση. Εφόσον η κίνηση είναι έγκυρη την πραγματοποιεί, αλλάζοντας τη θέση του κομματιού στη σκακιέρα, απομακρύνοντας αντίπαλο κομμάτι
	 * που τυχόν υπάρχει στην τελική θέση, κ.λπ. Σε διαφορετική περίπτωση προκαλεί μία εξαίρεση του
	 * τύπου InvalidMoveException (βλ. παρακάτω) με κατάλληλα χαρακτηριστικά.
	 * @param newLocation
	 */
	abstract void moveToLocation(Location newLocation);
	
	
	@Override
	public String toString() {
		return color.equals(Color.BLACK)?symbol.toLowerCase():symbol;
	}

}
