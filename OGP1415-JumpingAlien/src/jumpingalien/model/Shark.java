package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.util.Sprite;

public class Shark {
	

	/**
	 * Creates a new shark, located at the provided pixel location (x, y).
	 * The returned shark should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the shark's initial position
	 * @param y
	 *            The y-coordinate of the shark's initial position
	 * @param sprites
	 *            An array of sprites for the new shark
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for the shark
	 * 			| !isValidPosition(x_pos,y_pos)
	 * @throws IllegalSpriteException
	 * 			The given sprite is not valid
	 * 			| !isValidSprite(sprites) 
	 */
	public Shark(int x_pos,int y_pos, Sprite[] sprites ) 
			throws IllegalPositionException, IllegalSpriteException {
				if(!isValidPosition(x_pos,y_pos))
					throw new IllegalPositionException(x_pos,y_pos); 
				if (!isValidSprite(sprites))
					throw new IllegalSpriteException(sprites);
		this.setXPos(x_pos);
		this.setYPos(y_pos);
		this.setSprite(sprites);
		this.setNumberOfSharksPlusOne();
	}
	
	private int x_pos;
	private int y_pos;
	private Sprite[] sprites;
	/**
	 * the orientation of the shark
	 */
	private String orientation  = "left";
	/**
	 * the minimal value for x_pos
	 */
	private static int MIN_X_VALUE = 0;
	/**
	 * the maximal value for x_pos
	 */
	private static int MAX_X_VALUE = 1023;
	/**
	 * the minimal value for y_pos
	 */
	private static int MIN_Y_VALUE = 0;
	/**
	 * the maximal value for y_pos
	 */
	private static int MAX_Y_VALUE = 767;
	/**
	 * the initial amount of hitpoints a shark possesses
	 */
	private static int INITHITPOINTS = 100;
	/**
	 * the horizontal acceleration of a shark
	 */
	private double xAcc = 1.5;
	/**
	 * the maximum horizontal speed a slime can reach
	 */
	private static double MAXXSPEED = 4;
	/**
	 * the vertical speed at which the shark moves when 
	 * startJump() is initiated
	 */
	private static int JUMP_SPEED = 2;
	/**
	 * the minimal duration of a movement period (1s)
	 */
	private static int MINMOVEMENTDURATION = 1;
	/**
	 * the maximal duration of a movement period (4s)
	 */
	private static int MAXMOVEMENTDURATION = 4;
	/**
	 * the amount of hitpoints a shark loses when touching 
	 * a mazub or a slime
	 */
	private int CONTACTDAMAGE = 50;
	
	private int HITPOINTS = 1;
	/**
	 * a variable containing the amount of characters Shark
	 */
	private int numberOfSharks;
	
// GETTERS
	/**
	 * returns the minimal value of x_pos
	 * @return MIN_X_VALUE
	 */
	@Basic @Immutable @Raw 
	private static int getMINXVALUE() {
		return MIN_X_VALUE;
	}
	/**
	 * returns the maximal value of x_pos
	 * @return MAX_X_VALUE
	 */
	@Basic @Immutable @Raw 
	private static int getMAXXVALUE() {
		return MAX_X_VALUE;
	}
	/**
	 * returns the minimal value of y_pos
	 * @return MIN_Y_VALUE
	 */
	@Basic @Immutable @Raw 
	private static int getMINYVALUE() {
		return MIN_Y_VALUE;
	}/**
	 * returns the maximal value of y_pos
	 * @return MAX_Y_VALUE
	 */
	@Basic @Immutable @Raw 
	private static int getMAXYVALUE() {
		return MAX_Y_VALUE;
	}
	private static int getINITHITPOINTS() {
		return INITHITPOINTS;
	}
	private double getXAcc() {
		return xAcc;
	}
	private static double getMAXXSPEED() {
		return  MAXXSPEED;
	}
	private static int getMaxMovementDuration() {
		return MAXMOVEMENTDURATION;
	}
	private static int getMinMovementDuration() {
		return MINMOVEMENTDURATION;
	}
	/**
	 * @return the x
	 */
	private int getXPos() {
		return x_pos;
	}

	/**
	 * @return the y
	 */
	private int getYPos() {
		return y_pos;
	}

	/**
	 * @return the sprites
	 */
	private Sprite[] getSprite() {
		return sprites;
	}
	private String getOrientation() {
		return orientation;
	}
	/**
	 * Returns the current location of the given shark.
	 * 
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given shark's bottom left pixel in the world.
	 */
	public int[] getLocation(){
		return new int[]{this.getXPos(),this.getYPos()};
	}
	
	public int getNumberOfSharks() {
		return this.numberOfSharks;
	}
	
	
//	SETTERS
	/**
	 * @param x the x to set
	 */
	private void setXPos(int x) {
		this.x_pos = x;
	}

	/**
	 * @param y the y to set
	 */
	private void setYPos(int y) {
		this.y_pos = y;
	}
	
	/**
	 * @param sprites the sprites to set
	 */
	private void setSprite(Sprite[] sprites) {
		this.sprites = sprites;
	}
	/**
	 * Sets the orientation of the shark to right
	 * @post orientation == "right"
	 */
	@Raw 
	private void setOrientationRight() {
		this.orientation = "right";
	}
	/**
	 * Sets the orientation of the shark to left
	 * @post orientation == "left"
	 */
	@Raw 
	private void setOrientationLeft() {
		this.orientation = "left";
	}
	public void setNumberOfSharksPlusOne() {
		if (this.getNumberOfSharks() == 0) {
			this.numberOfSharks = 1;
		}
		else {
			this.numberOfSharks += 1;
		}
	}
//	Validations
	private boolean isValidSprite(Sprite[] sprites) {
		return sprites.length == 2;
	}
	/**
	 * 	Checks whether the given positions are valid positions for 
	 *  any shark
	 * @return 	True if the horizontal position x_pos and 
	 *			and the vertical position y_pos stay in the 
	 *			game world.
	 *			| ((x_pos >= MIN_X_VALUE && x_pos <= MAX_X_VALUE
	 *				&& y_pos >= MIN_Y_VALUE && y_pos <= MAX_Y_VALUE))
	 */
	public boolean isValidPosition(int x_pos, int y_pos) {
		return ((x_pos >= Shark.getMINXVALUE())
				&& (x_pos <= Shark.getMAXXVALUE())
				 && (y_pos >= Shark.getMINYVALUE())
				 && (y_pos <= Shark.getMAXYVALUE()));
	}
	
	/**
	 * starts the action period for an object
	 */
	public void start<action> {
		
	}
	
	/**
	 * ends the action period for an object
	 */
	public void stop<action> {
		
	}
	
	public void advanceTime(double dt) {		
		
	}
	
	/**
	 * Return the current sprite image for the given shark.
	 * 
	 * @return The current sprite image for the given shark, determined by its
	 *         orientation as defined in the assignment.
	 */
	public Sprite getCurrentSprite(){
		assert isValidSprite(this.getSprite());
		if (this.getOrientation() == "right") {
			return sprites[1];
		}
		else {
			return sprites[0];
		}
	}

	
}
