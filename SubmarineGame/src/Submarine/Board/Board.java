package Submarine.Board;

import Submarine.Submarine.Submarine;

public class Board {
	private char[][] matrix;
	private Submarine[] submarines;
	
	public static final int ROWS = 10;
	public static final int COLS = 20;
	public static final int NUM_OF_SUBMARINES = 5;
	public static final char HIT = 'H';
	public static final char MISS = '-';
	public static final char PATTERN = '*';

	public Board() {
		matrix = new char[ROWS][COLS];
		submarines = new Submarine[NUM_OF_SUBMARINES];
		initBoard();
	}

	//TODO  logic !!!
	private void initBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				matrix[i][j] = ' ';
			}
		}
	}

	public void print() {
		String space = "  ";
		char curr;

		System.out.print("   ");
		printRowNumbers(0, 10, space);
		printRowNumbers(10, COLS, " ");
		System.out.println("\n");

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (j == 0) {
					System.out.print(i + space);
				}
				curr = matrix[i][j];
				if (curr == HIT || curr == MISS) {
					System.out.print(curr + "  ");
				}
			}
			System.out.println("");
		}
	}

	private void printRowNumbers(int from, int to, String space) {
		for (int i = from; i < to; i++) {
			System.out.print(i + space);
		}
	}

	public boolean makeMove(int row, int col) {
		matrix[row][col] = matrix[row][col] == PATTERN ? HIT : MISS;
		return matrix[row][col] == HIT;
	}
}
