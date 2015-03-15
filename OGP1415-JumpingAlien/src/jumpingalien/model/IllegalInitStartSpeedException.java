package jumpingalien.model;
/**
 * 
 * A class to check for an illegal initSartSpeed
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus 
 *
 */
@SuppressWarnings("serial")
public class IllegalInitStartSpeedException extends Exception {
	
	/**
	 * the initial speed of mazub when he starts moving horizontally
	 */	
	private final int initStartSpeed;

	/**
	 * initialize this new illegal speed exception with a given
	 * initial speed 
	 * @param	initStartSpeed
	 * 			the initial speed of mazub
	 * @post	the value of the new illegal initial speed exception is equal to 
	 * 			the given initStartSpeed
	 * 			| new.getInitStartSpeed = initStartSpeed
	 */
	public IllegalInitStartSpeedException(int initStartSpeed){
		this.initStartSpeed = initStartSpeed;
	}
	
	public int getinitStartSpeed() {
		return initStartSpeed;
	}
}