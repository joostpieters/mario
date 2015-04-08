package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.util.Sprite;

public class Slime extends GameObject {
	
	/**
	 * Creates a new slime, located at the provided pixel location (x, y).
	 * The returned slime should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the slime's initial position
	 * @param y
	 *            The y-coordinate of the slime's initial position
	 * @param sprites
	 *            An array of sprites for the new slime
	 * @param school
	 *            The initial school to which the new slime belongs
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for the slime
	 * 			| !isValidPosition(x_pos,y_pos)
	 * @throws IllegalSpriteException
	 * 			The given sprite is not valid
	 * 			| !isValidSprite(sprites) 
	 */
// TODO school nog toewijzen	
	public Slime(int xPos,int yPos, Sprite[] sprites,School school) 
			throws IllegalPositionException, IllegalSpriteException {
		super(xPos, yPos, sprites);
		this.setSchool(school);
		this.setHitpoints(Slime.getInitHitpoints());
		this.setMaxSpeed(Slime.getMaxXSpeed());
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


	private static int INIT_HITPOINTS = 100;
	private static int getInitHitpoints() {
		return INIT_HITPOINTS;
	}
	/**
	 * the horizontal acceleration of a slime
	 */
	private double xAcc = 0.7;
	@Override
	public double getXAcc() {
		return xAcc;
	}
	/**
	 * the maximum horizontal speed a slime can reach
	 */
	private static double MAX_X_SPEED = 2.5;
	/**
	 * the minimal duration of a movement period (2s)
	 */
	private static int MIN_MOVEMENT_DURATION = 2;
	/**
	 * the maximal duration of a movement period (6s)
	 */
	private static int MAX_MOVEMENT_DURATION = 6;
	/**
	 * the amount of hitpoints a slime loses when touching 
	 * a shark or mazub.
	 */
	private static int CONTACT_DAMAGE = 50;

	/**
	 * the damage every slime in the school looses when a single
	 * slime looses some hitpoints
	 */
	private int SCHOOL_DAMAGE = 1;
	/**
	 * the amount of hitpoints a slime hands over to the members of 
	 * the old school when joining a school
	 */
	private int JOINING_HITPOINTS_GIVE = 1;
	/**
	 * the amount of hitpoints a slime receives when from every
	 * member of the new group when joining
	 */
	private int JOINING_HITPOINTS_RECIEVE = 1;
	/**
	 * the maximum amount of slime schools in a game world
	 */
	private int MAX_AMOUNT_OF_SCHOOLS = 10;	
	
	private School school;
	/**
	 * Returns the current school to which the given slime belongs.
	 * 
	 * @param slime
	 *            The slime for which to retrieve the school.
	 * 
	 * @return The current school of the given slime.
	 */
	public School getSchool() {
		return this.school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	
	private int MAX_HITPOINTS = 100;
	@Override
	/**
	 * the maximum amount of hitpoints
	 * @returnMAXHITPOINTS
	 */
	protected int getMaxHitpoints() {
		return MAX_HITPOINTS;
	}
	
//	GETTERS	
	

	private static double getMaxXSpeed() {
		return  MAX_X_SPEED;
	}
	



	
	public static int getContactDamage() {
		return CONTACT_DAMAGE;
	}
	
	private int getSchoolDamage() {
		return SCHOOL_DAMAGE;
	}
	private int getJoiningHitpointsGive() {
		return JOINING_HITPOINTS_GIVE;
	}
	private int getJoiningHitpointsRecieve() {
		return JOINING_HITPOINTS_RECIEVE;
	}
	private int getMaxAmountOfSchools() {
		return MAX_AMOUNT_OF_SCHOOLS;
	}
	


	
//	VALIDATIONS
	@Override
	protected boolean isValidSprite(Sprite[] sprites) {
		return sprites.length == 2;
	}
	
	private double[] checkSurroundings(double newXPos, double newYPos) {

		if (this.getOrientation() == Orientation.LEFT && againstLeftWall(newXPos,newYPos)) {
			newXPos = (this.getTilesLeft(newXPos,newYPos)[0][0] + 1) * getWorld().getTileLength();
			this.setXSpeed(0);
			this.setXAcc(0);
			if (this.getYSpeed()>0) {
				this.setYSpeed(0);
			}
		}	

		if (this.getOrientation() == Orientation.RIGHT && againstRightWall(newXPos,newYPos)) {
			newXPos = (this.getTilesRight(newXPos,newYPos)[0][0]) * getWorld().getTileLength() - this.getSize()[0];
			this.setXSpeed(0);
			this.setXAcc(0);
			if (this.getYSpeed()>0) {
				this.setYSpeed(0);
			}
		}
		if (isAgainstRoof(newXPos,newYPos)) {
			newYPos = (this.getTilesAbove(newXPos,newYPos)[0][1]) * getWorld().getTileLength() - this.getSize()[1] -1;
			this.setYSpeed(0);
		}
		
		if (this.isFalling() && this.onFloor(newXPos,newYPos)) {
			newYPos = ((this.getTilesUnder(newXPos,newYPos)[0][1] +1) * getWorld().getTileLength() -1);
			this.endFall();
		}
		
		if (( ! this.isFalling()) && ( ! onFloor(newXPos,newYPos))){
			fall();
		}
		
		return new double[] {newXPos, newYPos};
	}
	
	public double[] colliding(double newXPos, double newYPos) {		
		for(Slime other: this.getWorld().getSlimes()) {
			if(other != this) {
				double x1 = newXPos;
				double xDim1 = this.getXDim();
				double y1 = newYPos;
				double yDim1 = this.getYDim();
				double x2 = other.getXPos();
				double xDim2 = other.getXDim();
				double y2 = other.getYPos();
				double yDim2 = other.getYDim();
				boolean touched = false;
				if (this.collidesRight(x1, xDim1, y1, yDim1, x2, xDim2, y2, yDim2)) {
					newXPos = x2 - xDim1;
					this.setXSpeed(0);
					this.setXAcc(0);
					if (this.getYSpeed()>0) {
						this.setYSpeed(0);
					}
					touched = true;
				}
				if (this.collidesLeft(x1, xDim1, y1, yDim1, x2, xDim2, y2, yDim2)) {
					newXPos = x2 + xDim2;
					this.setXSpeed(0);
					this.setXAcc(0);
					if (this.getYSpeed()>0) {
						this.setYSpeed(0);
					}
					touched = true;
				}
				if (this.collidesAbove(x1, xDim1, y1, yDim1, x2, xDim2, y2, yDim2)) {
					newYPos = y2 - yDim1;
					this.setYSpeed(0);
					touched = true;
				}
				if (this.isFalling() &&  this.collidesUnder(x1, xDim1, y1, yDim1, x2, xDim2, y2, yDim2)) {
					newYPos = y2 + yDim2;
					this.endFall();
					touched = true;
				}
				if (( ! this.isFalling()) && ( ! this.collidesUnder(x1, xDim1, y1, yDim1, x2, xDim2, y2, yDim2))){
					fall();
				}
				
				if (touched) {
					
				}
			}
		}
		return new double[] {newXPos, newYPos};
	}
	

	
	public void advanceTime(double dt) throws IllegalDtException {
		if ( ! isValidDt(dt))
			throw new IllegalDtException(dt);
		
		randomMovement(dt);
		
		//TODO dis is mss nogal inefficient, waarom?, omdat 2 keer newpos wordt uitgerekend
		double newXPos = this.calculateNewPos(dt)[0];
		double newYPos = this.calculateNewPos(dt)[1];
		
		// Hier moet hij gewoon sterven als hij buiten gaat -> just!
		if( ! isWithinBoundaries(newXPos,newYPos)) {
			this.die();
			// TODO spel eindigen ofzo -> HOER
		}
		
		double[] newPos = checkSurroundings(newXPos,newYPos);
		newPos = colliding(newPos[0],newPos[1]);
		this.setNewSpeed(dt);
		
		this.setXPos(newPos[0]);
		this.setYPos(newPos[1]);
		
		int touchedSharks = this.getWorld().touchedSharks(this.getXPos(), this.getYPos(), this.getXDim(), this.getYDim());
		this.setHitpoints(this.getHitpoints() - touchedSharks * Slime.getContactDamage() );
		
		
		if (this.getHitpoints() <= 0) {
			this.die();
		}
	}


	/**
	 * Return the current sprite image for the given slime.
	 * 
	 * @return The current sprite image for the given slime, determined by its
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
	
	public void remove() {
		this.getWorld().removeSlime(this);
		this.setWorld(null);
	}
	
	private void randomMovement(double dt) {
		if(this.getTimeSinceMove() >= this.getMovementDuration()) {
			this.stopMove();
			this.setMovementDuration(Math.random() * 4 + 2);
			this.setTimeSinceMove(dt);
			if(Math.random()>=0.5) {
				this.startMoveRight();
			}
			else {
				this.startMoveLeft();
			}
		}
		else {
			this.setTimeSinceMove(this.getTimeSinceMove() + dt);
		}
	}
	

	/**
	 * Mazub starts moving to the right
	 * @effect 	| 
	 */
	public void startMoveRight(){
		if(this.getOrientation() == Orientation.LEFT) {
			this.setXSpeed(0);
			this.setOrientationRight();
		}
	}
	/**
	 * Mazub starts moving to the left
	 * @effect 	|
	 */
	public void startMoveLeft(){
		this.setOrientationLeft();
	}	
	
	public void stopMove() {
		this.setXSpeed(0);
	}
	
	
}
	
