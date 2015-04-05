package jumpingalien.model;
import java.util.Arrays;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.util.Sprite;


/**
 * First part of the jumping alien project for OGP
 * made by: Ward Romanus (2e bach ELT-CW) and Pieter Van den Berghe (2e bach ELT-CW)
 * github: https://github.com/pietervdb/mario
 */

/**
 * A class that describes the movement of the rectangular player
 * character Mazub in a game world. 
 * 
 * @invar The lowest left corner of mazub (x_pos,y_pos) has to stay
 * 			in the game world.
 * 		| isValidPosition(getXPos(), getYPos())
 * @invar The speed has to be limited to a maximum speed
 * 		| isValidSpeed(getXSpeed(), getYSpeed())
 * @author Pieter Van den Berghe, Ward Romanus
 */
public class Mazub extends GameObject {

	/**
	 * Initialize this new Mazub with given x and y positions and given sprites.
	 * 
	 * @param xPos
	 * The x position in the field for the new Mazub
	 * @param yPos
	 * The y position in the field for the new Mazub
	 * @param sprites
	 * The sprites for the new Mazub
	 * @effect the mazub is created at (x_pos,y_pos)
	 * 			| new.getXPos() == x_pos
	 * 			| new.getYPos() == y_pos
	 * 			| new.getSprite() == sprites
	 * 			| new.getInitStartSpeed() == START_SPEED
	 * 			| new.getMaxSpeed() == MAX_SPEED
	 * 			| new.getNumberOfMazubs += 1
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for Mazub
	 * 			| !isValidPosition(x_pos,y_pos)
	 * @throws IllegalSpriteException
	 * 			The given sprite is not valid
	 * 			| !isValidSprite(sprites) 
	 * 
	 */
	@Raw
	public Mazub(int xPos, int yPos, Sprite[] sprites)
		throws IllegalPositionException, IllegalSpriteException {
		super(xPos,yPos,sprites);
		this.setInitStartSpeed(START_SPEED);
		this.setMaxSpeed(MAX_MOVING_SPEED);
		
	}
	

	/**
	 * Initialize this new Mazub with given x and y positions, sprites,
	 * 
	 * < horizontal start speed and maximum horizontal speed.
	 * 
	 * @param xPos
	 * The x position in the field for the new Mazub
	 * @param yPos
	 * The y position in the field for the new Mazub
	 * @param sprites
	 * The sprites for the new Mazub
	 * @param initStartSpeed
	 * The initial horizontal start speed
	 * @param maxSpeed
	 * The maximum horizontal speed 
	 * @effect the mazub is created at (x_pos,y_pos)
	 * 			| new.getXPos() == x_pos
	 * 			| new.getYPos() == y_pos
	 * 			| new.getSprite() == sprites
	 * 			| new.getInitStartSpeed() == initStartSpeed
	 * 			| new.getMaxSpeed() == maxSpeed
	 * 			| new.getNumberOfMazubs += 1
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for Mazub
	 * 			| !isValidPosition(x_pos,y_pos)
	 * @throws IllegalSpriteException
	 * 			The given sprite is not valid
	 * 			| !isValidSprite(sprites) 
	 * @throws IllegalSpeedException
	 * 			The given maxSpeed and initalStartSpeed are not valid
	 * 			| ! isValidSpeed()
	 * The initial velocity will never be changed below 1 m/s so
	 * we don't need an IllegalInitStartSpeedException or a
	 * IllegalMaxSpeedException. 
	 * 
	 */	
	@Raw
	public Mazub(int xPos, int yPos, Sprite[] sprites,
			int initStartSpeed,int maxSpeed)
		throws IllegalPositionException,IllegalSpriteException
			, IllegalSpeedException {
			super(xPos, yPos, sprites);
			this.setInitStartSpeed(initStartSpeed);
			this.setMaxSpeed(maxSpeed);
	}
	
	
	/**
	 * the starting speed when startMove is initiated
	 */
	private int initStartSpeed;
	/**
	 * a boolean saying if the mazub is ducked
	 */
	private boolean duck = false;
	/**
	 * the static int giving the starting speed of mazub
	 * when startMove() is initiated
	 */
	private static int START_SPEED = 1;
	/**
	 * the maximal horizontal speed mazub can reach
	 */
	private static int MAX_MOVING_SPEED = 3;
	/**
	 * the maximal horizontal speed mazub can reach
	 * when mazub is ducked
	 */
	private static int MAX_SPEED_DUCK = 1;
	/**
	 * the vertical speed at which mazub moves when 
	 * startJump() is initiated
	 */
	private static int JUMP_SPEED = 8;
	/**
	 * the vertical acceleration at which mazub falls
	 */
	private static double FALL_ACC = -10;
	/**
	 * the acceleration when mazub starts moving horizontally
	 */
	private static double MOVE_ACC = 0.9;
	/**
	 * the time passed after endMove was invoced for the
	 * last time
	 */
	private double timeSinceEndMove;
	/**
	 * the time passed after startMove was invoced for the
	 * last time
	 */
	private double timeSinceStartMove;
	/**
	 * the time that mazub should not move before sprite 0 is displayed
	 */
	private static double NOT_MOVING_TIME = 1;
	/**
	 * the time every image is displayed when mazub is running
	 */
	private static double TIME_DIFFERENT_SPRITE = 0.075;
	/**
	 * the amount of running sprites with a certain orientation
	 */
	private int nbRunningSprites;
	/**
	 * the counter to go over the array of possible sprites
	 */
	private int counterSprites;	

	
	private boolean immune = false;
	private void setImmune() {
		this.immune = true;
	}
	private void setNotImmune() {
		this.immune = false;
	}
	
//GETTERS
	
	
	/**
	 * Returns the initial start speed
	 * @return the initial start speed
	 * 			| initStartSpeed
	 */
	@Basic @Immutable @Raw 
	private int getInitStartSpeed() {
		return initStartSpeed;
	}

