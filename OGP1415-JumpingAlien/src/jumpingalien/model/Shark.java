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
		this.setNbHitpoints(INITHITPOINTS);
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
	private int hitpoints;
	private double xSpeed;
	private double ySpeed;
	private double REMAININGTIME = 0.6;
	
	private boolean isDying = false;
	
	private double timeSinceDeath = 0;
	
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
	private int getNbHitpoints() {
		return this.hitpoints;
	}
	
	private double getXSpeed() {
		return this.xSpeed;
	}
	private double getYSpeed() {
		return this.ySpeed;
	}
	private double getREMAININGTIME() {
		return this.REMAININGTIME;
	}
	
	private double getTimeSinceDeath() {
		return this.timeSinceDeath;
	}
	
	private boolean isDying() {
		return this.isDying;
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
	
	private void setNbHitpoints(int nb) {
		this.hitpoints = nb;
	}
	
	private void setXSpeed(double speed) {
		this.xSpeed = speed;
	}
	private void setYSpeed(double speed) {
		this.ySpeed = speed;
	}
	private void setTimeSinceDeath(double t) {
		this.timeSinceDeath += t;
	}
	
	private void setDying() {
		this.isDying = true;
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
	
	private void advance_x(double dt) {
		if (this.getOrientation() == "right") {
			this.setNewXPos(this.getXPos() + this.getXSpeed()*100*dt
					+ this.getXDifference());		
		}
		else if (this.getOrientation() == "left") {
			this.setNewXPos(this.getXPos() - this.getXSpeed()*100*dt
					+ this.getXDifference());
		}
		
		if ((this.getNewXPos() < Shark.getMINXVALUE()) || 
				(this.getNewXPos() > Shark.getMAXXVALUE())){
			this.remove();
		}
		
		this.setXPos(this.getNewXPos());
		this.setXDifference(this.getNewXPos() - this.getXPos());
	}
	
	/**
	 * Changes the vertival position of Mazub with the current speed and accelleration
	 * and with a given time interval dt. The method will keep Mazub between the boundaries
	 * of the game world by setting his speed to zero if he jumps too high.
	 * Changes the vertical speed of Mazub. If Mazub comes on the ground, his fall will ends.
	 * @param dt: A small time interval
	 * @effect	The new vertical position is changed
	 * 			| new_y_pos == this.getYPos() +  this.getYSpeed()*100*dt + 0.5 * 100 *
	 * 			| 				this.getYAcc() * Math.pow(dt,2) + this.getYDifference()
	 * @effect 	The ySpeed is changed given the acceleration and the time interval
	 * 			| ySpeed += dt * this.getYAcc()
	 * @effect	If the new speed is not valid (greater then the jumpspeed), the speed
	 * 			is set to JUMP_SPEED
	 * 			| if ( ! isValidSpeed())
	 * 			| 	then ySpeed = JUMP_SPEED
	 * @effect 	If the new position is the ground or lower, his fall will end and
	 * 			his vertical position will be 0
	 * 			| if new_y_pos == 0
	 * 			| 	then endfall()
	 * 			| 		 new_y_pos == 0
	 * @effect  If the vertical position of Mazub would exceed the upper boundary, 
	 * 			Mazubs speed is set to zero and het new vertical position to MAX_Y_VALUE
	 * 			| if new_y_pos > MAX_Y_VALUE
	 * 			| 	then new_y_pos == MAX_Y_VALUE
	 * 			| 		 ySpeed == 0
	 * @effect the current vertical position is changed to the rounded down new position
	 * 			and the difference between the two values is stored in y_difference
	 * 			| y_pos == new_y_pos
	 * 			| y_difference == new_y_pos - y_pos
	 */
	private void advance_y(double dt){	
		if ((this.getYPos() > 0) && (!this.isFalling())){
			fall();
		}
		this.setNewYPos(this.getYPos() + this.getYSpeed()*100*dt + 0.5 * 100 *
				this.getYAcc() * Math.pow(dt,2) + this.getYDifference());
		this.setYSpeed(this.getYSpeed() + dt * this.getYAcc());
		if ( ! this.isValidYSpeed()) {
			this.setYSpeed(this.getJUMPSPEED());
		}
		if (this.getNewYPos() <= 0) {
			this.endFall();
			this.setNewYPos(0);
		}
		if (this.getNewYPos() > Mazub.getMAXYVALUE()) {
			this.setNewYPos(Mazub.getMAXYVALUE());
			this.setYSpeed(0);
		}
		this.setYPos(this.getNewYPos());	
		this.setYDifference(this.getNewYPos() - this.getYPos());
	}
	
	public void advanceTime(double dt) {
		
		this.advance_x(dt);
		this.advance_y(dt);
		
		if (this.isDying()) {
			setTimeSinceDeath(this.getTimeSinceDeath() + dt);
			if (this.getTimeSinceDeath() >= this.getREMAININGTIME()) {
				this.remove();
			}
		}
		else if (this.getNbHitpoints() <= 0) {
			this.die();
		}
	}	
	
	private void die() {
		this.setXSpeed(0);
		this.setYSpeed(0);
		this.setDying();
	}
	
// TODO hoe deleten?
	private void remove() {
		this.world = null;
//		Plant plant = null;
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
