package jumpingalien.model.exceptions;
/**
 * 
 * A class to check for an illegal position
 * 
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus
 * 
 *
 */
@SuppressWarnings("serial")
public class IllegalPositionException extends Exception {		
	
		/**
		 * Variable registering the x-position evolved in this illegal 
		 * position exception.
		 */
		private final int xPos;
		/**
		 * Variable registering the y-position evolved in this illegal 
		 * position exception.
		 */
		private final int yPos;

		/**
		 * initialize this new illegal position exception with a given position
		 * @param	xPos
		 * 			the x position
		 * @param 	yPos
		 * 			the y position
		 * @post	the value of the new illegal position exception is equal to 
		 * 			the given positions
		 * 			| new.getXPos() = xPos
		 * 			| new.getYPos() = yPos
		 */
		public IllegalPositionException(int xPos, int yPos){
			this.xPos = xPos;
			this.yPos = yPos;
		}
		
		public int getXPos() {
			return xPos;
		}
		public int getYPos() {
			return yPos;
		}
}
