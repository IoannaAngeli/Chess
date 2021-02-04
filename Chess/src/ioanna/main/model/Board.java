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

//	Η κλάση αυτή αντιπροσωπεύει τη σκακιέρα. Περιέχει τα κομμάτια του παιχνιδιού  ίσως η πιο
//	εύκολη αναπαράσταση είναι με έναν πίνακα δύο διαστάσεων του οποίου τα στοιχεία είναι κομμάτια
//	(Piece). Προσέξτε εδώ ότι η σχέση των κομματιών και της σκακιέρας είναι αμφίδρομη (η σκακιέρα
//	περιέχει τα κομμάτια, αλλά και τα κομμάτια γνωρίζουν τη σκακιέρα στην οποία βρίσκονται), κάτι που
//	είναι απαραίτητο για την υλοποίηση του παιχνιδιού.
//	Η κλάση υλοποιεί τουλάχιστον τις ακόλουθες μεθόδους που έχουν σχέση με τον χειρισμό των κομματιών 
//  πάνω στη σκακιέρα:

	/**
	 * Δημιουργεί τα κομμάτια που αντιστοιχούν στην αρχή του παιχνιδιού και τα
	 * τοποθετεί στις αρχικές θέσεις τους.
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
	 * Επιστρέφει το κομμάτι που βρίσκεται στη θέση loc.
	 */
//	Piece getPieceAt(Location loc) 
//	{
//		return new Piece();
//	}

	/**
	 * Υλοποιεί στη σκακιέρα την κίνηση του κομματιού που βρίσκεται στη θέση from,
	 * στη θέση to. Όταν καλείται αυτή η μέθοδος, έχει ήδη επιβεβαιωθεί η εγκυρότητα
	 * της κίνησης. Στη θέση to δεν υπάρχει (αντίπαλο) κομμάτι, άρα δεν γίνεται
	 * απομάκρυνση κομματιού.
	 */
	void movePiece(Location from, Location to) {
		from.getCol();
		from.getRow();
	}

	/**
	 * Υλοποιεί στη σκακιέρα την κίνηση του κομματιού που βρίσκεται στη θέση from,
	 * στη θέση to. Όταν καλείται αυτή η μέθοδος, έχει ήδη επιβεβαιωθεί η εγκυρότητα
	 * της κίνησης. Στη θέση to υπάρχει (αντίπαλο) κομμάτι, το οποίο απομακρύνεται
	 * από τη σκακιέρα.
	 */

	void movePieceCapturing(Location from, Location to) {

	}

	/**
	 * Επιστρέφει true αν η διαδρομή από τη θέση from στη θέση to είναι ελεύθερη.
	 * Χρησιμοποιείται κατά τον έλεγχο εγκυρότητας της κίνησης του πύργου και της
	 * βασίλισσας. Η αρχική και η τελική θέση δεν ελέγχονται (καθώς στην αρχική θέση
	 * βρίσκεται το κομμάτι που κινείται, και η κατάσταση της τελικής θέσης
	 * ελέγχεται χωριστά). Όταν καλείται η μέθοδος, έχει ήδη επιβεβαιωθεί ότι οι
	 * θέσεις from και to βρίσκονται στην ίδια γραμμή της σκακιέρας.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */

	boolean freeHorizontalPath(Location from, Location to) {
		return true;

	}

	/**
	 * Όπως η προηγούμενη μέθοδος, αλλά για τον έλεγχο κάθετης διαδρομής. Ισχύουν
	 * αντίστοιχες υπο- θέσεις για την αρχική και την τελική θέση.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */

	boolean freeVerticalPath(Location from, Location to) {
		return true;
	}

	/**
	 * Όπως η προηγούμενη μέθοδος, αλλά για τον έλεγχο διαγώνιας διαδρομής της
	 * μορφής «/», π.χ. a2c4 ή e7b4. Ισχύουν αντίστοιχες υποθέσεις για την αρχική
	 * και την τελική θέση.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */

	boolean freeDiagonalPath(Location from, Location to) {
		return true;
	}

	/**
	 * Όπως η προηγούμενη μέθοδος, αλλά για τον έλεγχο διαγώνιας διαδρομής της
	 * μορφής «\», π.χ. b6g1 ή d3a6. Ισχύουν αντίστοιχες υποθέσεις για την αρχική
	 * και την τελική θέση.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */

	boolean freeAntidiagonalPath(Location from, Location to) {
		return true;
	}

	/**
	 * Επιστρέφει την αναπαράσταση της σκακιέρας σε μορφή κειμένου, όπως φαίνεται
	 * στο σχήμα 1 (αριστερά).
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
