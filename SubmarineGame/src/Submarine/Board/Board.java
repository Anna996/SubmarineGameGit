package Submarine.Board;

public class Board {
	private char[][] matrix;
	public static final int ROWS = 10;
	public static final int COLS = 20;
	public static final int NUM_OF_SUBMARINES = 5;

	public Board() {
		initMatrix();
	}

	private void initMatrix() {
		matrix = new char[ROWS][COLS];

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				matrix[i][j] = 'X';
			}
		}
	}

	public void printValues() {
		for (char[] row : matrix) {
			for (char val : row) {
				System.out.print(val + " ");
			}
			System.out.println(" ");
		}
		System.out.println("\n");
	}
	
	public void print() {
		String space = "  ";
		System.out.print("   ");
		printRowNumbers(0,10,space);
		printRowNumbers(10,COLS," ");
		System.out.println("\n");
		
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if(j == 0) {
					System.out.print(i + space);
				}
//				 System.out.print(matrix[i][j] + "  ");
			}
			System.out.println("");
		}
	}
	
	private void printRowNumbers(int from,int to, String space) {
		for(int i = from; i < to; i++) {
			System.out.print(i + space);
		}
	}

	public boolean isMatch(int row, int col) {
		// TODO Auto-generated method stub
		return false;
	}
}
