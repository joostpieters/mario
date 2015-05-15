package jumpingalien.model;

import java.util.ArrayList;
import java.util.List;

import program.Program;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.model.exceptions.IllegalDtException;
import jumpingalien.model.exceptions.IllegalPositionException;
import jumpingalien.model.exceptions.IllegalSpriteException;
import jumpingalien.util.Sprite;

/**
 * A class that describes the GameObject Shark
 * @author Ward Romanus, Pieter Van den Berghe
 *
 */
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
	 * 			| !isValidPosition(xPos,yPos)
	 * @throws IllegalSpriteException
	 * 			The given sprite is not valid
	 * 			| !isValidSprite(sprites) 
	 */
	@Raw
	public Shark(int xPos,int yPos, Sprite[] sprites ) 
			throws IllegalPositionException, IllegalSpriteException {
		super(xPos,yPos,sprites);
		this.setHitpoints(Shark.getInitHitpoints());
		this.setMaxSpeed(Shark.getMaxXSpeed());
	}
	
	public Shark(int xPos,int yPos, Sprite[] sprites, Program program) 
			throws IllegalPositionException, IllegalSpriteException {
		super(xPos,yPos,sprites, program);
		this.setHitpoints(Shark.getInitHitpoints());
		this.setMaxSpeed(Shark.getMaxXSpeed());
	}
	
	/**
	 * the initial amount of hitpoints a shark possesses
	 */
	private static final int INIT_HITPOINTS = 100;
	/**
	 * returns the amount of hitpoints a Shark possesses when created
	 * @return the initial amount of hitpoints
	 * 			| INIT_HITPOINTS
	 */
	@Basic @Immutable
	private static int getInitHitpoints() {
		return INIT_HITPOINTS;
	}		
	/**
	 * the maximum horizontal speed a shark can reach
	 */
	private static final double MAX_X_SPEED = 4;
	/**
	 * returns the maximal horizontal speed a Shark can reach
	 * @return the max x-speed
	 * 			| MAX_X_SPEED	
	 */
	@Basic @Immutable
	private static final double getMaxXSpeed() {
		return  MAX_X_SPEED;
	}
	/**
	 * the vertical speed at which the shark moves when 
	 * startJump() is initiated
	 */
	private static final int JUMP_SPEED = 2;
	/**
	 * returns the vertical speed when a startJump gets initialized
	 * @return the vertical initial velocity when jumping
	 * 			| JUMP_SPEED
	 */
	@Basic @Immutable
	private static final double getJumpSpeed() {
		return JUMP_SPEED;
	}
	/**
	 * the horizontal acceleration
	 */
	private static final double MOVE_ACC = 1.5;
	/**
	 * returns the horizontal acceleration
	 * @return the acceleration when moving horizontally
	 * 			| MOVE_ACC
	 */
	@Basic @Immutable
	private static final double getMoveAcc() {
		return MOVE_ACC;
	}
	/**
	 * the amount of hitpoints a shark loses when touching 
	 * a mazub or a slime
	 */
	private static final int CONTACT_DAMAGE = 50;
	/**
	 * returns the amount of lost hitpoints when making contact
	 * with mazub or a slime
	 * @return the hitpoints to be deducted in case of contact
	 * 			| CONTACT_DAMAGE
	 */
	@Basic @Immutable
	public static final int getContactDamage() {
		return CONTACT_DAMAGE;
	}
	/**
	 * the maximal amount of hitpoints a shark may posses
	 */
	private static final int MAX_HITPOINTS = 100;
	/**
	 * the maximum amount of hitpoints
	 * @return MAX_HITPOINTS
	 */
	@Basic @Immutable @Override
	protected int getMaxHitpoints() {
		return MAX_HITPOINTS;
	}
	/**
	 * the amount of hitpoints a shark loses when in air
	 */
	private static final int LOSS_HITPOINTS_IN_AIR = 6;
	/**
	 * returns the amount of hitpoints to lose per 0.2 seconds in contact with air
	 * @return the hitpoints to lose
	 * 			| LOSS_HITPOINTS_IN_AIR
	 */
	@Basic @Immutable 
	private static final int getLossHitpointsInAir() {
		return LOSS_HITPOINTS_IN_AIR;
	}
	/**
	 * the time a Shark may live spend in air without losing any hitpoints
	 */
	private static final double TIME_UNTIL_CHOKE = 0.2;
	/**
	 * returns the time in seconds until a Shark chokes in air
	 * @return the time until deduction of hitpoints
	 * 			| TIME_UNTIL_CHOKE
	 */
	@Basic @Immutable 
	private static final double getTimeUntilChoke() {
		return TIME_UNTIL_CHOKE;
	}
	/**
	 * a boolean indicating if a Shark is in water
	 */
	private boolean inWater = true;
	/**
	 * returns true if the Shark is in water
	 * @return if the shark is in water
	 * 			| inWater
	 */
	@Basic 
	private boolean isInWater() {
		return inWater;
	}
	/**
	 * sets the value of inWater to true
	 * @post the value of inWater is true
	 * 			| inWater = true
	 */
	@Raw
	private void setInWater() {
		this.inWater = true;
	}
	/**
	 * sets the value of inWater to false
	 * @post the value of inWater is false
	 * 			| inWater = false
	 */
	@Raw
	private void setNotInWater() {
		this.inWater = false;
	}
	/**
	 * double that counts the time spend in air
	 */
	private double timeInAir = 0;
	/**
	 * returns the double timeInAir
	 * @return the time a Shark spend in air
	 * 			| timeInAir
	 */
	@Basic @Raw
	private double getTimeInAir() {
		return timeInAir;
	}
	/**
	 * Sets the timeInAir to a new value
	 * @param time
	 * 			the new value for timeInAir
	 * @pre time should always be bigger than or equal to zero
	 * 			| time >= 0
	 * @post the value of timeInAir is equal to the given double
	 * 			|  this.timeInAir = time
	 */
	@Raw
	private void setTimeInAir(double time) {
		assert time >= 0;
		this.timeInAir = time;
	}
	/**
	 * a double indicating the duration of a certain movement
	 */
	private double movementDuration = 0;
	/**
	 * returns the duration of the movement in seconds
	 * @return the duration
	 * 			| movementDuration
	 */
	@Basic @Raw
	private double getMovementDuration() {
		return movementDuration;
	}
	/**
	 * Sets movementDuration to a new value
	 * @param time
	 * 			the new value for movementDuration
	 * @pre  time should always be bigger than or equal to zero
	 * 		| time >= 0
	 * @post this.movementDuration = time
	 */
	@Raw
	private void setMovementDuration(double time) {
		assert time >= 0;
		this.movementDuration = time;
	}	
	private double timeSinceMove = 0;
	/**
	 * returns the time in seconds since the last movement
	 * @return the time since the last movement
	 * 			| timeSinceMove
	 */
	@Basic @Raw
	private double getTimeSinceMove() {
		return timeSinceMove;
	}
	/**
	 * Sets timeSinceEndMove to a new value
	 * @param timeSinceMove the timeSinceMove to set
	 * @pre  time should always be bigger than or equal to zero
	 * 		| time >= 0
	 * @post this.movementDuration = time
	 */
	@Raw
	private void setTimeSinceMove(double time) {
		assert time >= 0;
		this.timeSinceMove = time;
	}
	/**
	 * an int indicating the amount of times this Shark has jumped
	 */
	private int jumpCounter = 0;
	/**
	 * returns the int giving the amount of jumps of this Shark
	 * @return the amount of jumps
	 * 			| jumpCounter
	 */
	@Basic @Raw
	private int getJumpCounter() {
		return this.jumpCounter;
	}
	/**
	 * Sets jumpCounter to a new value
	 * @param count
	 * 			new value to set
	 * @pre count should always be bigger than or equal to zero
	 * 		| count >= 0
	 */
	@Raw
	private void setJumpCounter(int count) {
		assert count >= 0;
		this.jumpCounter = count;		
	}
	/**
	 * a boolean indicating if the Shark is moving at the moment
	 */
	private boolean moving = true;
	/**
	 * returns true when the Shark is moving
	 * @return if the shark is moving
	 * 			| moving
	 */
	@Basic
	private boolean isMoving() {
		return this.moving;
	}
	/**
	 * sets the boolean moving to a given boolean move
	 * @param move
	 * 			the new value of moving
	 * @post moving is equal to the given boolean
	 * 			| moving = move
	 */
	@Raw
	private void setMoving(boolean move) {
		this.moving = move;
	}
	
