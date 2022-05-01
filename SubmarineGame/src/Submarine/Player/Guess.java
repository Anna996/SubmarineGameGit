package Submarine.Player;

import java.io.Serializable;

public class Guess implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 724285969499484783L;
	private int numId;
	private int x;
	private int y;

	public Guess(int numId, int x, int y) {
		setNumId(numId);
		setX(x);
		setY(y);
	}

	private void setNumId(int numId) {
		this.numId = numId;
	}

	private void setX(int x) {
		this.x = x;
	}

	private void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "num: " + numId + "  (" + x + " , " + y + ")\n";
	}	
}
