package jumpingalien.model.exceptions;
/**
 * 
 * A class to check for an when the given pixel is not the left bottom
 * pixel of a tile
 * 
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus
 * 
 *
 */
@SuppressWarnings("serial")
public class IllegalPixelException extends Exception {
	
	/**
	 * Variable registering the x-pixel evolved in this illegal 
	 * pixel exception.
	 */
	private final int pixelX;
	/**
	 * Variable registering the y-pixel evolved in this illegal 
	 * pixel exception.
	 */
	private final int pixelY;

	/**
	 * initialize this new illegal pixel exception with a given pixel
	 * @param	pixelX
	 * 			the x pixel
	 * @param 	pixelY
	 * 			the y pixel
	 * @post	the value of the new illegal pixel exception is equal to 
	 * 			the given positions
	 * 			| new.getPixelX() = pixelX
	 * 			| new.getPixelY() = pixelY
	 */
	public IllegalPixelException(int pixelX, int pixelY) {
		this.pixelX = pixelX;
		this.pixelY = pixelY;
	}
	
	/**
	 * returns the horizontal pixel
	 * @return the x coordinate
	 * 			| pixelX
	 */
	public int getPixelX() {
		return pixelX;
	}
	/**
	 * returns the vertical pixel
	 * @return the y coordinate
	 * 			| pixelY
	 */
	public int getPixelY() {
		return pixelY;
	}

}
