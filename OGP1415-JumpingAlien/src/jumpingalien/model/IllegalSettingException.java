package jumpingalien.model;

/**
 * 
 * A class to check or no GameObjects or tiles or set once the game has started 
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus 
 *
 */
@SuppressWarnings("serial")
public class IllegalSettingException extends Exception {
	
	/**
	 * Initializes the new IllegalSettingException
	 */
	public IllegalSettingException() {
		 System.out.println("the game has already started");
	}
}
