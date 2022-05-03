package Submarine.Runner;

import Submarine.Gui.Gui;

public class Runner {
	public static void main(String[] args) {
		Gui submarineGui = new Gui();
		submarineGui.startGame();
		
		Gui recordedGame = new Gui();
		recordedGame.replayLastGame();
	}
}