	/**
	 * Returns the number of available sprites for running
	 * @return the nbRunningSprites
	 * 			| nbRunningSprites
	 */
	private int getNbRunningSprites() {
		return nbRunningSprites;
	}
	/**
	 * Returns the counter that goes over the available sprites for running
	 * @return the counterSprites
	 * 			| counterSprites
	 */
	private int getCounterSprites() {
		return counterSprites;
	}
	/**
	 * Returns the time since Mazub stopped moving horizontally
	 * @return the time_since_endMove
	 * 			| time_since_endMove
	 */
	@Raw 
	private double getTimeSinceEndMove() {
		return timeSinceEndMove;
	}
	/**
	 * Returns the time since Mazub started moving
	 * @return the time_since_startMove
	 * 			| time_since_startMove
	 */
	@Raw 
	private double getTimeSinceStartMove() {
		return timeSinceStartMove;
	}
	/**
	 * Returns the jump speed of Mazub
	 * @return JUMP_SPEED
	 */
	@Basic @Immutable @Raw 
	private int getStartJumpSpeed() {
		return JUMP_SPEED;
	}
	/**
	 * Returns the maximum horizontal speed of mazub
	 * @return MAX_SPEED
	 */
	@Basic @Immutable @Raw 
	private int getMaxMovingSpeed() {
		return MAX_MOVING_SPEED;
	}
	/**
	 * Returns the maximum speed as Mazub ducks
	 * @return MAX_SPEED_DUCK
	 */
	@Basic @Immutable @Raw 
	private int getMaxSpeedDuck() {
		return MAX_SPEED_DUCK;
	}
	/**
	 * Returns the starting speed for running
	 * @return START_SPEED
	 */
	@Basic @Immutable @Raw 
	private int getStartSpeed() {
		return START_SPEED;
	}
	/**
	 * returns the acceleration when mazub starts moving
	 * @return MOVE_ACC
	 */
	@Basic @Immutable @Raw
	private double getMoveAcc() {
		return MOVE_ACC;
	}
	/**
	 * Returns the acceleration when Mazub falls
	 * @return FALL_ACC
	 */
	@Basic @Immutable @Raw 
	private double getFallAcc() {
		return FALL_ACC;
	}
	/**
	 * Returns the time between endMove and an change of sprites
	 * @return NOT_MOVING_TIME
	 */
	@Basic @Immutable @Raw 
	private double getNotMovingTime() {
		return NOT_MOVING_TIME;
	}
	/**
	 * returns the time until the next sprite
	 * @return TIME_DIFFERENT_SPRITE
	 */
	@Basic @Immutable @Raw 
	private static double getTimeDifferentSprite() {
		return TIME_DIFFERENT_SPRITE;
	}
	/**
	 * Returns the boolean duck, if mazub is ducked, true is returned,
	 * otherwise false
	 * @return (duck)
	 *     __
        .'  `.
        |a_a  |
        \<_)__/    ,
         /   `-...-'\
        |    -~',   /
     ~`~\   '.-`  .' ~~^-~
     ~~` `-.~-..~` ~~`  ~`
     '~~ ~~`-  ^-~~`~ ^
	 */
	private boolean isDucked(){
		return (this.duck);
	}	
	
	
//Setters	
	/**
	 * Sets the initial starting speed to a new value
	 * @param initstartspeed
	 * 			The new speed by initialization
	 */
	@Raw 
	private void setInitStartSpeed(int initstartspeed) {
		this.initStartSpeed = initstartspeed;
	}
	/**
	 * Sets the boolean duck to a new value duck
	 * @param duck
	 * 			The new boolean for duck
	 */
	@Raw 
	private void setDuck(boolean duck) {
		this.duck = duck;
	}
	/**
	 * Sets the number of available running sprites to a new value
	 * @param nbRunningSprites
	 * 			the nuw value for nbRunningSprites
	 */
	private void setNbRunningSprites(int nbRunningSprites) {
		this.nbRunningSprites = nbRunningSprites;
	}
	/**
	 * Sets the counter of the running sprites to a new value
	 * @param counterSprites
	 * 			the new value for counterSprites
	 */
	private void setCounterSprites(int counterSprites) {
		this.counterSprites = counterSprites;
	}
	/**
	 * Sets the time since endMove to a new value
	 * @param time_since_endMove 
	 * 			the new time_since_endMove
	 */
	@Raw 
	private void setTimeSinceEndMove(double time_since_endMove) {
		this.timeSinceEndMove = time_since_endMove;
	}
	/**
	 * Sets the time since startMove to a new value
	 * @param time_since_startMove 
	 * 			the new time_since_startMove
	 */
	@Raw 
	private void setTimeSinceStartMove(double time_since_startMove) {
		this.timeSinceStartMove = time_since_startMove;
	}
	
//VALIDATIONS	
	
