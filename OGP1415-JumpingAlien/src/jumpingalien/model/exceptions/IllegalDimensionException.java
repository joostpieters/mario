package jumpingalien.model.exceptions;
/**
 * 
 * A class to check for an illegal dimension
 * 
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus
 * 
 *
 */
@SuppressWarnings("serial")
public class IllegalDimensionException extends Exception {
	
	/**
	 * Variable registering the dimension evolved in this illegal 
	 * dimension exception.
	 */
	private final int[] dimension;
	
	/**
	 * initialize this new illegal dimension exception with a given dimension
	 * @param	dimension
	 * 			the dimension
	 * @post	the value of the new illegal dimension exception is equal to 
	 * 			the given dimension
	 * 			| new.getDimension() = dimension
	 */
	public IllegalDimensionException(int[] dimension){
		this.dimension = dimension;
	}
	
	public int[] getDimension() {
		return dimension;
	}
	
}
