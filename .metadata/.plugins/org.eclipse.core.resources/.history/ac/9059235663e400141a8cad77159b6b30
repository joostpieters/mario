package jumpingalien.model;

import java.util.ArrayList;
import java.util.List;

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
		super(xPos,yPos,sprites);
		this.setHitpoints(Shark.getInitHitpoints());
		this.setMaxSpeed(Shark.getMaxXSpeed());
	}
	
	/**
	 * the initial amount of hitpoints a shark possesses
	 */
	private static final int INIT_HIT_POINTS = 100;
	@Immutable
	private static int getInitHitpoints() {
		return INIT_HIT_POINTS;
	}		
	/**
	 * the maximum horizontal speed a shark can reach
	 */
	private static final double MAX_X_SPEED = 4;
	@Immutable
	private static final double getMaxXSpeed() {
		return  MAX_X_SPEED;
	}
	/**
	 * the vertical speed at which the shark moves when 
	 * startJump() is initiated
	 */
	private static final int JUMP_SPEED = 2;
	@Immutable
	private static final double getJumpSpeed() {
		return JUMP_SPEED;
	}
	private static final double MOVE_ACC = 1.5;
	@Immutable
	private static final double getMoveAcc() {
		return MOVE_ACC;
	}
	/**
	 * the amount of hitpoints a shark loses when touching 
	 * a mazub or a slime
	 */
	private static final int CONTACT_DAMAGE = 50;
	@Immutable
	public static final int getContactDamage() {
		return CONTACT_DAMAGE;
	}	
	private final int MAX_HITPOINTS = 100;
	@Override
	/**
	 * the maximum amount of hitpoints
	 * @return MAX_HITPOINTS
	 */
	@Immutable
	protected int getMaxHitpoints() {
		return MAX_HITPOINTS;
	}
	private static final int LOSS_HITPOINTS_IN_AIR = 6;
	private static final int getLossHitpointsInAir() {
		return LOSS_HITPOINTS_IN_AIR;
	}	
	private static final double TIME_UNTIL_CHOKE = 0.2;
	private static final double getTimeUntilChoke() {
		return TIME_UNTIL_CHOKE;
	}
	private boolean inWater = true;
	private boolean isInWater() {
		return inWater;
	}
	private void setInWater() {
		this.inWater = true;
	}
	private void setNotInWater() {
		this.inWater = false;
	}
	private double timeInAir = 0;
	private double getTimeInAir() {
		return timeInAir;
	}
	private void setTimeInAir(double time) {
		this.timeInAir = time;
	}
	private double movementDuration = 0;
	private double getMovementDuration() {
		return movementDuration;
	}
	private void setMovementDuration(double time) {
		this.movementDuration = time;
	}	
	private double timeSinceMove = 0;
	/**
	 * @return the timeSinceMove
	 */
	private double getTimeSinceMove() {
		return timeSinceMove;
	}
	/**
	 * @param timeSinceMove the timeSinceMove to set
	 */
	private void setTimeSinceMove(double time) {
		this.timeSinceMove = time;
	}	
	private int jumpCounter = 0;
	private int getJumpCounter() {
		return this.jumpCounter;
	}
	private void setJumpCounter(int count) {
		this.jumpCounter = count;		
	}
	private boolean moving = true;
	private boolean isMoving() {
		return this.moving;
	}
	private void setMoving(boolean move) {
		this.moving = move;
	}
	