	/**
	 * Checks whether the given initstartspeed and maxspeed are valid
	 * for any Mazub
	 * @return True if the given initstartspeed and maxspeed are valid
	 * 			| ((this.getInitStartSpeed() >= 1) && (this.getMaxSpeed() 
	 * 			|	>= this.getInitStartSpeed()))
	 */
	private boolean isValidSpeed(int initStartSpeed,int maxSpeed) {
		return ((initStartSpeed >= 1) && (maxSpeed >= initStartSpeed));
	}
	/**
	 * Checks whether the current horizontal speed is valid
	 * @return True if the current horizontal speed is valid
	 * 			| ((this.getXSpeed() >= 0) && (this.getXSpeed() <= this.getMaxSpeed()))
	 */
	private boolean isValidXSpeed() {
		return ((this.getXSpeed() >= 0) && (this.getXSpeed() <= this.getMaxSpeed()));
	}
	/**
	 * Checks whether the current vertical speed is valid
	 * for any Mazub
	 * @return True if the current vertical speed isn't equal to NaN 
	 */
	public boolean isValidYSpeed(double ySpeed) {
		return ( ! Double.isNaN(ySpeed));
	}
	
	/**
	 * Checks whether the given sprites are valid for any Mazub
	 * @param sprites
	 * @return True if the sprites are valid
	 * 			| ((sprites.length >= 8) && (sprites.length % 2 == 0))
	 */
	@Override
	protected boolean isValidSprite(Sprite[] sprites) {
		return ((sprites.length >= 8) && (sprites.length % 2 == 0));
	}


