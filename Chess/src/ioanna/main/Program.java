package ioanna.main;

import ioanna.main.model.Board;
import ioanna.main.model.Game;

public class Program {
	public static void main(String[] args) {

		Board board = new Board();
		board.init();
		Game game = new Game(board);
		System.out.println("\nHello!\n");
		game.play();

	}
}
