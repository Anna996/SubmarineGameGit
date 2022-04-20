package Submarine.Game;

import java.util.Scanner;

import Submarine.Board.Board;

public class Game {
	private Board board;
	private int numOfHits;
	private int numOfMiss;
	private Status status;
	
	public Game() {
		board = new Board();
		setNumOfHits(0);
		setNumOfHits(0);
		status = Status.PLAYING;
	}

	private void setNumOfHits(int numOfHits) {
		this.numOfHits = numOfHits;
	}

	private void setNumOfMiss(int numOfMiss) {
		this.numOfMiss = numOfMiss;
	}	
	
	public void play() {
		Scanner scanner = new Scanner(System.in);
		int row , col;
		
		System.out.println("Playing...\n");
		board.print();
		
		while(status == Status.PLAYING) {
			System.out.print("Enter row: ");
			row = scanner.nextInt();
			System.out.print("Enter col: ");
			col = scanner.nextInt();
			updateScore(board.isMatch(row, col));
			updateStatus();
		}
		
		System.out.println("You " + status + " !");
	}
	
	private void updateStatus() {
		if(numOfHits + numOfMiss == 100) {
			this.status = numOfHits > numOfMiss ? Status.WON : Status.LOST;
		}
	}

	private void updateScore(boolean isMatch) {
		if(isMatch) {
			setNumOfHits(numOfHits + 1);
		}
		else {
			setNumOfMiss(numOfMiss + 1);
		}
	}
	
	public enum Status{
		PLAYING, WON, LOST
	}
}
