package sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class Option {
	ArrayList<Character> possibilities = new ArrayList<Character>();
	char value = '.';
	
	public Option() {
		possibilities.addAll(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
	}

	/**
	 * @return the value
	 */
	public char getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(char value) {
		this.value = value;
	}

}
