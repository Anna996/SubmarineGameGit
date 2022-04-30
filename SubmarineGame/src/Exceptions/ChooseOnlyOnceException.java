package Exceptions;

public class ChooseOnlyOnceException extends Exception {

	public ChooseOnlyOnceException() {
		super("You can't choose the same square twice");
	}
}
