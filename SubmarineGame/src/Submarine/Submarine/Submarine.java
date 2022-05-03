package Submarine.Submarine;

import java.io.Serializable;

public class Submarine implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6759272276820844200L;
	private int[] rows;
	private int[] cols;
	private int index;
	public static final char PATTERN = 'B';

	public Submarine(int size) {
		rows = new int[size];
		cols = new int[size];
		index = 0;
	}

	public void addData(int row, int col) {
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