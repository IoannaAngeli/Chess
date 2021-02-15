package ioanna.main.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;  // Import the File class
import java.io.FileReader;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import ioanna.main.model.exceptions.InvalidMoveException;
import ioanna.main.model.utils.Color;

public class Game {
	
	private Board board;
	private int turn;
	private boolean gameInAction;
	private ArrayList<String> validMoves;
	Scanner sc = new Scanner(System.in);

//	Η κλάση αντιπροσωπεύει μία παρτίδα σκάκι. Αποθηκεύει (τουλάχιστον) τη σκακιέρα και γνωρίζει
//	τον παίκτη που παίζει κάθε στιγμή.
//	Εκτός πιθανής μεθόδου δημιουργίας, μόνη public μέθοδος της κλάσης είναι η ακόλουθη:
	
		public Game(Board board) {
		super();
		this.board = board;
		gameInAction = true;
		turn = 0;
		validMoves = new ArrayList<>();
		
	}


		/** 
		 * Υλοποιεί ένα παιχνίδι σκακιού, σύμφωνα με τη διεπαφή που περιγράφηκε παραπάνω. Χειρίζεται
		 * την είσοδο του χρήστη και ενημερώνει για την εξέλιξη του παιχνιδιού.
		 * Για καλύτερη δόμηση του προγράμματος, η κλάση αυτή θα περιέχει και private μεθόδους για την
		 * υλοποίηση των λειτουργιών του προγράμματος, τουλάχιστον τις ακόλουθες:
		 */
		public void play() 
		{
			
//			board.init();
			System.out.println("Hello! \nThis is the board of your chess game:\n");
			board.printTheBoard();
			
			while(gameInAction==true) 
			{
				if(turn==0)
				{
					System.out.print("White plays. Please insert your move: ");
				}
				else 
				{
					System.out.print("Black plays. Please insert your move: ");
				}
				
				String round = sc.nextLine();
				handleInput(round);
			}
		}
	
		private void changeTurn()
		{
			turn = (turn+1)%2;
		}
	
		/** 
		 * 	Όταν η είσοδος του χρήστη δεν ξεκινάει από «:» υποτίθεται ότι είναι η κίνησή του. Η μέθοδος αυτή
		 * δέχεται όλη αυτή την είσοδο του χρήστη και την επεξεργάζεται κατάλληλα ώστε, αν είναι έγκυρη
		 * να πραγματοποιήσει την αντίστοιχη κίνηση, ή διαφορετικά να χειριστεί τις διάφορες εξαιρέσεις
		 * που θα προκληθούν.
		 * @param moveString
		 */
		
		
		public void handleInput(String moveString)
		{
			moveString = moveString.trim();
			Pattern patternCommand = Pattern.compile(":[hsox]", Pattern.CASE_INSENSITIVE);
			Pattern patternMovement = Pattern.compile("[a-h][1-8][a-h][1-8]", Pattern.CASE_INSENSITIVE);
			
			Matcher commandMatcher = patternCommand.matcher(moveString);
			Matcher movementMatcher = patternMovement.matcher(moveString);
			
		    boolean commandMatchFound = commandMatcher.find();
		    boolean movementMatchFound = movementMatcher.find();
		    
		    if(commandMatchFound) 
		    {
		      System.out.println("\n=====Command found");
		      handleCommand(moveString);
		    } 
		    else if (movementMatchFound && moveString.length()==4)
		    {
		    	System.out.println("\n=====Movement found");
		    	handleMove(moveString);
		    }
		    else {
		    	printHelp();
		    }
			
		}
		
		public void handleCommand(String moveString)
		{
			switch(moveString) {
			  case ":h":
				printHelp();
			    break;
			  case ":s":
				  saveGame();
			    break;
			  case ":o":
				  openGame();
				    break;
			  case ":x":
				  if(exitGame())
				  {
					  this.gameInAction = false;
				  }
				  break;
			   default:
//				  throw new CommandSyntaxError("Λανθασμένη εντολή "+moveString);
				  break;
			}
		}
		
		public void handleMove(String moveString)
		{

			Location from = board.getLocation(moveString.substring(0,2));
			Location to = board.getLocation(moveString.substring(2,4));
			
			Piece pieceToMove = from.getPiece();
			
			try {
				if(!thereIsPieceInLoc(from))
				{
					throw new InvalidMoveException(InvalidMoveException.THERE_IS_NO_PIECE_THERE_TO_MOVE);
				}
				if(!checkIfPieceToMoveMatchesPlayer(from))
				{
					throw new InvalidMoveException(InvalidMoveException.NOT_YOUR_PIECE);
				}
				
				pieceToMove.moveToLocation(to);
				validMoves.add(moveString);
				changeTurn();
			} catch (InvalidMoveException e) {
				System.out.println(e.getMessage());
			}
			
			board.printTheBoard();
		}
		
		
		
		/**
		 * 		Returns true if there is a piece in the 'from' position.
		 * @throws InvalidMoveException 
		 */
		
