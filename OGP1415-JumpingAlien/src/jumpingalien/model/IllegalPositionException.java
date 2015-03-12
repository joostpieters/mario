package jumpingalien.model;
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
		private final int x_pos;
		/**
		 * Variable registering the y-position evolved in this illegal 
		 * position exception.
		 */
		private final int y_pos;

		/**
		 * initialize this new illegal position exception with a given position
		 * @param	x_pos
		 * 			the x position
		 * @param 	y_pos
		 * 			the y position
		 * @post	the value of the new illegal position exception is equal to 
		 * 			the given positions
		 * 			| new.getXPos() = x_pos
		 * 			| new.getYPos() = y_pos
		 */
		public IllegalPositionException(int x_pos, int y_pos){
			this.x_pos = x_pos;
			this.y_pos = y_pos;
		}
		
		public int getXPos() {
			return x_pos;
		}
		public int getYPos() {
			return y_pos;
		}
}
