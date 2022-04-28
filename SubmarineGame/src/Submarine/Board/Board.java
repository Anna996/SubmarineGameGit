package Submarine.Board;

import java.util.Random;

import Submarine.Submarine.Submarine;

public class Board {
	private char[][] matrix;
	private Submarine[] submarines;
	private Random random;
	public final char EMPTY = ' ';
	public final char NOT_AVAILABLE = '+';
	public static final int ROWS = 10;
	public static final int COLS = 20;
	public static final int NUM_OF_SUBMARINES = 5;
	public static final int MAX_SIZE_OF_SUBMARINE = 4;

	public Board() {
		matrix = new char[ROWS][COLS];
		submarines = new Submarine[NUM_OF_SUBMARINES];
		initBoard();
	}

	private void initBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				matrix[i][j] = EMPTY;
			}
		}
	}

	public void addRandomSubmarines() {
		random = new Random();
		int size;

		for (int i = 0; i < NUM_OF_SUBMARINES; i++) {
			size = 1 + random.nextInt(MAX_SIZE_OF_SUBMARINE);
			submarines[i] = createSubmarine(size);
			addSubmarine(submarines[i]);
		}
	}

	private void addSubmarine(Submarine submarine) {
		int size = submarine.getSize();
		int[] rows = submarine.getRows();
		int[] cols = submarine.getCols();

		for (int i = 0; i < size; i++) {
			setValueAt(rows[i], cols[i], Submarine.PATTERN);
		}
	}

	// TODO LOGIC: find available place for submarine
	private Submarine createSubmarine(int size) {
		Submarine submarine = new Submarine(size);
		int row, col;
		boolean foundPlace;
		
		do {
			foundPlace=true;
			row = random.nextInt(ROWS);
			col = random.nextInt(COLS);
			while(isAvailableSquare(row, col)) {
				
			}
		}while(foundPlace);

		
//		for (int i = 0; i < size; i++) {
//			row = random.nextInt(ROWS);
//			col = random.nextInt(COLS);
//			submarine.addData(row, col);
//		}

		return submarine;
	}
	
	private boolean isAvailableSquare(int row, int col) {
		return getValueAt(row, col) == EMPTY;
	}

	public void print() {
		String space = "  ";

		System.out.print("   ");
		printHeaderNumbers(0, 10, space);
		printHeaderNumbers(10, COLS, " ");
		System.out.println("\n");
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (j == 0) {
					System.out.print(i + space);
				}
				System.out.print(matrix[i][j] + "  ");
			}
			System.out.println("\n");
		}
	}

	private void printHeaderNumbers(int from, int to, String space) {
		for (int i = from; i < to; i++) {
			System.out.print(i + space);
		}
	}

	// TODO Exception setValueAt
	public void setValueAt(int row, int col, char val) {
		matrix[row][col] = val;
	}

	// TODO Exception getValueAt
	public char getValueAt(int row, int col) {
		return matrix[row][col];
	}

	public char getSubmarinePattern() {
		return Submarine.PATTERN;
	}
}
