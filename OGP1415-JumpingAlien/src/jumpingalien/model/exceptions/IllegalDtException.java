package jumpingalien.model.exceptions;
/**
 * 
 * A class to check for an illegal dt
 * 
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus
 * 
 *
 */
@SuppressWarnings("serial")
public class IllegalDtException extends Exception {
	
	/**
	 * a small time interval, for example the new_speed after 
	 * deltaT seconds is given by speed + deltaT*acc 
	 * (acc =  acceleration)
	 */	
	private final double dt;

	/**
	 * initialize this new illegal dt exception with a given dt
	 * @param	dt
	 * 			the time interval
	 * @post	the value of the new illegal dt exception is equal to 
	 * 			the given dt
	 * 			| new.getDt = dt
	 */
	public IllegalDtException(double dt){
		this.dt = dt;
	}
	
	public double getDt() {
		return dt;
	}
}
