package Submarine.Game;

import Exceptions.ChooseOnlyOnceException;
import Exceptions.OutOfBoardException;
import Exceptions.OutOfTargetsException;
import Submarine.Board.Board;

public class Game {
	private Board logicBoard;
	private Board userBoard;
	private int numOfHits;
	private int numOfMiss;
	private int score;
	private boolean consecutiveHit;
	private Status status;
	public static final int GUESSES = 100;
	public final char HIT = 'H';
	public final char MISS = '-';

	public Game() {
		initLogicBoard();
		initUserBoard();
		setNumOfHits(0);
		setNumOfHits(0);
		setScore(1000);
		setConsecutiveHit(false);
		status = Status.PLAYING;
	}
	
	public Game(Board logicBoard) {
		this();
		this.logicBoard = logicBoard;
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

	private void setScore(int score) {
		this.score = score;
	}
	
	private void setConsecutiveHit(boolean consecutiveHit) {
		this.consecutiveHit = consecutiveHit;
	}

	private boolean isConsecutiveHit() {
		return consecutiveHit;
	}

	public Status getStatus() {
		return status;
	}
	
	public Board getLogicBoard() {
		return logicBoard;
	}

	public void playNext(int row, int col) throws OutOfBoardException, ChooseOnlyOnceException {
		char resultVal;

		resultVal = logicBoard.getValueAt(row, col) == logicBoard.getSubmarinePattern() ? HIT : MISS;
		if(!userBoard.isAvailableSquare(row, col)) {
			throw new ChooseOnlyOnceException();
		}
		
		userBoard.setValueAt(row, col, resultVal);
		try {
			updateScore(resultVal);
		} catch (OutOfTargetsException e) {
			System.out.println(e.getMessage());
			this.status = Status.WON;
			return;
		}
		updateStatus();
	}

	private void updateScore(char resultVal) throws OutOfTargetsException {
		int score;
		
		if (resultVal == HIT) {
			setNumOfHits(numOfHits + 1);
			score = isConsecutiveHit() ? 1000 : 200;
			setConsecutiveHit(true);
			logicBoard.foundOnePattern();
			
		} else {
			setNumOfMiss(numOfMiss + 1);
			setConsecutiveHit(false);
			score = -10;
		}
		setScore(this.score + score);
	}

	private void updateStatus() {
		if (numOfHits + numOfMiss == GUESSES || score <= 0) {
			this.status = numOfHits > numOfMiss ? Status.WON : Status.LOST;
		}
	}

	public void printLogicBoard() {
		logicBoard.print();
	}

	public void printUserBoard() {
		userBoard.print();
	}

	public int getNumOfTriesPlayerDid() {
		return numOfHits + numOfMiss;
	}
	
	public void printCurrentInfo() {
		System.out.printf("Score: %d , #hits: %d , #misses: %d\n", score, numOfHits, numOfMiss);
	}

	public enum Status {
		PLAYING, WON, LOST
	}
}
