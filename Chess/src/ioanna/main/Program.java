package ioanna.main;

import java.util.regex.Pattern;

import ioanna.main.model.Board;
import ioanna.main.model.Game;
import ioanna.main.model.Location;

import java.util.Scanner;
import java.util.regex.Matcher;

public class Program {
	public static void main(String[] args) {
		

		
		Scanner sc = new Scanner(System.in);  
//	    System.out.print("Please enter the first player's name: ");
//	    String nameA= sc.nextLine();
//	    System.out.print("Please enter the second player's name: ");
//	    String nameB= sc.nextLine();

		
	    Board board = new Board();
	    board.init();
	    Game game=new Game(board);
		game.play();

		

	}
}
