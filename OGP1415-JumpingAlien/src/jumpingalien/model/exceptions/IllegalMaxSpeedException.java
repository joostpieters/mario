package jumpingalien.model.exceptions;
/**
 * 
 * A class to check for an illegal maxSpeed 
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus 
 *
 */
@SuppressWarnings("serial")
public class IllegalMaxSpeedException extends Exception {
	
	/**
	 * the maximum speed mazub can reach when he's moving horizontally
	 */
	private final int maxSpeed;

	/**
	 * initialize this new illegal speed exception with a given
	 * maximum speed
	 * @param maxSpeed
	 * 			the maximum speed mazub can reach (when moving
	 * 			horizontally)
	 * @post	the value of the new illegal max speed exception is equal to 
	 * 			the given maxSpeed
	 * 			| new.getMaxSpeed = maxSpeed
	 */
	public IllegalMaxSpeedException(int maxSpeed){
		this.maxSpeed = maxSpeed;
	}
	
	public double getMaxSpeed() {
		return maxSpeed;
	}
}