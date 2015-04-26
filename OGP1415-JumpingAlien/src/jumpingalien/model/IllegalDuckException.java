package jumpingalien.model;
/**
 * 
 * A class to check for an illegal ducking operation
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus 
 *
 */
@SuppressWarnings("serial")
public class IllegalDuckException extends Exception {
	
	/**
	 * a boolean indicating if the Mazub is ducked
	 */
	private boolean duck;
	/**
	 * returns true if the Mazub is ducked
	 * @return 
	 * 			| duck
	 */
	public boolean getDuck() {
		return duck;
	}
	/**
	 * initialize the new illegal duck exception with a given boolean
	 * @post the value of the exception is equal to the given boolean
	 * 			new.getDuck = duck
	 */
	public IllegalDuckException(boolean ducked) {
		this.duck = ducked;
	}
	
}