//	Validations
	
	@Override
	protected boolean isValidSprite(Sprite[] sprites) {
		return sprites.length == 2;
	}	
	
	private double[] checkSurroundings(double newXPos, double newYPos) {
		if (againstLeftWall(newXPos,newYPos) && this.getOrientation() == Orientation.LEFT) {
			newXPos = (this.getTilesLeft(newXPos,newYPos)[0][0] + 1) * getWorld().getTileLength();
			this.stopMoving();
		}
		if (againstRightWall(newXPos,newYPos) && this.getOrientation() == Orientation.RIGHT) {
			newXPos = (this.getTilesRight(newXPos,newYPos)[0][0]) * getWorld().getTileLength() - this.getSize()[0];
			this.stopMoving();
		}
		if (isAgainstRoof(newXPos,newYPos)) {
			newYPos = (this.getTilesAbove(newXPos,newYPos)[0][1]) * getWorld().getTileLength() - this.getSize()[1] -1;
			this.setYSpeed(0);
		}
		if (this.isMoving() && this.isInWater() && ( ! this.isFullyInFeature(newXPos, newYPos, 2))) {
			this.setYSpeed(0);
			this.setYAcc(0);
			newYPos = (this.getTilesAbove(newXPos,newYPos)[0][1]) * getWorld().getTileLength() - this.getSize()[1] -1;
		}
		if (this.isFalling() && this.onFloor(newXPos,newYPos)) {
			newYPos = ((this.getTilesUnder(newXPos,newYPos)[0][1] +1) * getWorld().getTileLength() -1);
			this.endFall();
		}
		if (this.isFalling() && this.isFullyInFeature(newXPos, newYPos, 2)) {
			this.endFall();
		}
		if (this.onFloor(newXPos,newYPos)) {
			newYPos = ((this.getTilesUnder(newXPos,newYPos)[0][1] +1) * getWorld().getTileLength() -1);
			if(this.getYAcc() < 0 ) {
				this.setYAcc(0);
				this.setYSpeed(0);
			}
		}			

		return new double[] {newXPos, newYPos};
	}
	
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
	
	private void checkIfWithinBoundaries(double newXPos, double newYPos) {
		if ( ! isWithinBoundaries(newXPos,newYPos)) {
			this.remove();
		}
	}
	
	private void advanceTimeWhileLiving(double dt) {
		this.randomMovement(dt);
		
		double[] newCalculatedPos = this.calculateNewPos(dt);
		double newXPos = newCalculatedPos[0];
		double newYPos = newCalculatedPos[1];
		
		this.checkIfWithinBoundaries(newXPos, newYPos);
		
		double[] newPos = checkSurroundings(newXPos,newYPos);
		newPos = colliding(newPos[0],newPos[1], dt);
		this.setNewSpeed(dt);
		this.setXPos(newPos[0]);
		this.setYPos(newPos[1]);
		
		int touchedSlimes = this.getWorld().touchedSlimes(this.getXPos(), this.getYPos(), this.getXDim(), this.getYDim());
		this.setHitpoints(this.getHitpoints() - touchedSlimes * Shark.getContactDamage() );
		
		this.loseHitpointsBecauseOfFeature(dt);
		this.updateImmunity(dt);
		
		if ((this.isFullyInFeature(newXPos, newYPos, 2)) && ( ! this.isInWater())) {
			this.setInWater();			
		}
		if (this.getHitpoints() <= 0) {
			this.die();
		}
	}
	
	private void advanceTimeWhileDeath(double dt) {
		setTimeSinceDeath(this.getTimeSinceDeath() + dt);
		if (this.getTimeSinceDeath() >= GameObject.getTimeUntilRemove()) {
			this.remove();
		}
	}
	
	public void advanceTime(double dt) throws IllegalDtException {
		if ( ! isValidDt(dt))
			throw new IllegalDtException(dt);		
		if (! this.isDying()) { 
			this.advanceTimeWhileLiving(dt);
		}		
		else {
			this.advanceTimeWhileDeath(dt);
		}
	}	
	
	private void remove() {
		this.getWorld().removeShark(this);
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

	/**
	 * Mazub starts moving to the right
	 * @effect 	| 
	 */
	public void moveRight(){
		this.setXAcc(Shark.getMoveAcc());
		this.setOrientationRight();
		if (isInWater()) {
			this.setYAcc(Math.random() * 4 - 2);
		}
	}
	
	/**
	 * Mazub starts moving to the left
	 * @effect 	|
	 */
	public void moveLeft(){
		this.setXAcc(Shark.getMoveAcc());
		this.setOrientationLeft();
		if (isInWater()) {
			this.setYAcc(Math.random() * 4 -  2);
		}
	}
	
	/**
	 * Mazub starts moving to the right
	 * @effect 	| 
	 */
	public void jumpRight(){
		this.setXAcc(Shark.getMoveAcc());
		this.setYSpeed(Shark.getJumpSpeed());
		this.setOrientationRight();
	}
	
	/**
	 * Mazub starts moving to the left
	 * @effect 	|
	 */
	public void jumpLeft(){
		this.setXAcc(Shark.getMoveAcc());
		this.setYSpeed(Shark.getJumpSpeed());
		this.setOrientationLeft();
	}
	
	public void stopMove() {
		this.setXSpeed(0);
		if (!isFalling()) {
			this.setYAcc(0);
		}
	}
	
	private void randomMovement(double dt) {
		if(this.getTimeSinceMove() >= this.getMovementDuration()) {
			this.stopMove();
			this.setMovementDuration(Math.random() * 3 + 1);
			this.setTimeSinceMove(dt);
			double randomNb;
			if((this.getJumpCounter() >= 4) && (this.onFloor(this.getXPos(),this.getYPos()) || this.isInWater())) {
				randomNb = Math.random() * 4;
			}
			else {
				randomNb = Math.random() * 2;
			}
			if(randomNb < 1) {
				this.moveRight();
				this.setJumpCounter(this.getJumpCounter() + 1);
				this.setMoving(true);
			}
			else if(randomNb < 2){
				this.moveLeft();
				this.setJumpCounter(this.getJumpCounter() + 1);
				this.setMoving(true);
			}
			else if(randomNb < 3){
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
	
	public double[] colliding(double newXPos, double newYPos,double dt) {	
		List<GameObject> allSlimesSharksMazub =  new ArrayList<GameObject>(this.getWorld().getSlimes());
		allSlimesSharksMazub.addAll(this.getWorld().getSharks());
		allSlimesSharksMazub.add(this.getWorld().getAlien());
		boolean onGameObject = false;
		for(GameObject other: allSlimesSharksMazub) {
			if(this != other) {
				// double x1 = newXPos;
				double xDim1 = this.getXDim();
				// double y1 = newYPos;
				double yDim1 = this.getYDim();
				double x2 = other.getXPos();
				double xDim2 = other.getXDim();
				double y2 = other.getYPos();
				double yDim2 = other.getYDim();
				boolean touched = false;
				
				double[] newPos = collidesSomewhere(newXPos, xDim1, newYPos, yDim1, x2, xDim2, y2,
						yDim2);
				newXPos = newPos[0];
				newYPos = newPos[1];
				if (newPos[2] == 1) {
					touched = true;
				}
				if (newPos[3] == 1) {
					onGameObject = true;
				}
				if ( touched && ( ! other.isDying()) && !(other instanceof Shark))  {
					this.contactDamage(dt);
					other.contactDamage(dt);
				}		
			}
		}
		if (this.isFalling() && onGameObject) {
			this.endFall();
		}
		else if ( ! this.isFalling() && ( ! onGameObject) && ( ! this.isFullyInFeature(newXPos, newYPos, 2)) && ( ! this.onFloor(newXPos,newYPos))) {
			fall();
		}
		return new double[] {newXPos, newYPos};
	}
		
}
