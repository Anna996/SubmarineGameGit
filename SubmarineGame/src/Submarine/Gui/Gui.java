package Submarine.Gui;

import java.util.Random;
import java.util.Scanner;

import Submarine.Game.Game;
import Submarine.Game.Game.Status;

public class Gui {
	private Game game;
	private Scanner scanner;

	public void startGame() {
		scanner = new Scanner(System.in);
		initGame();
		printTitle();
		play();
		printGameResult();
		scanner.close();
	}

	private void initGame() {
		game = new Game();
	}

	private void printTitle() {
		System.out.println("Sunk The Submarine");
		System.out.println("==================");
	}

	private void printNewLine() {
		System.out.println("");
	}

	private void play() {
		int row, col;

		while (game.getStatus() == Status.PLAYING) {
			
			// Temporary =====================
			printNewLine();
			System.out.println("Logic Board:");
			game.printLogicBoard();
			// ================================
			
			printNewLine();
			System.out.println("User's Board:"); // <- Temporary =====================
			game.printUserBoard();
			printNewLine();
			row = askForRow();
			col = askForCol();

			// Temporary =====================
//			Random random = new Random();
//			row = random.nextInt(Board.ROWS);
//			col = random.nextInt(Board.COLS);
			// ================================

			game.playNext(row, col);
		}
	}

	// TODO Exception for input row
	private int askForRow() {
		System.out.print("Enter row: ");
		int row = scanner.nextInt();

		return row;
	}

	// TODO Exception for input col
	private int askForCol() {
		System.out.print("Enter col: ");
		int col = scanner.nextInt();

		return col;
	}
	
	private void printGameResult() {
		System.out.println("You " + game.getStatus() + " !");
	}
}