	/**
	 * Method to make the Mazub start moving
	 * 
	 * @pre the position of Mazub must be valid
	 * 			| isValidPosition()
	 * @pre the initial startspeed and maxspeed of Mazub must be valid
	 * 			| isValidSpeed()
	 * @effect Mazub starts moving with a certain speed and acceleration
	 * 			| if ( ! isDucked())
	 * 			| 	then acc == 0.9
	 * 			|		 maxSpeed == getMaxSpeed()
	 * 			| else
	 * 			| 	acc == 0
	 * 			|	maxSpeed == MAX_SPEED_DUCK
	 * @effect time_since_startMove is set to zero
	 * 			| time_since_startMove == 0
	 */
	@Raw
	private void startMove() {
		assert this.isValidPosition(getXPos(), getYPos());
		assert this.isValidSpeed(this.getInitStartSpeed(), this.getMaxSpeed());
		this.setXSpeed(this.getInitStartSpeed());
		if (this.isDucked() == false) {
			this.setXSpeed(this.getInitStartSpeed());
			this.setXAcc(this.getMoveAcc());
			this.setMaxSpeed(this.getMaxSpeed());
		}
		else {
			this.setXSpeed(this.getMaxSpeedDuck());
			this.setXAcc(0);
			this.setMaxSpeed(this.getMaxSpeedDuck());
		}
		this.setTimeSinceStartMove(0);
	}	
	/**
	 * Mazub starts moving to the right
	 * @effect 	| startMove()
	 * 			| orientation == right
	 */
	public void startMoveRight(){
		this.setOrientationRight();
		this.startMove();
	}
	/**
	 * Mazub starts moving to the left
	 * @effect 	| startMove()
	 * 			| orientation == left
	 */
	public void startMoveLeft(){
		this.setOrientationLeft();
		this.startMove();
	}	
	/**
	 * Method to end the move of a Mazub
	 * @pre The horizontal speed of Mazub must be valid
	 * 			| isValidXSpeed()
	 * @pre The position of Mazub must be valid
	 * 			| isValidPosition()
	 * @effect Mazub does not move
	 * 			| speed == 0;
	 * 			| acc == 0;
	 * @effect time_until_endMove is set to zero
	 * 			| time_until_endMove == 0
	 */
	@Raw
	private void endMove() {
		assert (this.isValidXSpeed());
		assert (this.isValidPosition(this.getXPos(),this.getYPos()));
		this.setXSpeed(0);
		this.setXAcc(0);
		this.setTimeSinceEndMove(0);
	}	
	/**
	 * Ends the mazub's movement to the left
	 */
	public void endMoveLeft() {
		this.endMove();
	}	
	/**
	 * ends the mazub's movement to the right
	 */
	public void endMoveRight() {
		this.endMove();
	}	
	/**
	 * Makes Mazub start his jump, when he is standing
	 * on the ground, by setting his vertical speed
	 * @effect ySpeed == JUMP_SPEED
	 * 			| if (this.getYPos() == 0)
	 * 			| 	then this.setySpeed(this.getJUMPSPEED())
	 */
	public void startJump() {
		if (this.onFloor(this.getXPos(),this.getYPos())) {
			this.setYSpeed(this.getStartJumpSpeed());	
		}
	}	
	/**
	 * Ends Mazub's jump if he is still jumping
	 * @effect Sets the Yspeed to zero if it is positive
	 * 			| if ySpeed > 0
	 * 			|	then setYSpeed(0)
	 */
	public void endJump() {
		if (this.getYSpeed()> 0) {
			this.setYSpeed(0);
		}
	}

	
	/**
	 * the alien moves horizontally if the  new speed does not exceed the maximum speed
	 * and if the alien doesn't walk into a wall
	 * @effect if the current speeds exceeds the maximum speed, xSpeed is set to
	 * 			maxspeed and the acceleration is set to zero
	 * 			| if xSpeed >= maxSpeed
	 * 			| 	then sSpeed == maxSpeed
	 * 			| 		 xAcc == 0
	 * @effect if mazub is oriented right, his horizontal new position changes to the right,
	 * 		   if mazub is oriented left, his horizontal new position changes to the left
	 * 			| if orientation == right
	 * 			| 	then new_x_pos == this.getXPos() + this.getXSpeed()*100*dt
	 * 			| 					+ 0.5 * this.getXAcc() * 100 * Math.pow(dt,2) + this.getXDifference();
	 * 			| else if orientation == left
	 * 			| 	then new_x_pos == this.getXPos() - this.getXSpeed()*100*dt
	 * 			|					- 0.5 * this.getXAcc() * 100 * Math.pow(dt,2) + this.getXDifference();
	 */
//	private double moveX(double dt) {
		
//	}
	
	
	/**
	 * Changes the horizontal coordinates of mazub when mazub is moving
	 * with a certain speed and accelerating with a certain acceleration
	 * for a given time interval.
	 * Changes the horizontal speed of Mazub.
	 * Changes the counter of the sprites when running and changes the 
	 * time since startMove
	 * @param dt: A small time interval
	 * 
	 * @effect if mazub's new horizontal position exceeds the boundaries of the game field, his new position 
	 * 			is set to the boundary of the game
	 * 			| if new_x_pos < MIN_X_VALUE
	 * 			|	then new_x_pos == MIN_X_VALUE
	 * 			| else if new_x_pos > MAX_X_VALUE
	 * 			|	then new_x_pos == MAX_X_VALUE
	 * @effect the current speed changes given the acceleration and dt
	 * 			| xSpeed + == dt * xAcc
	 * @effect the current position is changed to the rounded down new position
	 * 			and the difference between the two values is stored in x_difference
	 * 			| x_pos == new_x_pos
	 * 			| x_difference == new_x_pos - x_pos
	 * @effect if the speed is 0, the time since endMove is increased with dt
	 * 			if the speed is greater than 0, time since startMove is 
	 * 			increased with dt and when this time exceeds the time for a different
	 * 			sprite, the time since startmove is decreased by the time for a 
	 * 			different sprite and the counter for the sprites is increment or, if
	 * 			it has come to it's maximum value (m), set to zero.
	 * 			| if xSpeed == 0
	 * 			| 	then time_since_endMove += dt
	 * 			| else if xSpeed > 0
	 * 			| 	then time_since_startMove += dt
	 * 			| 		 if time_since_startMove >= TIME_DIFFERENT_SPRITE
	 * 			| 				then time_since_startMove -= TIME_DIFFERENT_SPRITE
	 * 			|					 if i< m-1
	 * 			| 						then i += 1
	 * 			|					 else i==0					
	 */
	private void advanceX(double dt) {				
	}

	
	//TODO OPASSEN VOLGORDE VAN TOEWIJZIGINGEN AAN NEWPOS 
	private double[] checkSurroundings(double newXPos, double newYPos) {
		if (againstLeftWall(newXPos,newYPos) && this.getOrientation() == Orientation.LEFT) {
			this.setXSpeed(0);
			this.setXAcc(0);
			newXPos = (this.getTilesLeft(newXPos,newYPos)[0][1] + 1) * getWorld().getTileLength();
		}	
		
		if (againstRightWall(newXPos,newYPos) && this.getOrientation() == Orientation.RIGHT) {
			this.setXSpeed(0);
			this.setXAcc(0);
			newXPos = (this.getTilesRight(newXPos,newYPos)[0][1]) * getWorld().getTileLength() - this.getSize()[0] -1;
		}
		
		if (isAgainstRoof(newXPos,newYPos)) {
			this.setYSpeed(0);
			this.setXSpeed(0);
			this.setXAcc(0);
			newYPos = (this.getTilesAbove(newXPos,newYPos)[0][1]) * getWorld().getTileLength() - this.getSize()[1] -1;
		}
		
		if (this.onFloor(newXPos,newYPos) && this.isFalling()) {
			this.endFall();
			newYPos = ((this.getTilesUnder(newXPos,newYPos)[0][1] +1) * getWorld().getTileLength() -1);
		}
		
		if (( ! onFloor(newXPos,newYPos)) && ( ! this.isFalling())){
			fall();
		}
		
		return new double[] {newXPos, newYPos};
	}
	
	
	private void changeMovingTimes(double dt) {
		if (this.getXSpeed() == 0) {
			this.setTimeSinceEndMove(this.getTimeSinceEndMove() + dt);
		}
		else if (this.getXSpeed() > 0) {
			this.setTimeSinceStartMove(this.getTimeSinceStartMove() + dt);
			if (this.getTimeSinceStartMove() > Mazub.getTimeDifferentSprite()) {
				this.setTimeSinceStartMove(this.getTimeSinceStartMove()
						- Mazub.getTimeDifferentSprite());
				if (this.getCounterSprites() < this.getNbRunningSprites()-1) {
					this.setCounterSprites(this.getCounterSprites() +1);
				}
				else {
					this.setCounterSprites(0);
				}
			}
		}
	}

	
	
