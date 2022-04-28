package Submarine.Submarine;

public class Submarine {
	private int[] rows;
	private int[] cols;
	private int index;
	public static final char PATTERN = '*';

	public Submarine(int size) {
		rows = new int[size];
		cols = new int[size];
		index = 0;
	}

	public void addData(int row, int col) {
		if(index == rows.length) {
			// TODO Exception for Submarine class addData
			System.out.println("Overflow!");
			return;
		}
		rows[index] = row;
		cols[index] = col;
		index++;
	}

	public int[] getRows() {
		return rows;
	}

	public int[] getCols() {
		return cols;
	}
	
	public int getSize() {
		return rows.length;
	}
}