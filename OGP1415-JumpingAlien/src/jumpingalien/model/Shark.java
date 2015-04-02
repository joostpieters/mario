package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.util.Sprite;

public class Shark extends GameObject {
	

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
	public Shark(int xPos,int yPos, Sprite[] sprites ) 
			throws IllegalPositionException, IllegalSpriteException {
				if(!isValidPosition(xPos,yPos))
					throw new IllegalPositionException(x_pos,y_pos); 
				if (!isValidSprite(sprites))
					throw new IllegalSpriteException(sprites);
		super(xPos,yPos,sprites);
		this.setNbHitpoints(INIT_HIT_POINTS);
	}
	


	/**
	 * the initial amount of hitpoints a shark possesses
	 */
	private static int INIT_HIT_POINTS = 100;
	
	
	/**
	 * the maximum horizontal speed a shark can reach
	 */
	private static double MAX_X_SPEED = 4;
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
	private static int MIN_MOVEMENT_DURATION = 1;
	/**
	 * the maximal duration of a movement period (4s)
	 */
	private static int MAX_MOVEMENT_DURATION = 4;
	/**
	 * the amount of hitpoints a shark loses when touching 
	 * a mazub or a slime
	 */
	private int CONTACT_DAMAGE = 50;
	
	private int HIT_POINTS = 1;

	
	private double REMAINING_TIME = 0.6;
	
	
	
	
	
	/**
	 * The boolean to reflect or shark is falling or not
	 */
	private boolean falling = false;	
	
	private boolean isFalling() {
		return falling;
	}
	
// GETTERS
	
	private static int getInitHitpoints() {
		return INIT_HIT_POINTS;
	}
	
	private static double getMaxXSpeed() {
		return  MAX_X_SPEED;
	}
	private static double getJumpSpeed() {
		return JUMP_SPEED;
	}
	/**
	 * Returns the acceleration when the shark falls
	 * @return FALL_ACC
	 */
	@Basic @Immutable @Raw 
	private double getFallAcc() {
		return FALL_ACC;
	}
	private static int getMaxMovementDuration() {
		return MAX_MOVEMENT_DURATION;
	}
	private static int getMinMovementDuration() {
		return MIN_MOVEMENT_DURATION;
	}	
	private double getRemainingTime() {
		return this.REMAINING_TIME;
	}

	


	
//	SETTERS

	
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
	


	
	private void advanceX(double dt) {
		if (this.getOrientation() == Orientation.RIGHT) {
			this.setNewXPos(this.getXPos() + this.getXSpeed()*100*dt);	
		}
		else if (this.getOrientation() == Orientation.LEFT) {
			this.setNewXPos(this.getXPos() - this.getXSpeed()*100*dt);
		}
		
		if ((this.getNewXPos() < Shark.getMinXValue()) || 
				(this.getNewXPos() > Shark.getMaxXValue())){
			this.remove();
		}
		
		this.setXPos(this.getNewXPos());
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
	private void advanceY(double dt){	
		if ((this.getYPos() > 0) && (!this.isFalling())){
			fall();
		}
		this.setNewYPos(this.getYPos() + this.getYSpeed()*100*dt + 0.5 * 100 *
				this.getYAcc() * Math.pow(dt,2));
		this.setYSpeed(this.getYSpeed() + dt * this.getYAcc());
		if ( ! this.isValidYSpeed()) {
			this.setYSpeed(Shark.getJumpSpeed());
		}
		if (this.getNewYPos() <= 0) {
			this.endFall();
			this.setNewYPos(0);
		}
		if (this.getNewYPos() > Shark.getMaxYValue()) {
			this.setNewYPos(Shark.getMaxYValue());
			this.setYSpeed(0);
		}
		this.setYPos(this.getNewYPos());	
	}
	
	private void fall() {
		if (this.getYPos() > 0){
			this.setYAcc(this.getFallAcc());
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
		
		this.advanceX(dt);
		this.advanceY(dt);
		
		if (this.isDying()) {
			setTimeSinceDeath(this.getTimeSinceDeath() + dt);
			if (this.getTimeSinceDeath() >= this.getRemainingTime()) {
				this.remove();
			}
		}
		else if (this.getNbHitpoints() <= 0) {
			this.die();
		}
	}	
	
	private void remove() {
		this.world.removeShark(this);
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
