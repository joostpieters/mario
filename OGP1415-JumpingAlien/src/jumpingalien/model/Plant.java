package jumpingalien.model;


import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.util.Sprite;

public class Plant {
	
	/**
	 * Creates a new plant, located at the provided pixel location (x, y).
	 * The returned plant should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the plant's initial position
	 * @param y
	 *            The y-coordinate of the plant's initial position
	 * @param sprites
	 *            An array of sprites for the new plant
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for the plant
	 * 			| !isValidPosition(x_pos,y_pos)
	 * @throws IllegalSpriteException
	 * 			The given sprite is not valid
	 * 			| !isValidSprite(sprites) 
	 */
	public Plant(int x_pos,int y_pos, Sprite[] sprites ) 
			throws IllegalPositionException, IllegalSpriteException {
				if(!isValidPosition(x_pos,y_pos))
					throw new IllegalPositionException(x_pos,y_pos); 
				if (!isValidSprite(sprites))
					throw new IllegalSpriteException(sprites);
		this.setXPos(x_pos);
		this.setYPos(y_pos);
		this.setSprite(sprites);
		this.setNumberOfPlantsPlusOne();
		}
	

	private Sprite[] sprites;
	
	/**
	 *  the horizontal position 
	 */
	private int x_pos = 0;
	/**
	 * the vertical position
	 */
	private int y_pos = 0;
	/**
	 * the x position (horizontal position) after dt seconds
	 */
	private double new_x_pos;
	/**
	 * the initial amount of hitpoints a plant possesses
	 */
	
	private int INITHITPOINTS = 1;
	/**
	 * the horizontal speed of a plant
	 */
	private double xSpeed = 0.5;
	/**
	 * the orientation of the plant
	 */
	private String orientation  = "left";
	/**
	 * 
	 */
	private double TIMEUNTILCHANGEORIENTATION = 0.5;	
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
	 * the difference between the new x_position (new_x_pos) and
	 * the previous x_pos (x_pos)
	 */
	private double x_difference;
	/**
	 * the time a plant is moving in 1 direction
	 */
	private double timeSameOrientation = 0;
	/**
	 * a variable containing the amount of characters Plant
	 */
	private int numberOfPlants;

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
	/**
	 * Returns the horizontal position of the plant after dt seconds
	 * @return new_x_pos
	 */
	@Raw 
	private double getNewXPos() {
		return new_x_pos;
	}
	/**
	 * Returns the x position
	 * @return x_pos
	 */
	@Basic @Raw 
	private int getXPos() {
		return x_pos;
	}
	
	/**
	 * Returns the y position
	 * @return y_pos
	 */
	@Basic @Raw 
	private int getYPos() {
		return y_pos;
	}
	/**
	 * Returns the difference between the real horizontal position of the plant
	 * and the rounded down value 
	 * @return x_difference
	 */
	@Raw 
	private double getXDifference() {
		return x_difference;
	}
	/**
	 * 
	 */
	private double getTimeSameOrientation() {
		return timeSameOrientation;
	}
	private int getINITHITPOINTS() {
		return INITHITPOINTS;
	}
	private double getXSpeed() {
		return xSpeed;
	}
	private String getOrientation() {
		return orientation;
	}
	private double getTIMEUNTILCHANGEORIENTATION() {
		return TIMEUNTILCHANGEORIENTATION;
	}

	/**
	 * @return the sprites
	 */
	private Sprite[] getSprite() {
		return sprites;
	}

	/**
	 * Returns the current location of the given plant.
	 * 
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given plant's bottom left pixel in the world.
	 */
	public int[] getLocation(){
		return new int[]{this.getXPos(),this.getYPos()};
	}
	
	public int getNumberOfPlants() {
		return this.numberOfPlants;
	}
	
//	SETTERS
	
	
	/**
	 * @param sprites the sprites to set
	 */
	private void setSprite(Sprite[] sprites) {
		this.sprites = sprites;
	}

