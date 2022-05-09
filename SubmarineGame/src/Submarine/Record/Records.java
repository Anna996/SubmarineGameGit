package Submarine.Record;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Submarine.Board.Board;
import Submarine.Player.Guess;
import Submarine.Player.Player;

public class Records {
	private static String prefix;
	private static String suffix;
	
	static {
		prefix ="files/record_info_";
		suffix = ".txt";
	}
	

	private static String getFileName(int recordNum) {
		return prefix + recordNum + suffix;
	}
	
	public static void record(int recordNum, Player player, String info, Board logicBoard) {
		String fileName = getFileName(recordNum);

		try (FileOutputStream file = new FileOutputStream(fileName);
				ObjectOutputStream output = new ObjectOutputStream(file)) {

			output.writeObject(player);
			output.writeObject(info);
			output.writeObject(logicBoard);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void printFile(int recordNum) {
		String fileName = getFileName(recordNum);
		
		try (FileInputStream file = new FileInputStream(fileName);
				ObjectInputStream input = new ObjectInputStream(file)) {

			System.out.println(input.readObject());
			System.out.println(input.readObject());
			System.out.println(input.readObject());

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	public static Player getRecordedPlayer(int recordNum) {
		String fileName = getFileName(recordNum);
		
		try (FileInputStream file = new FileInputStream(fileName);
				ObjectInputStream input = new ObjectInputStream(file)) {

			return (Player) input.readObject();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public static Guess[] getRecordedGuesses(int recordNum) {
		String fileName = getFileName(recordNum);
		
		try (FileInputStream file = new FileInputStream(fileName);
				ObjectInputStream input = new ObjectInputStream(file)) {

			Player player = (Player) input.readObject();
			return player.getGuesses();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
			return null;
		}
	}

	public static Board getLogicBoard(int recordNum) {
		String fileName = getFileName(recordNum);
		
		try (FileInputStream file = new FileInputStream(fileName);
				ObjectInputStream input = new ObjectInputStream(file)) {

			input.readObject();
			input.readObject();
			return (Board) input.readObject();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
			return null;
		}
	}

	public static int getNumOfFiles() {
		File files = new File("files");
		return files.list().length;
	}
}