	//TODO  deel van functies naar gameoject verhuizen
	/*opbouwen als volgt:
	 * - newpos
	 * - boundaries checken
	 * - speed aanpassen
	 * - isagainst.... (als hij niet dood is)
	 * - pos aanpassen
	 */
	public void advanceTime(double dt) throws IllegalDtException {
		if ( ! isValidDt(dt))
			throw new IllegalDtException(dt);

		//TODO dis is mss nogal inefficient, waarom?, omdat 2 keer newpos wordt uitgerekend
		double newXPos = this.calculateNewPos(dt)[0];
		double newYPos = this.calculateNewPos(dt)[1];
		
		// Hier moet hij gewoon sterven als hij buiten gaat -> just!
		if( ! isWithinBoundaries(newXPos,newYPos)) {
			this.die();
			// TODO spel eindigen ofzo -> HOER
		}
		double[] newPos = checkSurroundings(newXPos,newYPos);
		
		this.setNewSpeed(dt);
		this.changeMovingTimes(dt);
		
		this.setXPos(newPos[0]);
		this.setYPos(newPos[1]);
		
		
		if (this.getNbHitpoints() <= 0) {
			this.die();
		}
	}
	
	
	/**
	 * Starts the ducking of Mazub by setting the boolean duck on true 
	 * and the maxSpeed back to MAX_SPEED_DUCK
	 * @effect
	 * 			| duck == true
	 * 			| maxSpeed == MAX_SPEED_DUCK
	 */
	public void startDuck() {
		this.setDuck(true);
		this.setMaxSpeed(this.getMaxSpeedDuck());
	}	
	/**
	 * Ends the ducking of Mazub by setting the boolean duck on false 
	 * and the maxSpeed back to MAX_SPEED
	 * @effect
	 * 			| duck == false
	 * 			| maxSpeed == MAX_SPEED
	 */
	public void endDuck()  {
//		if ( ! this.isAgainstRoof()){
//			this.setDuck(false);
//			this.setMaxSpeed(this.getMaxMovingSpeed());
//		}	
		
		this.setDuck(false);
		this.setMaxSpeed(this.getMaxMovingSpeed());
		
	}		
	/**
	 * GEEN formele documentatie nodig
	 */
	@Raw
	public Sprite getCurrentSprite() {
		assert isValidSprite(this.getSprite());
		this.setNbRunningSprites(((this.getSprite()).length-8)/2);
		if ((this.getXSpeed()==0) && (! this.isDucked()) &&
				(this.getTimeSinceEndMove() > this.getNotMovingTime())){
			return sprites[0];
		}
		else if ((this.getXSpeed()==0) &&
				(this.getTimeSinceEndMove() > this.getNotMovingTime())){
			return sprites[1];
		}
		else if ((this.getXSpeed()==0) &&
				(!this.isDucked()) && (this.getOrientation() == Orientation.RIGHT )){
			return sprites[2];
		}		
		else if ((this.getXSpeed()==0) && (!this.isDucked())){
			return sprites[3];
		}
		else if ((this.getXSpeed() > 0) && (this.getOrientation() == Orientation.RIGHT ) 
				&& (this.isFalling()) && (!this.isDucked())){
			return sprites[4];
		}
		else if ((this.getXSpeed() > 0) && (this.isFalling()) && (!this.isDucked())){
			return sprites[5];
		}
		else if ((this.getOrientation() == Orientation.RIGHT) && (this.isDucked())){
			return sprites[6];
		}
		else if (this.isDucked()){
			return sprites[7];
		}
		else if (this.getOrientation() == Orientation.RIGHT){
			return sprites[8 + this.getCounterSprites()];
		}
		else {
			return sprites[8 + this.getNbRunningSprites() + this.getCounterSprites()]; 
		}
	}
	
// PART 2
	/**
	 * the amount of hitpoints mazub possesses
	 */
	private int hitpoints = this.getInitHitPoints();
	/**
	 * the amount of hitpoints mazub possesses in the beginning of a game
	 */
	private static int INIT_HIT_POINTS = 100;
	/**
	 * the maximum amount of hitpoints a mazub can reach
	 */
	private static int MAX_HIT_POINTS = 500;
	/**
	 * the initial amount of hitpoints
	 * @return INITHITPOINTS
	 */
	private int getInitHitPoints() {
		return INIT_HIT_POINTS;
	}
	/**
	 * the maximum amount of hitpoints
	 * @returnMAXHITPOINTS
	 */
	private int getMaxHitpoints() {
		return MAX_HIT_POINTS;
	}

	
	public void setNbHitpoints(int number) {
		if ( ! (number > this.getMaxHitpoints())) {
			this.hitpoints = number;
		}		
	}	
	
	/**
	 * Returns whether the given alien is currently immune against enemies
	 * 
	 * @return True if the given alien is immune against other enemies (i.e.,
	 *         there are no interactions between the alien and enemy objects).
	 */
	public boolean isImmune() {
		return immune;
	}
	
	
	
}
