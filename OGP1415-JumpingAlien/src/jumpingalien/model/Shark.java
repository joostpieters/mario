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
		this.setNbHitpoints(INITHITPOINTS);
	}
	
	private int x_pos;
	private int y_pos;
	private Sprite[] sprites;
	private World world;
	/**
	 * the x position (horizontal position) after dt seconds
	 */
	private double new_x_pos;
	/**
	 * the y position (vertical position) after dt seconds
	 */
	private double new_y_pos;
	/**
	 * the difference between the new x_position (new_x_pos) and
	 * the previous x_pos (x_pos)
	 */
	private double x_difference;
	/**
	 * the difference between the new y_position (new_y_pos) and
	 * the previous y_pos (y_pos)
	 */
	private double y_difference;
	/**
	 * the orientation of the shark
	 */
	private Orientation orientation  = Orientation.LEFT;
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
	 * the vertical acceleartion of a shark
	 */
	private double yAcc;
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
	 * the vertical acceleration at which mazub falls
	 */
	private static double FALL_ACC = -10;
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
	private int hitpoints;
	private double xSpeed;
	private double ySpeed;
	private double REMAININGTIME = 0.6;
	
	private boolean isDying = false;
	
	private double timeSinceDeath = 0;
	
	/**
	 * The boolean to reflect or shark is falling or not
	 */
	private boolean falling = false;	
	
	private boolean isFalling() {
		return falling;
	}
	
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
	private static double getJUMPSPEED() {
		return JUMP_SPEED;
	}
	/**
	 * Returns the acceleration when the shark falls
	 * @return FALL_ACC
	 */
	@Basic @Immutable @Raw 
	private double getFALLACC() {
		return FALL_ACC;
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
	private Orientation getOrientation() {
		return orientation;
	}
	/**
	 * Returns the difference between the real horizontal position
	 * and the rounded down value 
	 * @return x_difference
	 */
	@Raw 
	private double getXDifference() {
		return x_difference;
	}
	/**
	 * Returns the horizontal position after dt seconds
	 * @return new_x_pos
	 */
	@Raw 
	private double getNewXPos() {
		return new_x_pos;
	}	
	
	/**
	 * Returns the difference between the real vertical position
	 * and the rounded down value 
	 * @return y_difference
	 */
	@Raw 
	private double getYDifference() {
		return y_difference;
	}
	/**
	 * Returns the vertical position after dt seconds
	 * @return new_y_pos
	 */
	@Raw 
	private double getNewYPos() {
		return new_y_pos;
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
	

	private int getNbHitpoints() {
		return this.hitpoints;
	}
	
	private double getXSpeed() {
		return this.xSpeed;
	}
	private double getYSpeed() {
		return this.ySpeed;
	}
	/**
	 *  Returns the vertical acceleration 
	 * @return the vertical acceleration
	 * 			| YAcc
	 */
	@Basic @Raw 
	private double getYAcc() {
		return yAcc;
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
	
	private World getWorld() {
		return this.world;
	}
	
//	SETTERS
	/**
	 * Sets the horizontal position to the rounded down value of x
	 * @param x
	 * 			The new value for the horizontal position
	 */
	@Raw 
	private void setXPos(double x) {
		this.x_pos = (int) Math.floor(x);
	}
	/**
	 * Sets the vertical position to the rounded down value of y
	 * @param y
	 * 			The new value for the vertical position
	 */	
	@Raw 
	private void setYPos(double y) {
		this.y_pos = (int) Math.floor(y);
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	/**
	 * @param sprites the sprites to set
	 */
	private void setSprite(Sprite[] sprites) {
		this.sprites = sprites;
	}
	/**
	 * Sets the orientation of the shark to right
	 * @post orientation == Orientation.RIGHT
	 */
	@Raw 
	private void setOrientationRight() {
		this.orientation = Orientation.RIGHT;
	}
	/**
	 * Sets the orientation of the shark to left
	 * @post orientation == Orientation.LEFT
	 */
	@Raw 
	private void setOrientationLeft() {
		this.orientation = Orientation.LEFT;
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
	 * Sets the difference between the reel y position and the
	 * rounded down y position to a new value y_difference
	 * @param y_difference
	 * 			The new value for theyx_difference of Maxub
	 */	
	@Raw 
	private void setYDifference(double y_difference) {
		this.y_difference = y_difference;
	}
	/**
	 * Sets the new horizontal position to a new value
	 * @param x
	 * 			The new value for the new horizontal position
	 */
	@Raw 
	private void setNewXPos(double x) {
		this.new_x_pos = x;
	}
	/**
	 * Sets the new vertical position to a new value
	 * @param y
	 * 			The new value for the new vertical position
	 */
	@Raw 
	private void setNewYPos(double y) {
		this.new_y_pos = y;
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
	/**
	 * Sets the vertical acceleration to a new value
	 * @param yAcc
	 * 			the new value of the vertical acceleration
	 */
	@Raw 
	private void setYAcc(double yAcc) {
		this.yAcc = yAcc;
	}
	private void setTimeSinceDeath(double t) {
		this.timeSinceDeath += t;
	}
	
	private void setDying() {
		this.isDying = true;
	}
	/**
	 * Marks the boolean falling as true
	 * @post falling == true
	 */
	private void setFalling() {
		this.falling = true;
	}	
	/**
	 * Marks the boolean falling as false
	 * @post falling == false
	 */
	private void endFalling() {
		this.falling = false;
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
		if (this.getOrientation() == Orientation.RIGHT) {
			this.setNewXPos(this.getXPos() + this.getXSpeed()*100*dt
					+ this.getXDifference());		
		}
		else if (this.getOrientation() == Orientation.LEFT) {
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
			this.setYSpeed(Shark.getJUMPSPEED());
		}
		if (this.getNewYPos() <= 0) {
			this.endFall();
			this.setNewYPos(0);
		}
		if (this.getNewYPos() > Shark.getMAXYVALUE()) {
			this.setNewYPos(Shark.getMAXYVALUE());
			this.setYSpeed(0);
		}
		this.setYPos(this.getNewYPos());	
		this.setYDifference(this.getNewYPos() - this.getYPos());
	}
	
	private void fall() {
		if (this.getYPos() > 0){
			this.setYAcc(this.getFALLACC());
			this.setFalling();
		}
	} 
	/**
	 * Fall ends
	 * @effect	the acceleration is set to 0
	 * 		|	yAcc == 0
	 * 		|	ySpeed == 0
	 * 		|	endFalling()
	 */
	private void endFall() {
		this.setYAcc(0);
		this.setYSpeed(0);
		this.endFalling();		
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
		this.setWorld(null);
	}
	/**
	 * Return the current sprite image for the given shark.
	 * 
	 * @return The current sprite image for the given shark, determined by its
	 *         orientation as defined in the assignment.
	 */
	public Sprite getCurrentSprite(){
		assert isValidSprite(this.getSprite());
		if (this.getOrientation() == Orientation.RIGHT) {
			return sprites[1];
		}
		else {
			return sprites[0];
		}
	}

	
}