//	Validations
	
	/**
	 * returns true if the given sprites are valid
	 * @return true if the length is equal to 2
	 * 			| sprites.length == 2
	 */
	@Override @Raw
	protected boolean isValidSprite(Sprite[] sprites) {
		return sprites.length == 2;
	}	
	
	/**
	 * checks the surroundings of the shark and adapts the position, speed and acceleration
	 * @param newXPos
	 * 			the newly calculated horizontal position
	 * @param newYPos
	 * 			the newly calculated vertical position
	 * @effect if the slime touches a wall, it's movement is stopped and his position is adapted
	 * 			| if (againstLeftWall(newXPos,newYPos) && getOrientation() == Orientation.LEFT)
	 * 			| 	then newXPos = (getTilesLeft(newXPos,newYPos)[0][0] + 1) * world.getTileLength()
	 * 			| 		 stopMoving()
	 * 			| if (againstRightWall(newXPos,newYPos) && this.getOrientation() == Orientation.RIGHT) 
	 * 			| 	then newXPos = (getTilesRight(newXPos,newYPos)[0][0]) * world.getTileLength() - getSize()[0]
	 * 			| 		 stopMoving()
	 * @effect if the slime touches a roof, its vertical speed is set to zero and its vertical position is adapted
	 * 			| if (isAgainstRoof(newXPos,newYPos)) 
	 * 			| 	then newYPos = (getTilesAbove(newXPos,newYPos)[0][1]) * world.getTileLength() - getSize()[1] - 1
	 * 			| 		 setYSpeed(0)
	 * @effect if the slime is moving and not fully submerged in water, the horizontal movement is stopped
	 * 			and the vertical position is adapted
	 * 			| if (isMoving() && isInWater() && ( ! isFullyInFeature(newXPos, newYPos, 2)))
	 * 			| 	then this.setYSpeed(0)
	 * 			| 		 setYAcc(0)
	 * 			| 		 newYPos = (getTilesAbove(newXPos,newYPos)[0][1]) * world.getTileLength() - getSize()[1] - 1
	 * @effect if a falling slime touches a floor, its fall is ended and the vertical position is adapted
	 * 			| if (isFalling() && onFloor(newXPos,newYPos))
	 * 			| 	then newYPos = ((getTilesUnder(newXPos,newYPos)[0][1] +1) * world.getTileLength() - 1)
	 * 			| 		 endFall()
	 * @effect if a falling slime is fully submerged in water, it's fall is ended
	 * 			| if (this.isFalling() && this.isFullyInFeature(newXPos, newYPos, 2)) 
	 * 			| 	then endFall()
	 * @effect if a slime touches a floor, its vertical position is adapted and vertical acceleration and speed
	 * 			are set to zero if the vertical acceleration is still negative
	 * 			| if (onFloor(newXPos,newYPos))
	 * 			| 	then newYPos = ((getTilesUnder(newXPos,newYPos)[0][1] + 1) * world.getTileLength() - 1)
	 * 			| 		 if (this.getYAcc() < 0 )
	 * 			| 			then setYAcc(0)
	 * 			| 				 setYSpeed(0)
	 *
	 * @return {newXPos, newYPos}
	 */
	@Raw
	private double[] checkSurroundings(double newXPos, double newYPos) {
		if (againstLeftWall(newXPos,newYPos) && this.getOrientation() == Orientation.LEFT) {
			newXPos = (this.getTilesLeft(newXPos,newYPos)[0][0] + 1) * this.getWorld().getTileLength();
			this.stopMoving();
		}
		if (againstRightWall(newXPos,newYPos) && this.getOrientation() == Orientation.RIGHT) {
			newXPos = (this.getTilesRight(newXPos,newYPos)[0][0]) * this.getWorld().getTileLength() - this.getSize()[0];
			this.stopMoving();
		}
		if (isAgainstRoof(newXPos,newYPos)) {
			newYPos = (this.getTilesAbove(newXPos,newYPos)[0][1]) * this.getWorld().getTileLength() - this.getSize()[1] - 1;
			this.setYSpeed(0);
		}
		if (this.isMoving() && this.isInWater() && ( ! this.isFullyInFeature(newXPos, newYPos, 2))) {
			this.setYSpeed(0);
			this.setYAcc(0);
			newYPos = (this.getTilesAbove(newXPos,newYPos)[0][1]) * this.getWorld().getTileLength() - this.getSize()[1] - 1;
		}
		if (this.isFalling() && this.onFloor(newXPos,newYPos)) {
			newYPos = ((this.getTilesUnder(newXPos,newYPos)[0][1] + 1) * this.getWorld().getTileLength() - 1);
			this.endFall();
		}
		if (this.isFalling() && this.isFullyInFeature(newXPos, newYPos, 2)) {
			this.endFall();
		}
		if (this.onFloor(newXPos,newYPos)) {
			newYPos = ((this.getTilesUnder(newXPos,newYPos)[0][1] + 1) * this.getWorld().getTileLength() - 1);
			if (this.getYAcc() < 0 ) {
				this.setYAcc(0);
				this.setYSpeed(0);
			}
		}			

		return new double[] {newXPos, newYPos};
	}
	
	/**
	 * lets this Shark lose hitpoints when in air or magma
	 * @param dt
	 * 			a small time interval
	 * @effect when making contact with air, hitpoints are deducted
	 * 			| if (this.isInContactWithFeature(getXPos(), getYPos(), 0))
	 * 			| 	then if (isInWater()) 
	 * 			| 			then setNotInWater()
	 * 			| 				 setTimeInAir(dt)
	 * 			| else 
	 * 			| 	then setTimeInAir(getTimeInAir() + dt)
	 * 			| if (getTimeInAir() >= Shark.getTimeUntilChoke())
	 * 			| 	then setHitpoints(getHitpoints() - Shark.getLossHitpointsInAir())
	 * 			| 		 setTimeInAir(0)
	 * @effect when making contact with magma, hitpoints are deducted
	 * 			| if (this.isInContactWithFeature(this.getXPos(), this.getYPos(), 3))
	 * 			| 	then double toLose = (GameObject.getLossHitpointsInMagma() * (dt / GameObject.getBurnTime())) 
	 * 			| 							+ this.getHitpointsDifference()
	 * 			| 		 setHitpointsDifference(toLose - (int) toLose)
	 * 			| 		 loseHitpoints( (int) toLose)
	 */
	@Raw
	private void loseHitpointsBecauseOfFeature(double dt) {
		if (this.isInContactWithFeature(this.getXPos(), this.getYPos(), 0)) {
			if (this.isInWater()) {
				this.setNotInWater();
				this.setTimeInAir(dt);
			}
			else {
				this.setTimeInAir(this.getTimeInAir() + dt);
			}
			if (this.getTimeInAir() >= Shark.getTimeUntilChoke()) {
				this.setHitpoints(this.getHitpoints() - Shark.getLossHitpointsInAir());
				this.setTimeInAir(0);
			}
		}
		if (this.isInContactWithFeature(this.getXPos(), this.getYPos(), 3)) {
			double toLose = (GameObject.getLossHitpointsInMagma() * (dt / GameObject.getBurnTime())) 
								+ this.getHitpointsDifference();
			this.setHitpointsDifference(toLose - (int) toLose);
			this.loseHitpoints( (int) toLose);
		}
	}
	
	/**
	 * checks if this Shark is within the boundaries of the game world, if not
	 * the Shark is removed
	 * @param newXPos
	 * 			the new horizontal position of the Shark
	 * @param newYPos
	 * 			the new vertical position of the Shark
	 * @effect the Shark gets removed if not within the boundaries
	 * 			| if ( ! isWithinBoundaries(newXPos, newYPos))
	 * 			| 	then remove()
	 */
	@Raw
	private void checkIfWithinBoundaries(double newXPos, double newYPos) {
		if ( ! isWithinBoundaries(newXPos,newYPos)) {
			this.remove();
		}
	}
	
	/**
	 * advances the time with dt seconds whilt the Shark is still alive
	 * @param dt
	 * 			a small time interval
	 * @effect the Shark starts a random movement
	 * 			| randomMovement(dt)
	 * @effect the local variables newCalculatedPos, newXPos and nexYPos are initialized, then 
	 * 			checked if the newly calculated positions are within the boundaries of the game 
	 * 			world, if this is not the case, the Shark is removed from the world, then 
	 * 			new positions, speed and acceleration of the Shark is adapted to the surrounding
	 * 			world, then the position, speed, acceleration and hitpoints are adapted in case of
	 * 			a collision with other gameObject and then the new positions are used to set the
	 * 			position of the shark
	 * 			| let 
	 * 			| 	double[] newPos =  this.calculateNewPos(dt);
	 * 			| in
	 * 			| 	checkIfWithinBoundaries(newPos[0], newPos[1])
	 * 			| newPos = checkSurroundings(newPos[0], newPos[1])
	 * 			| newPos = colliding(newPos[0],newPos[1], dt)
	 * 			| setXPos(newPos[0])
	 * 			| setYPos(newPos[1])
	 * @effect the speed of the Shark are set to the calculated values
	 * 			| setNewSpeed(dt)
	 * @effect hitpoints are lost if the Shark is in touch with air or magma
	 * 			and the immunity is updated
	 * 			| loseHitpointsBecauseOfFeature()
	 * 			| updateImmunity()
	 * @effect the boolean inWater is updated to the correct value and the shark dies
	 * 			if it hasn't got any hitpoints left
	 * 			| if ((this.isFullyInFeature(newPos[0], newPos[1], 2)) && ( ! this.isInWater()))
	 * 			| 	then setInWater()
	 * 			| if (this.getHitpoints() <= 0)
	 * 			| 	then die()
	 */
	@Raw
	private void advanceTimeWhileLiving(double dt) {
		if (this.getProgram() == null) {
			this.randomMovement(dt);
		}
		double[] newPos =  this.calculateNewPos(dt);
		
		this.checkIfWithinBoundaries(newPos[0], newPos[1]);
		
		newPos = checkSurroundings(newPos[0], newPos[1]);
		newPos = colliding(newPos[0],newPos[1], dt);
		this.setXPos(newPos[0]);
		this.setYPos(newPos[1]);
		
		this.setNewSpeed(dt);
		
		this.loseHitpointsBecauseOfFeature(dt);
		this.updateImmunity(dt);
		
		if ((this.isFullyInFeature(newPos[0], newPos[1], 2)) && ( ! this.isInWater())) {
			this.setInWater();			
		}
		if (this.getHitpoints() <= 0) {
			this.die();
		}
		if ((this.isJumping()) && (this.getYSpeed() < 0)) {
			this.setNotJumping();
		}
		
	}
	
	/**
	 * advances the time with dt seconds if the Shark is dying
	 * @param dt
	 * 			 a small time interval
	 * @effect the double timeSinceDeath is updated and if this double is greater than
	 * 			the time until the Shark gets removed from the world, the shark gets removed
	 * 			| setTimeSinceDeath(this.getTimeSinceDeath() + dt)
	 * 			| if (this.getTimeSinceDeath() >= GameObject.getTimeUntilRemove())
	 * 			| 	then remove()
	 */
	@Raw
	private void advanceTimeWhileDeath(double dt) {
		setTimeSinceDeath(this.getTimeSinceDeath() + dt);
		if (this.getTimeSinceDeath() >= GameObject.getTimeUntilRemove()) {
			this.remove();
		}
	}
	
	/**
	 * advances the time with dt seconds
	 * @param dt
	 * 			 a small time interval
	 * @throws IllegalDtException
	 * 			the given dt is not valid
	 * 			| ! isValidDt()
	 * @effect the time is advanced with dt seconds when the shark is living or while he's dying
	 * 			| if ( ! this.isDying()) 
	 * 			| 	then advanceTimeWhileLiving(dt)
	 * 			| else 
	 * 			| 	then advanceTimeWhileDeath(dt)
	 */
	@Raw
	public void advanceTime(double dt) throws IllegalDtException {
		if ( ! isValidDt(dt))
			throw new IllegalDtException(dt);		
		if ( ! this.isDying()) { 
			this.advanceTimeWhileLiving(dt);
		}		
		else {
			this.advanceTimeWhileDeath(dt);
		}
	}	
	
	/**
	 * removes this Shark from the game world
	 * @effect the Shark is removed from the world he lives in
	 * 			and the world of this Shark is set to null
	 * 			| this.getWorld().removeShark(this)
	 * 			| this.setWorld(null)
	 */
	private void remove() {
		this.getWorld().removeShark(this);
		this.setWorld(null);
	}
	
	/**
	 * Shark starts moving to the right
	 * @effect the horizontal acceleration and orientation are set
	 * 			| setXAcc(Shark.getMoveAcc())
	 * 			| setOrientationRight()
	 * @effect if this Shark is in the water, he's vertical acceleration
	 * 			is set to a random value
	 * 			| if (isInWater())
	 * 			| 	then setYAcc(Math.random() * 4 - 2)
	 */
	public void startMoveRight(){
		this.setXAcc(Shark.getMoveAcc());
		this.setOrientationRight();
		if (isInWater()) {
			this.setYAcc(Math.random() * 4 - 2);
		}
	}
	
	/**
	 * Shark starts moving to the left
	 * @effect the horizontal acceleration and orientation are set
	 * 			| setXAcc(Shark.getMoveAcc())
	 * 			| setOrientationLeft()
	 * @effect if this Shark is in the water, he's vertical acceleration
	 * 			is set to a random value
	 * 			| if (isInWater())
	 * 			| 	then setYAcc(Math.random() * 4 - 2)
	 */
	public void startMoveLeft(){
		this.setXAcc(Shark.getMoveAcc());
		this.setOrientationLeft();
		if (isInWater()) {
			this.setYAcc(Math.random() * 4 -  2);
		}
	}
	
	/**
	 * sets the vertical acceleration and velocity
	 * @effect the shark starts it's vertical jump
	 * 			| setXacc(Shark.getMoveAcc())
	 * 			| setYSpeed(Shark.getJumpSpeed())
	 */
	public void startJump() {
		this.setXAcc(Shark.getMoveAcc());
		this.setYSpeed(Shark.getJumpSpeed());
		this.setJumping();
	}
	
	/**
	 * Shark starts moving and jumping to the right
	 * @effect the horizontal acceleration, vertical speed and orientation are set
	 * 			| startJump()
	 * 			| setOrientationRight()
	 */
	private void jumpRight() {
		this.startJump();
		this.setOrientationRight();
	}
	
	/**
	 * Shark starts moving and jumping to the left
	 * @effect the horizontal acceleration, vertical speed and orientation are set
	 * 			| startJump()
	 * 			| setOrientationLeft()
	 */
	private void jumpLeft() {
		this.startJump();
		this.setOrientationLeft();
	}
	
	/**
	 * the Shark stops moving
	 * @effect the horizontal speed is set to zero and the vertical acceleration is 
	 * 			set to zero if the Shark isn't falling
	 * 			| setXSpeed(0)
	 * 			| if ( ! isFalling())
	 * 			| 	then setYAcc(0)
	 */
	private void stopMove() {
		this.setXSpeed(0);
		if ( ! isFalling()) {
			this.setYAcc(0);
		}
	}
	
	/**
	 * starts a random movement for the Shark
	 * @param dt
	 * 			a small time interval
	 * @effect the Shark moves randomly and changes the kind of movement every period
	 * 			| let 
	 * 			|	double randoNb
	 * 			| in
	 * 			| if (getTimeSinceMove() >= getMovementDuration())
	 * 			| 	then stopMove()
	 * 			| 		 setMovementDuration(Math.random() * 3 + 1)
	 * 			| 		 setTimeSinceMove(dt)
	 * 			| if ((getJumpCounter() >= 4) && (onFloor(getXPos(), getYPos()) || isInWater()))
	 * 			| 	then randomNb = Math.random() * 4
	 * 			| else
	 * 			| 	then randomNb = Math.random() * 2
	 * 			| if (randomNb < 1) 
	 * 			| 	then moveRight()
	 * 			| 	setJumpCounter(getJumpCounter() + 1)
	 * 			| 	setMoving(true)
	 * 			| else if (randomNb < 2)
	 * 			| 	then moveLeft()
	 * 			| 		 setJumpCounter(getJumpCounter() + 1)
	 * 			| 		 setMoving(true)
	 * 			| else if (randomNb < 3)
	 * 			| 	then jumpRight()
	 * 			| 		 setJumpCounter(0)
	 * 			| 		 setMoving(false)
	 * 			| else 
	 * 			|	then jumpLeft()
	 * 			| 		 setJumpCounter(0)
	 * 			|		 setMoving(false)
	 * 			| else
	 * 			| 	then setTimeSinceMove(this.getTimeSinceMove() + dt)
	 */
	@Raw
	private void randomMovement(double dt) {
		if (this.getTimeSinceMove() >= this.getMovementDuration()) {
			this.stopMove();
			this.setMovementDuration(Math.random() * 3 + 1);
			this.setTimeSinceMove(dt);
			double randomNb;
			if ((this.getJumpCounter() >= 4) && (this.onFloor(this.getXPos(), this.getYPos()) || this.isInWater())) {
				randomNb = Math.random() * 4;
			}
			else {
				randomNb = Math.random() * 2;
			}
			if (randomNb < 1) {
				this.startMoveRight();
				this.setJumpCounter(this.getJumpCounter() + 1);
				this.setMoving(true);
			}
			else if (randomNb < 2) {
				this.startMoveLeft();
				this.setJumpCounter(this.getJumpCounter() + 1);
				this.setMoving(true);
			}
			else if (randomNb < 3) {
				this.jumpRight();
				this.setJumpCounter(0);
				this.setMoving(false);
			}
			else {
				this.jumpLeft();
				this.setJumpCounter(0);
				this.setMoving(false);
			}			
		}
		else {
			this.setTimeSinceMove(this.getTimeSinceMove() + dt);
		}
	}
	
	/**
	 * 
	 * @param newXPos
	 * 			the new calculated horizontal position
	 * @param newYPos
	 * 			the new calculated vertical position
	 * @param dt
	 * 			a small time interval over which the time must be advanced
	 * @effect 
	 * 			| let 
	 * 			| 	List<GameObject> allSlimesSharksMazub =  new ArrayList<GameObject>(this.getWorld().getSlimes())
	 * 			| 	boolean onGameObject = false
	 * 			|	double[] newPos = {newXPos, newYPos}
	 * 			| 	double xDim1 = this.getXDim()
	 * 			|	double yDim1 = this.getYDim()
	 * 			| 	double x2 = other.getXPos()
	 * 			| 	double xDim2 = other.getXDim()
	 * 			| 	double y2 = other.getYPos()
	 * 			|	double yDim2 = other.getYDim()
	 * 			| in
	 * 			| allSlimesSharksMazub.addAll(world.getSharks())
	 * 			| allSlimesSharksMazub.add(world.getAlien())
	 * 			| for each GameObject other: allSlimesSharksMazub :
	 * 			| 	if (this != other)
	 * 			| 		then newPos = collidesSomewhere(newPos[0], xDim1, newPos[1], yDim1, x2, xDim2, y2,yDim2)
	 * 			| 	if (newPos[3] == 1) 
	 * 			| 		then onGameObject = true
	 * 			| 	if ( newPos[2] == 1 && ( ! other.isDying()) && !(other instanceof Shark)) 
	 * 			| 		this.contactDamage(dt)
	 * 			|		if (! (this.collidesAbove(newPos[0], xDim1, newPos[1], yDim1, x2, xDim2, y2, yDim2)
	 * 			|				&& other instanceof Mazub)) 
	 * 			|			then other.contactDamage(dt)
	 * 			| if (this.isFalling() && onGameObject)
	 * 			| 	then this.endFall()
	 * 			| else if ( ! this.isFalling() && ( ! onGameObject) && ( ! this.isFullyInFeature(newPos[0], newPos[1], 2))
	 * 			|			&& ( ! this.onFloor(newPos[0],newPos[1])))
	 * 			| 	then fall()
	 * @return {newPos[0], newPos[1]}
	 */
	//TODO commentaar en mss checker
	@Raw
	public double[] colliding(double newXPos, double newYPos, double dt) {	
		List<GameObject> allSlimesSharksMazubBuzam =  new ArrayList<GameObject>(this.getWorld().getSlimes());
		allSlimesSharksMazubBuzam.addAll(this.getWorld().getSharks());
		allSlimesSharksMazubBuzam.add(this.getWorld().getAlien());
		allSlimesSharksMazubBuzam.add(this.getWorld().getBuzam());
		boolean onGameObject = false;
		double[] newPos = {newXPos, newYPos};
		for (GameObject other: allSlimesSharksMazubBuzam) {
			if (this != other) {
				double xDim1 = this.getXDim();
				double yDim1 = this.getYDim();
				double x2 = other.getXPos();
				double xDim2 = other.getXDim();
				double y2 = other.getYPos();
				double yDim2 = other.getYDim();
				newPos = collidesSomewhere(newPos[0], xDim1, newPos[1], yDim1, x2, xDim2, y2, yDim2);
				if (newPos[3] == 1) {
					onGameObject = true;
				}
				if ( newPos[2] == 1 && ( ! other.isDying()) && !(other instanceof Shark))  {
					this.contactDamage(dt);
					if (! (this.collidesAbove(newPos[0], xDim1, newPos[1], yDim1, x2, xDim2, y2, yDim2)
							&& other instanceof Mazub)) { 
					other.contactDamage(dt);
					}
				}		
			}
		}
		if (this.isFalling() && onGameObject) {
			this.endFall();
		}
		else if ( ! this.isFalling() && ( ! onGameObject) && ( ! this.isFullyInFeature(newPos[0], newPos[1], 2))
				&& ( ! this.onFloor(newPos[0],newPos[1]))) {
			fall();
		}
		return new double[] {newPos[0],newPos[1]};
	}
		
}
