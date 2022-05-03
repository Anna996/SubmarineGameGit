package Submarine.Board;

import java.io.Serializable;
import java.util.Random;

import Exceptions.OutOfBoardException;
import Exceptions.OutOfTargetsException;
import Submarine.Submarine.Submarine;

public class Board implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4088873713912415739L;
	private char[][] matrix;
	private Submarine[] submarines;
	private Random random;
	private int numOfPatternsToDiscover;
	public final char EMPTY = ' ';
	public final char NOT_AVAILABLE = '+';
	public static final int ROWS = 10;
	public static final int COLS = 20;
	public static final int NUM_OF_SUBMARINES = 5;
	public static final int MAX_SIZE_OF_SUBMARINE = 4;

	public Board() {
		matrix = new char[ROWS][COLS];
		initBoard();
	}

	private void initBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				matrix[i][j] = EMPTY;
			}
		}
	}

	private void setNumOfPatternsToDiscover(int numOfPatternsToDiscover) {
		this.numOfPatternsToDiscover = numOfPatternsToDiscover;
	}

	public void foundOnePattern() throws OutOfTargetsException {
		setNumOfPatternsToDiscover(numOfPatternsToDiscover - 1);
		if (numOfPatternsToDiscover == 0)
			throw new OutOfTargetsException();
	}

	public void addRandomSubmarines() {
		random = new Random();
		int size, numOfPatternsToDiscover = 0;

		submarines = new Submarine[NUM_OF_SUBMARINES];
		for (int i = 0; i < NUM_OF_SUBMARINES; i++) {
			size = 1 + random.nextInt(MAX_SIZE_OF_SUBMARINE);
			submarines[i] = createSubmarine(size);
			addSubmarine(submarines[i]);
			numOfPatternsToDiscover += size;
		}

		setNumOfPatternsToDiscover(numOfPatternsToDiscover);
	}

	private void addSubmarine(Submarine submarine) {
		int size = submarine.getSize();
		int[] rows = submarine.getRows();
		int[] cols = submarine.getCols();

		for (int i = 0; i < size; i++) {
			setValueAt(rows[i], cols[i], Submarine.PATTERN);
		}

		for (int i = 0; i < size; i++) {
			addNotAvailablePatternsFor(rows[i], cols[i]);
		}
	}

	private void addNotAvailablePatternsFor(int row, int col) {
		int[] square;

		square = getUp(row, col);
		addNotAvailablePatternAt(square);
		square = getDown(row, col);
		addNotAvailablePatternAt(square);
		square = getRight(row, col);
		addNotAvailablePatternAt(square);
		square = getLeft(row, col);
		addNotAvailablePatternAt(square);
	}

	private void addNotAvailablePatternAt(int[] square) {
		int row = square[0], col = square[1];

		if (isAvailableSquare(row, col)) {
			setValueAt(row, col, NOT_AVAILABLE);
		}
	}

	private Submarine createSubmarine(int size) {
		int row, col;
		Boolean isFoundAll = false;
		int[][] arrOfFounded = new int[size][];

		do {
			row = random.nextInt(ROWS);
			col = random.nextInt(COLS);
			if (isAvailableSquare(row, col)) {
				int[] square = new int[] { row, col };
				arrOfFounded[0] = square;
				isFoundAll = findAvailableSquares(row, col, 1, size, arrOfFounded);
			}
		} while (!isFoundAll);

		return createSubmarine(arrOfFounded);
	}

	private Submarine createSubmarine(int[][] arrOfFounded) {
		Submarine submarine = new Submarine(arrOfFounded.length);

		for (int[] square : arrOfFounded) {
			submarine.addData(square[0], square[1]);
		}

		return submarine;
	}

	private boolean findAvailableSquares(int row, int col, int numOfFound, int size, int[][] arrOfFounded) {
		if (numOfFound == size) {
			return true;
		}

		int[][] arrOfAvailable = new int[4][];
		int numOfNeighbors = addAllAvailableNeighbors(row, col, arrOfAvailable, arrOfFounded);
		int[] unChecked = new int[numOfNeighbors];
		for (int i = 0; i < numOfNeighbors; i++) {
			unChecked[i] = i;
		}

		Random random = new Random();
		int unCheckedIdx;
		for (int i = 0; i < numOfNeighbors; i++) {
			unCheckedIdx = random.nextInt(unChecked.length);
			int[] neighbor = arrOfAvailable[unChecked[unCheckedIdx]];
			arrOfFounded[numOfFound] = neighbor;
			boolean isFoundAll = findAvailableSquares(neighbor[0], neighbor[1], numOfFound + 1, size, arrOfFounded);
			if (isFoundAll) {
				return true;
			}
			unChecked = deleteIndex(unChecked, unCheckedIdx);
		}
		return false;
	}

	/**
	 * Adds all available neighbors of this current square (represented with
	 * (row,col) ), but ignores the neighbors that had already found and in
	 * arrOfFounded.
	 * 
	 * @param row            the row of the current square.
	 * @param col            the col of the current square.
	 * @param arrOfAvailable array that contains all the available neighbors.
	 * @param arrOfFounded   array of all founded squares until now.
	 * @return the number of neighbors that was added to arrOfAvailable.
	 */
	private int addAllAvailableNeighbors(int row, int col, int[][] arrOfAvailable, int[][] arrOfFounded) {
		int[] neighbor;
		int index;

		neighbor = getUp(row, col);
		index = addNeighborIfAvailable(neighbor, arrOfAvailable, 0, arrOfFounded);
		neighbor = getDown(row, col);
		index = addNeighborIfAvailable(neighbor, arrOfAvailable, index, arrOfFounded);
		neighbor = getLeft(row, col);
		index = addNeighborIfAvailable(neighbor, arrOfAvailable, index, arrOfFounded);
		neighbor = getRight(row, col);
		index = addNeighborIfAvailable(neighbor, arrOfAvailable, index, arrOfFounded);

		return index;
	}

	private int[] deleteIndex(int[] arr, int index) {
		int[] updated = new int[arr.length - 1];
		int updatedIdx = 0, arrIdx = 0;

		while (updatedIdx < updated.length) {
			if (updatedIdx != index) {
				updated[updatedIdx++] = arr[arrIdx];
			}
			arrIdx++;
		}

		return updated;
	}

	private int addNeighborIfAvailable(int[] neighbor, int[][] arrOfAvailable, int index, int[][] arrOfFounded) {
		for (int[] ignoreSquare : arrOfFounded) {
			if (ignoreSquare == null)
				break;
			if (neighbor[0] == ignoreSquare[0] && neighbor[1] == ignoreSquare[1])
				return index;
		}

		if (isAvailableSquare(neighbor)) {
			arrOfAvailable[index] = new int[2];
			arrOfAvailable[index][0] = neighbor[0];
			arrOfAvailable[index][1] = neighbor[1];
			index++;
		}
		return index;
	}

	private boolean isAvailableSquare(int[] square) {
		return isAvailableSquare(square[0], square[1]);
	}

	public boolean isAvailableSquare(int row, int col) {
		try {
			return getValueAt(row, col) == EMPTY;
		} catch (OutOfBoardException e) {
			return false;
		}
	}

	private int[] getUp(int row, int col) {
		return new int[] { row - 1, col };
	}

	private int[] getDown(int row, int col) {
		return new int[] { row + 1, col };
	}

	private int[] getLeft(int row, int col) {
		return new int[] { row, col - 1 };
	}

	private int[] getRight(int row, int col) {
		return new int[] { row, col + 1 };
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

	public void setValueAt(int row, int col, char val) {
		matrix[row][col] = val;
	}

	public char getValueAt(int row, int col) throws OutOfBoardException {
		validSquareIndexes(row, col);
		return matrix[row][col];
	}

	public char getSubmarinePattern() {
		return Submarine.PATTERN;
	}

	private void validSquareIndexes(int row, int col) throws OutOfBoardException {
		validIndex(row, ROWS, "Row");
		validIndex(col, COLS, "Col");
	}

	private void validIndex(int index, int max, String nameOfIndex) throws OutOfBoardException {
		if (index < 0 || index >= max)
			throw new OutOfBoardException(nameOfIndex + " must be between 0 and " + (max - 1));
	}
}
