package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class that describes the position of a gameObject
 * @author Pieter Van den Berghe, Ward Romanus
 *
 */
public class Position {
	public Position(int xPos, int yPos) {
		this.setXPos(xPos);
		this.setYPos(yPos);
	}
	
	/**
	 * the horizontal position of the object
	 */
	private double xPos = 0;
	/**
	 * Returns the horizontal position
	 * @return xPos
	 */
	@Basic @Raw 
	public double getXPos() {
		return xPos;
	}	
	/**
	 * Sets the horizontal position of mazub to the rounded down value of x
	 * @param x
	 * 			The new value for the horizontal position
	 */
	@Raw 
	public void setXPos(double x) {
		this.xPos = x;
	}
	
	/**
	 * the vertical position of the object
	 */
	private double yPos = 0;
	/**
	 * Returns the vertical position
	 * @return yPos
	 */
	@Basic @Raw 
	public double getYPos() {
		return yPos;
	}	
	
	/**
	 * Sets the vertical position of mazub to the rounded down value of y
	 * @param y
	 * 			The new value for the vertical position
	 */	
	@Raw 
	public void setYPos(double y) {
		this.yPos = y;
	}
	
	/**
	 * the orientation of Mazub
	 */
	private Orientation orientation  = Orientation.RIGHT;	
	/**
	 * Returns the orientation of Mazub
	 * @return orientation
	 */
	@Basic @Raw 
	public Orientation getOrientation() {
		return orientation;
	}
	/**
	 * Sets the orientation of Mazub to right
	 * @post orientation == "right"
	 */
	@Raw 
	public void setOrientationRight() {
		this.orientation = Orientation.RIGHT;
	}
	/**
	 * Sets the orientation of Mazub to left
	 * @post orientation == "left"
	 */
	@Raw 
	public void setOrientationLeft() {
		this.orientation = Orientation.LEFT;
	}
	
	
}
