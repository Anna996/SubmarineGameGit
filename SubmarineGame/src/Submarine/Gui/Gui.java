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
	private static int gameCounter = 0;

	public Gui(Scanner scanner) {
		gameCounter++;
		this.scanner = scanner;
	}

	public void startGame() {
		initGame();
		printTitle();
		initPlayer();
		play();
		printGameResult();
		recordInforamtion();
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
		Records.record(gameCounter, player, "Player status: " + game.getStatus(), game.getLogicBoard());
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

	private void replayGame(int recordNum) {
		int row, col;

		initGame(Records.getLogicBoard(recordNum));
		printNewLine();
		Records.getRecordedPlayer(recordNum).printPlayerInfo();
		printNewLine();
		System.out.println("replay...");
		for (Guess guess : Records.getRecordedGuesses(recordNum)) {
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
			System.out.printf("Your choise: ( %d , %d )\n", row, col);
		}

		printNewLine();
		game.printCurrentInfo();
		game.printUserBoard();
		System.out.println("End Of Replay.");
	}

	public void replay() {
		System.out.println("\n========= REPLAY =========");
		System.out.print("You have " + Records.getNumOfFiles() + " recordings.\nEnter the number of record: ");
		int input = scanner.nextInt();
		replayGame(input);
	}

	private void delay() {
		LocalTime soon = LocalTime.now().plusSeconds(3);
		while (LocalTime.now().isBefore(soon)) {
		}
	}
}