		private boolean thereIsPieceInLoc(Location loc) 
		{
			if(board.getPieceAt(loc)!=null)
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		
		/**
		 * 	μέθοδος που ελέγχει για πιθανά InvalidMoveException
		 * 	Κίνηση λάθος χρώματος κομματιού, π.χ. κίνηση μαύρου κομματιού όταν παίζουν τα άσπρα.
		 * 	Returns true if the 
		 * @throws InvalidMoveException 
		 */
		
		private boolean checkIfPieceToMoveMatchesPlayer(Location locTo) 
		{
			if((turn==0 && board.getPieceAt(locTo).color.equals(Color.WHITE))
					|| (turn==1 && board.getPieceAt(locTo).color.equals(Color.BLACK)))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
	/**
	 * Ζητάει το όνομα του αρχείου στο οποίο θα αποθηκευτεί η παρτίδα μέχρι εκείνο το σημείο, και
	 * επιχειρεί να πραγματοποιήσει την αποθήκευση. Χειρίζεται τυχόν εξαιρέσεις που θα προκύψουν
	 * (π.χ. αν δεν μπορεί να γράψει στο αρχείο, εγκαταλείπει τη διαδικασία εκτυπώνοντας πληροφοριακό
	 * μήνυμα).
	 */
	public void saveGame()
	{
		System.out.print("Please enter the name of this game: ");
		String gameName = sc.nextLine();
		gameName=gameName.trim();
		System.out.println(validMoves.toString());
		File myObj = null;
		 try {
		      myObj = new File("C:\\Users\\Ioanna\\Desktop\\Chess\\"+ gameName+".txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("A game with this name already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		 try {
		      FileWriter myWriter = new FileWriter(myObj);
		      for(int i=0; i<validMoves.size();i++)
		      {
		    	  myWriter.write(validMoves.get(i)+"\n");
		      }
		      
		      myWriter.close();
		      System.out.println("This game was saved as "+gameName+".");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	
	
	/**
	 * 	Εφόσον έχει ήδη ξεκινήσει μία παρτίδα, ο χρήστης ερωτάται αν θέλει να τη διακόψει. Σε καταφατική απάντηση,
	 *  ζητάει το όνομα του αρχείου από το οποίο θα διαβάσει τη σειρά των κινήσεων μίας
	 *  αποθηκευμένης παρτίδας, και επιχειρεί να τις φορτώσει (και να τις εφαρμόσει σε μία νέα παρτίδα 
	 *  ώστε να μπορεί αυτή να συνεχιστεί από το σημείο που αποθηκεύθηκε).
	 */
	public void openGame()
	{
		System.out.println("Game opened-not");
		//ArrayList<String> result = new ArrayList<>();
		System.out.print("Please write he name of the game you would like to open: ");
		String gameToOpen = sc.nextLine();
		//ArrayList<String> result = readMovesFromFile(gameToOpen);
		
		this.validMoves = readMovesFromFile(gameToOpen);
		for (int i=0; i<validMoves.size();i++)
		{
			String move = validMoves.get(i).toString();
			handleMove(move);
		}		
		play();
	}
		

	
	private ArrayList<String> readMovesFromFile(String nameOfSavedGame){
		
		ArrayList<String> result = new ArrayList<>();

		try (Scanner s = new Scanner(new FileReader("C:\\Users\\Ioanna\\Desktop\\Chess\\"+ nameOfSavedGame+".txt"))) {
		    while (s.hasNext()) {
		        result.add(s.nextLine());
		    }

		}catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		//System.out.println(result);
		return result;
	}


	
	/** 
	 * 	Εφόσον έχει ήδη ξεκινήσει μία παρτίδα, ο χρήστης ερωτάται αν θέλει να τη διακόψει. Σε καταφατική 
	 * απάντηση, το πρόγραμμα τερματίζεται.
	 * @return
	 */
	public boolean exitGame()
	{
		System.out.print("Are you sure you want to exit this game? \nPlease type 'yes' or 'no': ");
		String answer = sc.nextLine().trim();
		if(answer.toLowerCase().equals("yes"))
		{
			System.out.println("Bye!");
			return true;
		}
		else if(answer.toLowerCase().equals("no"))
		{
			System.out.println("");
			return false;
		}
		else
		{
			System.out.println("You typed neither yes, nor no. You can keep playing.\n");
			return false;
		}
	}

	
     /**
      * Εκτυπώνει ένα σύντομο κείμενο με βασικές οδηγίες χρήσης του προγράμματος.
      */
	public void printHelp()
	{
		System.out.println("Οι κινήσεις εισάγονται δίνοντας τις συντεταγμένες της θέσης του κομματιού που θα κινηθεί και τις\r\n" + 
				"συντεταγμένες της τελικής του θέσης (χωρίς κενά μεταξύ τους), π.χ. η κίνηση του πιονιού από την αρχική\r\n" + 
				"του θέση e2 κατά δύο τετράγωνα δίνεται ως e2e4. Επίσης μπορείτε να επιλέξετε μία από τις παρακάτω εντολές: \r\n"+
				":h  Εμφάνιση κειμένου βοήθειας\r\n" + 
				":s  Αποθήκευση παιχνιδιού\r\n" + 
				":o  Φόρτωση παιχνιδιού\r\n" + 
				":x  Έξοδος");
	}
		
	
}
