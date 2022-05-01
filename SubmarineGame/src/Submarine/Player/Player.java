package Submarine.Player;

import java.io.Serializable;
import java.util.Arrays;

import Submarine.Game.Game;

public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7936670946760372017L;
	private String name;
	private String email;
	private String phoneNumber;
	private Guess[] guesses;
	private int guessCounter;

	public Player(String name, String email, String phoneNumber) {
		setName(name);
		setEmail(email);
		setPhoneNumber(phoneNumber);
		this.guesses = new Guess[Game.GUESSES];
		this.guessCounter = 0;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	private void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void addGuess(int x, int y) {
		this.guesses[guessCounter++] = new Guess(guessCounter, x, y);
	}
	
	public Guess[] getGuesses() {
		return guesses;
	}

	public void setGuesses(Guess[] guesses) {
		this.guesses = guesses;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", \nguesses:\n"
				+ Arrays.toString(guesses) + "]";
	}
}
