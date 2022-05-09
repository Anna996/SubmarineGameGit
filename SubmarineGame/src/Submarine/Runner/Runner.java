package Submarine.Runner;

import java.util.Scanner;

import Submarine.Gui.Gui;

public class Runner {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Gui submarineGui1 = new Gui(scanner);
		submarineGui1.startGame();
		
		Gui submarineGui2 = new Gui(scanner);
		submarineGui2.startGame();
		
		Gui submarineGui3 = new Gui(scanner);
		submarineGui3.startGame();
		
		Gui submarineGui4 = new Gui(scanner);
		submarineGui4.startGame();
		
		Gui submarineGui5 = new Gui(scanner);
		submarineGui5.startGame();
		
		// Replay -------------------
		Gui recordedGame = new Gui(scanner);
		recordedGame.replay();
		
		scanner.close();
	}
}