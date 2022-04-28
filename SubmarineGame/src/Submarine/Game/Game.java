package Submarine.Game;

import Submarine.Board.Board;

public class Game {
	private Board logicBoard;
	private Board userBoard;
	private int numOfHits;
	private int numOfMiss;
	private Status status;
	private final int GUESSES = 100;
	public final char HIT = 'H';
	public final char MISS = '-';

	public Game() {
		initLogicBoard();
		initUserBoard();
		setNumOfHits(0);
		setNumOfHits(0);
		status = Status.PLAYING;
	}

	private void initLogicBoard() {
		logicBoard = new Board();
		logicBoard.addRandomSubmarines();
	}

	private void initUserBoard() {
		userBoard = new Board();
	}

	private void setNumOfHits(int numOfHits) {
		this.numOfHits = numOfHits;
	}

	private void setNumOfMiss(int numOfMiss) {
		this.numOfMiss = numOfMiss;
	}

	public Status getStatus() {
		return status;
	}

	public void playNext(int row, int col) {
		char resultVal;

		resultVal = logicBoard.getValueAt(row, col) == logicBoard.getSubmarinePattern() ? HIT : MISS;
		userBoard.setValueAt(row, col, resultVal);
		updateScore(resultVal);
		updateStatus();
	}

	private void updateScore(char resultVal) {
		if (resultVal == HIT) {
			setNumOfHits(numOfHits + 1);
		} else {
			setNumOfMiss(numOfMiss + 1);
		}
	}

	private void updateStatus() {
		if (numOfHits + numOfMiss == GUESSES) {
			this.status = numOfHits > numOfMiss ? Status.WON : Status.LOST;
		}
	}

	public void printLogicBoard() {
		logicBoard.print();
	}

	public void printUserBoard() {
		userBoard.print();
	}

	public enum Status {
		PLAYING, WON, LOST
	}
}
