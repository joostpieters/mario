package jumpingalien.model.exceptions;
/**
 * 
 * A class to check for an illegal amount of characters
 * 
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus
 * 
 *
 */
@SuppressWarnings("serial")
public class IllegalAmountOfCharactersException extends Exception {
	
	/**
	 * initialize this new illegal amount of characters exception with the given value
	 */
	public IllegalAmountOfCharactersException(int amount){
		this.amountOfCharacters = amount;
	}
	
	
	/**
	 * the amount of characters
	 */
	private final int amountOfCharacters;
	/**
	 * 
	 */
	public int getAmountOfCharacters() {
		return this.amountOfCharacters;
	}
	
}