	/**
	 * Sets the horizontal position of the plant to the rounded down value of x
	 * @param x
	 * 			The new value for the horizontal position
	 */
	@Raw 
	private void setXPos(double x) {
		this.x_pos = (int) Math.floor(x);
	}
	/**
	 * Sets the vertical position of the plant to the rounded down value of y
	 * @param y
	 * 			The new value for the vertical position
	 */	
	@Raw 
	private void setYPos(double y) {
		this.y_pos = (int) Math.floor(y);
	}
	/**
	 * Sets the orientation of the plant to right
	 * @post orientation == "right"
	 */
	@Raw 
	private void setOrientationRight() {
		this.orientation = "right";
	}
	/**
	 * Sets the orientation of plant to left
	 * @post orientation == "left"
	 */
	@Raw 
	private void setOrientationLeft() {
		this.orientation = "left";
	}
	/**
	 * Sets the new horizontal position of the plant to a new value
	 * @param x
	 * 			The new value for the new horizontal position
	 */
	@Raw 
	private void setNewXPos(double x) {
		this.new_x_pos = x;
	}
	/**
	 * Sets the difference between the reel x position and the
	 * rounded down x position to a new value x_difference
	 * @param x_difference
	 * 			The new value for the x_difference of Maxub
	 */
	@Raw 
	private void setXDifference(double x_difference) {
		this.x_difference = x_difference;
	}
	
	/**
	 * 
	 */
	private void setTimeSameOrientation(double t) {
		this.timeSameOrientation = t;
	}
	
	/**
	 * 
	 */
	private void setOrientation(String s) {
		this.orientation = s;
	}
	
	public void setNumberOfPlantsPlusOne() {
		if (this.getNumberOfPlants() == 0) {
			this.numberOfPlants = 1;
		}
		else {
			this.numberOfPlants += 1;
		}
	}
	
// VALIDATIONS 
	/**
	 * 	Checks whether the given positions are valid positions for 
	 *  any plant
	 * @return 	True if the horizontal position x_pos and 
	 *			and the vertical position y_pos stay in the 
	 *			game world.
	 *			| ((x_pos >= MIN_X_VALUE && x_pos <= MAX_X_VALUE
	 *				&& y_pos >= MIN_Y_VALUE && y_pos <= MAX_Y_VALUE))
	 */
	public boolean isValidPosition(int x_pos, int y_pos) {
		return ((x_pos >= Plant.getMINXVALUE())
				&& (x_pos <= Plant.getMAXXVALUE())
				 && (y_pos >= Plant.getMINYVALUE())
				 && (y_pos <= Plant.getMAXYVALUE()));
	}
	
	private boolean isValidSprite(Sprite[] sprites) {
		return sprites.length == 2;
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
	
	/**
	 * changes the orientation of the plant
	 */
	private void changeOrientation() {
		if (this.getOrientation() == "right") {
			this.setOrientation("left");
		}
		else {
			this.setOrientation("right");
		}
	}

	public void advanceTime(double dt) {
		
		this.setTimeSameOrientation(this.getTimeSameOrientation() + dt);
		
		if (this.getOrientation() == "right") {
			this.setNewXPos(this.getXPos() + this.getXSpeed()*100*dt
					+ this.getXDifference());		
		}
		else if (this.getOrientation() == "left") {
			this.setNewXPos(this.getXPos() - this.getXSpeed()*100*dt
					+ this.getXDifference());
		}
		
		if (this.getNewXPos() < Plant.getMINXVALUE()){
			this.setNewXPos(Plant.getMINXVALUE());
		}
		else if (this.getNewXPos() > Plant.getMAXXVALUE()){
			this.setNewXPos(Plant.getMAXXVALUE());
		}
		
		this.setXPos(this.getNewXPos());
		this.setXDifference(this.getNewXPos() - this.getXPos());
		
		if (this.getTimeSameOrientation() > this.getTIMEUNTILCHANGEORIENTATION()) {
			this.changeOrientation();
		}
		
	}
	

	
	/**
	 * Return the current sprite image for the given plant.
	 * 
	 * @return The current sprite image for the given plant, determined by its
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
