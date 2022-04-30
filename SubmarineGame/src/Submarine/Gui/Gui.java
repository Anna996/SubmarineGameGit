package Submarine.Gui;

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
		boolean isValidSquare;

		while (game.getStatus() == Status.PLAYING) {
			printNewLine();
			System.out.printf("Try number %d:\n", game.getNumOfTriesPlayerDid() + 1);
			game.printCurrentInfo();
			printNewLine();
			game.printUserBoard();
			printNewLine();

			do {
				isValidSquare = true;
				row = askForRow();
				col = askForCol();
				try {
					game.playNext(row, col);
				} catch (Exception e) {
					printNewLine();
					System.out.println(e.getMessage());
					System.out.println("Please try again:");
					isValidSquare = false;
				}
			} while (!isValidSquare);
		}

		printNewLine();
		game.printCurrentInfo();
		printNewLine();
		game.printUserBoard();
		printNewLine();
	}

	private int askForRow() {
		System.out.print("Enter row: ");
		int row = scanner.nextInt();

		return row;
	}

	private int askForCol() {
		System.out.print("Enter col: ");
		int col = scanner.nextInt();

		return col;
	}

	private void printGameResult() {
		System.out.println("You " + game.getStatus() + " !");
	}
}
