package Submarine.Record;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Submarine.Player.Guess;
import Submarine.Player.Player;

public class Records {

	public void record(Player player, String info) {

		try (FileOutputStream file = new FileOutputStream("files/record_info.txt");
				ObjectOutputStream output = new ObjectOutputStream(file)) {

			output.writeObject(player);
			output.writeObject(info);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void printFile() {
		try (FileInputStream file = new FileInputStream("files/record_info.txt");
				ObjectInputStream input = new ObjectInputStream(file)) {

			System.out.println(input.readObject());
			System.out.println(input.readObject());

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	public Guess[] getRecordedGuesses() {
		try (FileInputStream file = new FileInputStream("files/record_info.txt");
				ObjectInputStream input = new ObjectInputStream(file)) {

			Player player = (Player) input.readObject();
			return player.getGuesses();
			
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
			return null;
		}
	}
}
