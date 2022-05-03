package Submarine.Record;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Submarine.Board.Board;
import Submarine.Player.Guess;
import Submarine.Player.Player;

public class Records {

	public static void record(Player player, String info, Board logicBoard) {

		try (FileOutputStream file = new FileOutputStream("files/record_info.txt");
				ObjectOutputStream output = new ObjectOutputStream(file)) {

			output.writeObject(player);
			output.writeObject(info);
			output.writeObject(logicBoard);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void printFile() {
		try (FileInputStream file = new FileInputStream("files/record_info.txt");
				ObjectInputStream input = new ObjectInputStream(file)) {

			System.out.println(input.readObject());
			System.out.println(input.readObject());
			System.out.println(input.readObject());

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	public static Guess[] getRecordedGuesses() {
		try (FileInputStream file = new FileInputStream("files/record_info.txt");
				ObjectInputStream input = new ObjectInputStream(file)) {

			Player player = (Player) input.readObject();
			return player.getGuesses();
			
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public static Board getLogicBoard() {
		try (FileInputStream file = new FileInputStream("files/record_info.txt");
				ObjectInputStream input = new ObjectInputStream(file)) {

			input.readObject();
			input.readObject();
			return (Board)input.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
			return null;
		}
	}
}
