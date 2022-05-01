package Submarine.Gui;

import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

import Exceptions.ChooseOnlyOnceException;
import Exceptions.OutOfBoardException;
import Submarine.Board.Board;
import Submarine.Game.Game;
import Submarine.Game.Game.Status;
import Submarine.Player.Guess;
import Submarine.Player.Player;
import Submarine.Record.Records;

public class Gui {
	private Game game;
	private Scanner scanner;
	private Player player;
	private Records infoRecord;

	public void startGame() {
		scanner = new Scanner(System.in);
		initGame();
		printTitle();
		initPlayer();
		play();
		printGameResult();
		recordInforamtion();
//		infoRecord.printFile();
		scanner.close();
	}

	private void initGame() {
		game = new Game();
	}

	private void initGame(Board logicBoard) {
		game = new Game(logicBoard);
	}
	
	private void initPlayer() {
//		System.out.print("Enter your name: ");
//		String name = scanner.next();
//		System.out.print("Enter your email: ");
//		String email = scanner.next();
//		System.out.print("Enter your phoneNumber: ");
//		String phoneNumber = scanner.next();
//		
//		player = new Player(name, email, phoneNumber);

		player = new Player("anna", "annaaba15@gmail.com", "0545225947");
	}

	private void recordInforamtion() {
		infoRecord = new Records();
		infoRecord.record(player, "Player status: " + game.getStatus());
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
//				row = askForRow();
//				col = askForCol();

				// Temporary =====================
				Random random = new Random();
				row = random.nextInt(Board.ROWS);
				col = random.nextInt(Board.COLS);
				// ================================

				try {
					game.playNext(row, col);
					player.addGuess(row, col);
				} catch (OutOfBoardException | ChooseOnlyOnceException e) {
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

	public void replay() {
		int row , col;
		
		initGame(game.getLogicBoard());
		printNewLine();
		System.out.println("replay...");
		for (Guess guess : infoRecord.getRecordedGuesses()) {
			row = guess.getX();
			col = guess.getY();
			
			delay();
			printNewLine();
			game.printCurrentInfo();
			game.printUserBoard();
			try {
				game.playNext(row, col);
			} catch (OutOfBoardException | ChooseOnlyOnceException e) {
				System.out.println(e);
			}
			printNewLine();
			System.out.printf("Your choise: ( %d , %d )\n", row , col);
		}
		
		printNewLine();
		game.printCurrentInfo();
		game.printUserBoard();
		System.out.println("End Of Replay.");
	}
	
	private void delay() {
		LocalTime soon = LocalTime.now().plusSeconds(3);
		while(LocalTime.now().isBefore(soon)) {}
	}
}
