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

//	� ����� �������������� ��� ������� �����. ���������� (�����������) �� �������� ��� ��������
//	��� ������ ��� ������ ���� ������.
//	����� ������� ������� �����������, ���� public ������� ��� ������ ����� � ��������:
	
		public Game(Board board) {
		super();
		this.board = board;
		gameInAction = true;
		turn = 0;
		validMoves = new ArrayList<>();
		
	}


		/** 
		 * �������� ��� �������� �������, ������� �� �� ������� ��� ����������� ��������. ����������
		 * ��� ������ ��� ������ ��� ���������� ��� ��� ������� ��� ����������.
		 * ��� �������� ������ ��� ������������, � ����� ���� �� �������� ��� private �������� ��� ���
		 * ��������� ��� ����������� ��� ������������, ����������� ��� ���������:
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
		 * 	���� � ������� ��� ������ ��� �������� ��� �:� ���������� ��� ����� � ������ ���. � ������� ����
		 * ������� ��� ���� ��� ������ ��� ������ ��� ��� ������������� ��������� ����, �� ����� ������
		 * �� ��������������� ��� ���������� ������, � ����������� �� ��������� ��� �������� ����������
		 * ��� �� ����������.
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
//				  throw new CommandSyntaxError("���������� ������ "+moveString);
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
		 * 	������� ��� ������� ��� ������ InvalidMoveException
		 * 	������ ����� �������� ���������, �.�. ������ ������ ��������� ���� ������� �� �����.
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
	 * ������ �� ����� ��� ������� ��� ����� �� ����������� � ������� ����� ������ �� ������, ���
	 * ��������� �� ��������������� ��� ����������. ���������� ����� ���������� ��� �� ���������
	 * (�.�. �� ��� ������ �� ������ ��� ������, ������������ �� ���������� ������������ ������������
	 * ������).
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
	 * 	������ ���� ��� ��������� ��� �������, � ������� �������� �� ����� �� �� ��������. �� ���������� ��������,
	 *  ������ �� ����� ��� ������� ��� �� ����� �� �������� �� ����� ��� �������� ����
	 *  ������������� ��������, ��� ��������� �� ��� �������� (��� �� ��� ��������� �� ��� ��� ������� 
	 *  ���� �� ������ ���� �� ���������� ��� �� ������ ��� ������������).
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
	 * 	������ ���� ��� ��������� ��� �������, � ������� �������� �� ����� �� �� ��������. �� ���������� 
	 * ��������, �� ��������� ������������.
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
      * ��������� ��� ������� ������� �� ������� ������� ������ ��� ������������.
      */
	public void printHelp()
	{
		System.out.println("�� �������� ���������� �������� ��� ������������� ��� ����� ��� ��������� ��� �� ������� ��� ���\r\n" + 
				"������������� ��� ������� ��� ����� (����� ���� ������ ����), �.�. � ������ ��� ������� ��� ��� ������\r\n" + 
				"��� ���� e2 ���� ��� ��������� ������� �� e2e4. ������ �������� �� ��������� ��� ��� ��� �������� �������: \r\n"+
				":h � �������� �������� ��������\r\n" + 
				":s � ���������� ����������\r\n" + 
				":o � ������� ����������\r\n" + 
				":x � ������");
	}
		
	
}
