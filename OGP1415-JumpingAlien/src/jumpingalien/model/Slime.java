package jumpingalien.model;

import java.util.ArrayList;
import java.util.List;

import program.Program;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.model.exceptions.IllegalDtException;
import jumpingalien.model.exceptions.IllegalPositionException;
import jumpingalien.model.exceptions.IllegalSchoolException;
import jumpingalien.model.exceptions.IllegalSpriteException;
import jumpingalien.util.Sprite;

/**
 * A class that describes the GameObject Slime
 * @author Ward Romanus, Pieter Van den Berghe
 *
 * @invar The school is always valid
 * 			| isValidSchool(this.getSchool)
 */
public class Slime extends GameObject {
	
	/**
	 * Creates a new slime, located at the provided pixel location (x, y).
	 * The returned slime should not belong to a world.
	 * 
	 * @param x
	 * 			The x-coordinate of the slime's initial position
	 * @param y
	 * 			The y-coordinate of the slime's initial position
	 * @param sprites
	 * 			An array of sprites for the new slime
	 * @param school
	 *			The initial school to which the new slime belongs
	 * @effect the Slime is created at the given position with the given sprites
	 * 			| super(xPos, yPos, sprites)
	 * @effect the slime is added to a School school
	 * 			| setSchool(school)
	 * 			| school.newSlime(this)
	 * @effect the Slime gets it's default amount of hitpoints
	 * 			| setHitpoints(getInitHitpoints())
	 * @effect the maximum speed gets initialized
	 * 			| setMaxSpeed(Slime.getMaxXSpeed())
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for the slime
	 * 			| ! isValidPosition(xPos,yPos)
	 * @throws IllegalSpriteException
	 * 			The given sprite is not valid
	 * 			| ! isValidSprite(sprites) 
	 * @throws IllegalSchoolException
	 * 			The given school is not valid
	 * 			| ! isValidSchool(school)
	 */
	@Raw
	public Slime(int xPos,int yPos, Sprite[] sprites,School school) 
			throws IllegalPositionException, IllegalSpriteException, IllegalSchoolException {
		super(xPos, yPos, sprites);
		if ( ! isValidSchool(school)) 
			throw new IllegalSchoolException(school);
		this.setSchool(school);
		this.getSchool().newSlime(this);
		this.setHitpoints(Slime.getInitHitpoints());
		this.setMaxSpeed(Slime.getMaxXSpeed());
	}
	
	@Raw
	public Slime(int xPos,int yPos, Sprite[] sprites,School school, Program program) 
			throws IllegalPositionException, IllegalSpriteException, IllegalSchoolException {
		super(xPos, yPos, sprites, program);
		if ( ! isValidSchool(school)) 
			throw new IllegalSchoolException(school);
		this.setSchool(school);
		this.getSchool().newSlime(this);
		this.setHitpoints(Slime.getInitHitpoints());
		this.setMaxSpeed(Slime.getMaxXSpeed());
	}
	
	
	/**
	 * the amount of hitpoints a slime loses when drowning (per 0.2 seconds)
	 */
	private static final int LOSS_HITPOINTS_IN_WATER = 2;
	/**
	 * returns the int that gives the amount of hitpoints a slime loses when 
	 * drowning in water
	 * @return the amount of hitpoints to lose every 0.2 seconds
	 * 			| LOSS_HITPOINTS_IN_WATER
	 */
	@Basic @Immutable 
	private static final int getLossHitpointsInWater() {
		return LOSS_HITPOINTS_IN_WATER;
	}	
	/**
	 * a double counting the duration of a movement period
	 */
	private double movementDuration = 0;
	/**
	 * returns the double giving the duration of a movement period
	 * @return the movement duration
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
	 * 			| time >= 0
	 * @post the value of movementDuration is set to the given value
	 * 			| this.movementDuration = time
	 */
	@Raw
	private void setMovementDuration(double time) {
		assert time >= 0;
		this.movementDuration = time;
	}
	/**
	 * a double indicating the passed time since the Slime has moved
	 */
	private double timeSinceMove = 0;
	/**
	 * returns the time passed since the last movement
	 * @return timeSinceMove
	 */
	@Basic @Raw
	private double getTimeSinceMove() {
		return timeSinceMove;
	}
	/**
	 * Sets timeSinceEndMove to the given value
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
	 * the amount of hitpoints a Slime possesses when initialized
	 */
	private static final int INIT_HITPOINTS = 100;
	/**
	 * returns the amount of hitpoints a Slime possesses when initialized
	 * @return the amount of initial hitpoints
	 * 			| INIT_HITPOINTS
	 */
	@Basic @Immutable
	private static final int getInitHitpoints() {
		return INIT_HITPOINTS;
	}
	/**
	 * the horizontal acceleration of a slime
	 */
	private static final double xAcc = 0.7;
	/**
	 * returns the horizontal acceleration of a Slime
	 */
	@Override @Basic @Immutable @Raw
	protected final double getXAcc() {
		return xAcc;
	}
	/**
	 * the maximum horizontal speed a slime can reach
	 */
	private static final double MAX_X_SPEED = 2.5;
	/**
	 * returns the maximum horizontal speed a Slime may reach
	 * @return the max speed
	 * 			| MAX_X_SPEED	
	 */
	@Basic @Immutable 
	private static final double getMaxXSpeed() {
		return  MAX_X_SPEED;
	}	
	/**
	 * the amount of hitpoints a slime loses when touching 
	 * a Shark or mazub.
	 */
	private static final int CONTACT_DAMAGE = 50;
	/**
	 * returns the lost hitpoints when colliding with a Mazub of Shark
	 * @return the hitpoints upon making contact
	 * 			| CONTACT_HITPOINTS
	 */
	@Basic @Immutable 
	public static final int getContactDamage() {
		return CONTACT_DAMAGE;
	}
	/**
	 * the damage every slime in the school looses when a single
	 * slime looses some hitpoints
	 */
	private static final int SCHOOL_DAMAGE = 1;
	/**
	 * returns the hitpoints to lose wxhen a Slime in a school loses hitpoints
	 * @return the hitpoints to lose
	 * 			| SCHOOL_DAMAGE
	 */
	@Basic @Immutable 
	public static final int getSchoolDamage() {
		return SCHOOL_DAMAGE;
	}
	/**
	 * the maximum amount of slime schools in a game world
	 */
	private static final int MAX_AMOUNT_OF_SCHOOLS = 10;
	/**
	 * returns the maximal amount of schools that may exist
	 * @return the maximal amount of schools
	 * 			| MAX_AMOUNT_OF_SCHOOLS
	 */
	@Basic @Immutable 
	private static final int getMaxAmountOfSchools() {
		return MAX_AMOUNT_OF_SCHOOLS;
	}
	/**
	 * the School school of this Slime
	 */
	private School school;
	/**
	 * Returns the current school to which the given slime belongs.
	 * 
	 * @param slime
	 *            The slime for which to retrieve the school.
	 * 
	 * @return The current school of the given slime.
	 * 			| this.school
	 */
	@Basic @Raw
	public School getSchool() {
		return this.school;
	}
	/**
	 * sets the School of this Slime to school
	 * @param school
	 * 			the new value for the School school
	 * @pre The new school has to be valid
	 * 			| isValidSchool(school)
	 * @post the School is equal to the given School school
	 * 			| this.school = school
	 */
	@Raw
	public void setSchool(School school) {
		assert this.isValidSchool(school);
		this.school = school;
	}
	/**
	 * the maximal amount of hitpoints a Slime may posses
	 */
	private static final int MAX_HITPOINTS = 500;
	@Override
	/**
	 * the maximum amount of hitpoints
	 * @return the max amount of hitpoints
	 * 			| MAXHITPOINTS
	 */
	@Basic @Immutable 
	protected final int getMaxHitpoints() {
		return MAX_HITPOINTS;
	}	
	
//	VALIDATIONS
	
	/**
	 * returns true if the Sprite sprites is valid
	 * @return the length of sprites is 2
	 * 			| sprites.length == 2
	 */
	@Override
	protected boolean isValidSprite(Sprite[] sprites) {
		return sprites.length == 2;
	}
	
	private boolean isValidSchool(School school) {
		return school != null;
	}
	
	/**
	 * checks the surroundings of the Slime and adapts the movement if needed
	 * @param newXPos
	 * 			the new horizontal position
	 * @param newYPos
	 * 			the new vertical position
	 * @effect if the Slime collides with a wall, it stops moving if not falling and the horizontal position is adapted
	 * 			| if (getOrientation() == Orientation.LEFT && againstLeftWall(newXPos, newYPos))
	 * 			| 	then newXPos = getTilesLeft(newXPos, newYPos)[0][0] * world.geTileLength() - getSize()[0]
	 * 			| 		 stopMoving()
	 *			| if (getOrientation() == Orientation.RIGHT && againstRightWall(newXPos, newYPos))
	 * 			| 	then newXPos = getTilesRight(newXPos, newYPos)[0][0] * world.geTileLength() - getSize()[0]
	 * 			| 		 stopMoving()
	 * @effect if the Slime collides with a roof, the vertical speed drops to zero and the vertical position is adapted
	 * 			| if (isAgainstRoof(newXPos, newYPos))
	 * 			| 	then newYPos = getTilesAbove(newXPos, newYPos[0][1] * world.getTileLength() - getSize()[1] - 1
	 * 			| 		 stopMoving()
	 * @effect if the Slime collides with a floor when falling, the fall is ended and the vertical position is adapted
	 * 			| if (isFalling() && onFloor(newXPos, newYPos))
	 * 			| 	then newYPos = ((getTilesUnder(newXPos, newYPos)[0][1] + 1) * getWorld().getTileLength() - 1)
	 * 			| 		 endFall()
	 * @effect the Slime falls when not touching a floor and not falling
	 * 			| if (( ! isFalling()) && ( ! onFloor(newXPos, newYPos)))
	 * 			| 	then fall()
	 * @return {newXPos, newYPos}
	 */
	@Raw
	private double[] checkSurroundings(double newXPos, double newYPos) {

		if (this.getOrientation() == Orientation.LEFT && this.againstLeftWall(newXPos, newYPos)) {
			newXPos = (this.getTilesLeft(newXPos, newYPos)[0][0] + 1) * this.getWorld().getTileLength();
			this.stopMoving();
		}
		if (this.getOrientation() == Orientation.RIGHT && againstRightWall(newXPos,newYPos)) {
			newXPos = (this.getTilesRight(newXPos,newYPos)[0][0]) * this.getWorld().getTileLength() - this.getSize()[0];
			this.stopMoving();
		}
		if (this.isAgainstRoof(newXPos, newYPos)) {
			newYPos = (this.getTilesAbove(newXPos,newYPos)[0][1]) * this.getWorld().getTileLength() - this.getSize()[1] - 1;
			this.setYSpeed(0);
		}		
		if (this.isFalling() && this.onFloor(newXPos, newYPos)) {
			newYPos = ((this.getTilesUnder(newXPos, newYPos)[0][1] +1) * this.getWorld().getTileLength() -1);
			this.endFall();
		}		
		if (( ! this.isFalling()) && ( ! this.onFloor(newXPos, newYPos))) {
			fall();
		}
		
		return new double[] {newXPos, newYPos};
	}
	
	/**
	 * adapts the hitpoints of the Slime and a touched other GameObject other or changes the School
	 * when other is a Slime
	 * @param other
	 * 			the touched GameObject
	 * @param touched
	 * 			a boolean indicating if the slime touched other
	 * @param dt	
	 * 			a small time interval
	 * @effect if this Slime touched other and other isn't dying, both object's hitpoints are adjusted
	 * 			or the schools are adapted if other is a Slime
	 * 			| if ((touched == 1) && ( ! other.isDying()))
	 * 			| 	then if (other instanceof Slime) 
	 * 			| 			then if (this.getSchool().getSize() > ((Slime) other).getSchool().getSize()) 
	 * 			|					then school.addSlime((Slime) other)
	 * 			| 				 else if (this.getSchool().getsize() < ((Slime) other).getSchool().getSize()) 
	 * 			| 					then ((Slime) other).getSchool().addSlime(this)
	 * 			| 		 else 	
	 * 			| 			then this.contactDamage(dt)
	 * 			| 				 if ( ! collidesAbove(newXPos, this.getXDim(), newYPos, this.getYDim(), other.getXPos(),
	 * 			| 						other.getXDim(), other.getYPos(), other.getYDim()) && other instanceof Mazub)
	 * 			| 					then other.contactDamage(dt)
	 */
	@Raw
	private void adjustHitpoints(GameObject other, double touched, double dt, double newXPos, double newYPos) {
		if ((touched == 1) && ( ! other.isDying())) {
			if (other instanceof Slime) {
				if (this.getSchool().getSize() > ((Slime) other).getSchool().getSize()) {
					this.getSchool().addSlime((Slime) other);
					
				}
				else if (this.getSchool().getSize() < ((Slime) other).getSchool().getSize()) {
					((Slime) other).getSchool().addSlime(this);
				}
			}
			else   {
				this.contactDamage(dt);
				if ( ! (this.collidesAbove(newXPos, this.getXDim(), newYPos, this.getYDim(), other.getXPos(),
						other.getXDim(), other.getYPos(), other.getYDim()) && other instanceof Mazub)) { 
					other.contactDamage(dt);
				}
			}
		}	
	}
	
	/**
	 * adapts the position of this Slime and all other game objects when colliding, it also
	 * updates the hitpoints of every object in case of a collision
	 * @param newXPos
	 * 			the newly calculated horizontal position
	 * @param newYPos
	 * 			the newly calculated vertical position
	 * @param dt
	 * 			a small time interval
	 * @effect a local list allSharksSlimeMazub contains all the Sharks, Slimes and Mazub(s)
	 * 			| allSharksSlimesMazub =  new ArrayList<GameObject>world.getSharks())
	 *			| allSharksSlimesMazub.addAll(world.getSlimes());
	 *			| allSharksSlimesMazub.add(world.getAlien())
	 * @effect a local boolean onGameObject indicating if this is on another game object is set to false
	 * 			and a local double newPos is initialized
	 * 			| onGameObject = false
	 * 			| newPos = {newXPos, newYPos}
	 * @effect the positions and hitpoints of all colliding gameObjects are adjusted
	 * 			| for each GameObject other: allSharksSlimesMazub:
	 * 			| 	if (other != this) 
	 * 			| 		then double xDim1 = this.getXDim()
	 * 			|			 double yDim1 = this.getYDim()
	 * 			| 			 double x2 = other.getXPos()
	 * 			| 			 double xDim2 = other.getXDim()
	 * 			| 			 double y2 = other.getYPos()
	 * 			| 			 double yDim2 = other.getYDim()
	 * 		 	|		     newPos = collidesSomewhere(newPos[0], xDim1, newPos[1], yDim1, x2, xDim2, y2, yDim2)
	 * 			| 			 if (newPos[3] == 1) 
	 * 			| 				then onGameObject = true
	 * 			| 			 adjustHitpoints(other, newPos[2], dt);
	 * @effect if this (falling) Slime is on top of another gameObject, the fall is ended,
	 * 			if this Slime is not on top of another gameObject and not falling, it starts falling
	 * 			| if (isFalling() && onGameObject)
	 * 			| 	then endFall()
	 * 			| else if ( ! isFalling() && ! onGameObject && ! onFloor(newPos[0], newPos[1]))
	 * 			| 	then fall()
	 * @return {newPos[0], newPos[1]}
	 */
	//TODO commentaar en mss checker
	@Raw
	private double[] collidingSharksSlimesMazub(double newXPos, double newYPos, double dt) {
		List<GameObject> allSharksSlimesMazubBuzam =  new ArrayList<GameObject>(this.getWorld().getSharks());
		allSharksSlimesMazubBuzam.addAll(this.getWorld().getSlimes());
		allSharksSlimesMazubBuzam.add(this.getWorld().getAlien());	
		allSharksSlimesMazubBuzam.add(this.getWorld().getBuzam());
		boolean onGameObject = false;
		double[] newPos = {newXPos, newYPos};
		for(GameObject other: allSharksSlimesMazubBuzam) {
			if(other != this) {
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
				this.adjustHitpoints(other, newPos[2], dt, newPos[0], newPos[1]);
			}
		}
		if (this.isFalling() && onGameObject) {
			this.endFall();
		}
		else if ( ! this.isFalling() && ( ! onGameObject) && ( ! this.onFloor(newPos[0],newPos[1]))) {
			fall();
		}
		return new double[] {newPos[0], newPos[1]};
	}
	
	/**
	 * lets this Slime lose hitpoints when in water or magma
	 * @param newXPos
	 * 			the newly calculated horizontal position
	 * @param newYPos
	 * 			the newly calculated vertical position
	 * @param dt
	 * 			a small time interval
	 * @effect if this Slime is in contact with magma, it loses hitpoints 
	 * 			| if (this.isInContactWithFeature(this.getXPos(), this.getYPos(), 3))
	 * 			| 	then 	if (this.getTimeInMagma() == 0) 
	 * 			| 				then this.loseHitpoints(Slime.getLossHitpointsInMagma());		
	 * 			| 			else if (this.getTimeInMagma() >= Slime.getBurnTime()) 
	 * 			| 				then this.loseHitpoints(Slime.getLossHitpointsInMagma());
	 * 			| 				then this.setTimeInWater(this.getTimeInWater() - GameObject.getDrownTime());
	 * 			| 	then 	this.setTimeInMagma(this.getTimeInMagma() + dt);
	 * @effect if this Slime is in contact with water longer than the drownTime, it loses hitpoints,
	 * 			else timeInWater is set to zero
	 * 			| if (this.isInContactWithFeature(newXPos,newYPos,2)) 
	 * 			| 	then setTimeInWater(this.getTimeInWater() + dt)
	 * 			| 		 if (this.getTimeInWater() >= Slime.getDrownTime())
	 * 			| 			then loseHitpoints(Slime.getLossHitpointsInWater())
	 * 			| 				 setTimeInWater(this.getTimeInWater() - GameObject.getDrownTime())
	 * 			| else
	 * 			| 	then setTimeInWater(0)
	 */
	@Raw
	private void loseHitpointsBecauseOfFeature(double dt, double newXPos, double newYPos) {
		if (this.isInContactWithFeature(this.getXPos(), this.getYPos(), 3)) {
			if (this.getTimeInMagma() == 0) {
				this.loseHitpoints(Slime.getLossHitpointsInMagma());
			}		
			else if (this.getTimeInMagma() >= Slime.getBurnTime()) {
				this.loseHitpoints(Slime.getLossHitpointsInMagma());
				this.setTimeInWater(this.getTimeInWater() - GameObject.getDrownTime());
			}
			this.setTimeInMagma(this.getTimeInMagma() + dt);
		}
		else {
			this.setTimeInMagma(0);
		}
		if (this.isInContactWithFeature(newXPos,newYPos,2)) {
			this.setTimeInWater(this.getTimeInWater() + dt);
			if (this.getTimeInWater() >= Slime.getDrownTime()) {
				this.loseHitpoints(Slime.getLossHitpointsInWater());
				this.setTimeInWater(this.getTimeInWater() - GameObject.getDrownTime());
			}			
		}
		else {
			this.setTimeInWater(0);
		}
	}
	
	/**
	 * checks if this Slime is within the boundaries of the game world, if not
	 * the Slime is removed
	 * @param newXPos
	 * 			the new horizontal position of the Slime
	 * @param newYPos
	 * 			the new vertical position of the Slime
	 * @effect the Slime gets removed if not within the boundaries
	 * 			| if ( ! isWithinBoundaries(newXPos, newYPos))
	 * 			| 	then remove()
	 */
	private void checkIfWithinBoundaries(double newXPos, double newYPos) {
		if ( ! isWithinBoundaries(newXPos, newYPos)) {
			this.remove();
		}
	}
	
	/**
	 * advances time when this Slime is still alive, the positions are adapted, 
	 * hitpoints and immunity are updated and the Slime dies if hitpoints reach zero
	 * @param dt
	 * 			a small time interval
	 * @effect this Slime moves randomly
	 * 			| randomMovement(dt)
	 * @effect the new position of the Slime is calculated, this new position also gets
	 * 			checked if within the boundaries of the game world, then the surroundings 
	 * 			of the newly calculated position and the presence of other gameObjects is 
	 * 			checked and the new position/speed is adjusted accordingly and at last the 
	 * 			horizontal and vertical position of this Slime are set to the new positions
	 * 			| let 
	 * 			| 	double[] newPos = this.calculateNewPos(dt);
	 * 			| in
	 * 			|	checkInWithinBoundaries(newPos[0],newPos[1])
	 * 			| 	newPos = checkSurroundings(newPos[0],newPos[1])
	 * 			| 	newPos = collidingSharksSlimesMazub(newPos[0],newPos[1], dt)
	 *			| 	setXPos(newPos[0])
	 * 			| 	setYPos(newPos[1])
	 * @effect calculate and set the new speed of the slime
	 * 			| setNewSpeed(dt);
	 * @effect this Slime loses hitpoints when drowning of burning in magma, the immunity also
	 * 			gets updated
	 * 			| loseHitpointsBecauseOfFeature(dt, getXPos(), getYPos())
	 * 			| updateImmunity(dt);
	 * @effect the Slime dies if no longer possesses hitpoints
	 */
	@Raw
	private void advanceTimeWhileLiving(double dt) {
		this.randomMovement(dt);			
		double[] newPos = this.calculateNewPos(dt);

		this.checkIfWithinBoundaries(newPos[0],newPos[1]);
				
		newPos = checkSurroundings(newPos[0],newPos[1]);
		newPos = collidingSharksSlimesMazub(newPos[0],newPos[1], dt);
		
		this.setXPos(newPos[0]);
		this.setYPos(newPos[1]);
		
		this.setNewSpeed(dt);
		
		this.loseHitpointsBecauseOfFeature(dt, this.getXPos(), this.getYPos());			
		this.updateImmunity(dt);
		
		if (this.getHitpoints() <= 0) {
			this.die();
		}
	}
	
	/**
	 * advances the time while the Slime is dying
	 * @param dt
	 * 			a small time interval
	 * @effect the timeSinceDeath is updated
	 * 			| setTimeSinceDeazth(getTimeSinceDeath() + dt)
	 * @effect if the Slime slime is dying long enough, it gets removed from the world
	 * 			| if (this.getTimeSinceDeath() >= GameObject.getTimeUntilRemove()) 
	 * 			| 	then remove()
	 */
	@Raw
	private void advanceTimeWhileDeath(double dt) {
		this.setTimeSinceDeath(this.getTimeSinceDeath() + dt);
		if (this.getTimeSinceDeath() >= GameObject.getTimeUntilRemove()) {
			this.remove();
		}
	}
	
	/**
	 * advances the time with dt seconds
	 * @param dt
	 * 			a small time interval
	 * @throws IllegalDtException
	 * 			the given dt is not valid 
	 * 			| ! isValidDt(dt)	 
	 * @effect advances the time if the Slime is still alive or dying
	 * 			| if ( ! isDying())
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
	 * removes this Slime from the gameWorld and it's school
	 * @effect this is removed from it's school
	 * 			| school.removeSlime(this)
	 * 			| setSchool(null)
	 * @effect this is removed from the world
	 * 			| world.removeSlime(this)
	 * 			| setWorld(null) 			
	 */
	public void remove() {
		this.getSchool().removeSlime(this);
		this.setSchool(null);
		this.getWorld().removeSlime(this);
		this.setWorld(null);		
	}
	
	/**
	 * moves this Slime in a random way
	 * @param dt
	 * 			a small time interval
	 * @effect if the time since last moving is greater than the duration of the movement
	 * 			the Slime stops moving, the movementDuration is set randomly, the time since
	 * 			moving is set to dt and the random movement starts
	 * 			otherwise, the timeSinceMove is updated
	 * 			| if (this.getTimeSinceMove() >= this.getMovementDuration()) 
	 * 			| 	then stopMove()
	 * 			| 		 setMovementDuration(Math.random() * 4 + 2)
	 * 			| 		 setTimeSinceMove(dt)
	 * 			| 		 if (Math.random() >= 0.5) 
	 * 			| 			then startMoveRight()
	 * 			| 		 else 
	 * 			| 			then startMoveLeft()
	 *			| else 
	 *			| 	then setTimeSinceMove(this.getTimeSinceMove() + dt)
	 */
	@Raw
	private void randomMovement(double dt) {
		if (this.getTimeSinceMove() >= this.getMovementDuration()) {
			this.stopMove();
			this.setMovementDuration(Math.random() * 4 + 2);
			this.setTimeSinceMove(dt);
			if (Math.random() >= 0.5) {
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
	 * The Slime starts moving to the right
	 * @effect if not moving to the left, the horizontal speed is set to zero
	 * 			and the orientation is set to RIGHT
	 * 			| if (this.getOrientation() == Orientation.LEFT) 
	 * 			| 	then setXSpeed(0)
	 * 			| 		 setOrientationRight()
	 */
	public void startMoveRight() {
		if (this.getOrientation() == Orientation.LEFT) {
			this.setXSpeed(0);
			this.setOrientationRight();
		}
	}
	
	/**
	 * The Slime starts moving to the left
	 * @effect if not moving to the right, the horizontal speed is set to zero
	 * 			and the orientation is set to LEFT
	 * 			| if (this.getOrientation() == Orientation.RIGHT) 
	 * 			| 	then setXSpeed(0)
	 * 			| 		 setOrientationLEFT()
	 */
	public void startMoveLeft() {
		if (this.getOrientation() == Orientation.RIGHT) {
			this.setXSpeed(0);
			this.setOrientationLeft();
		}
		this.setOrientationLeft();
	}	
	
	/**
	 * the horizontal speed of the Slime is stopped
	 * @effect the horizontal speed is set to zero
	 * 			| setXSpeed(0)
	 */
	private void stopMove() {
		this.setXSpeed(0);
	}
	
	/**
	 * this Slime loses the given amount of hitpoints, all the other
	 * members of the same school are also affected by deducting 1 hitpoint
	 * @effect the given number of hitpoints is deducted from this Slime
	 * 			| setHitpoints(getHitpoints() - nb)
	 * @effect every other Slime in the school is affected
	 * 			| for each Slime other in school.getMembers():
	 * 			| 	if (other != this)
	 * 			| 		then other.setHitpoints(other.getHitpoints() - Slime.getSchoolDamga())
	 */
	@Override @Raw
	public void loseHitpoints(int nb) {
		this.setHitpoints(this.getHitpoints() - nb);
		for (Slime other: this.getSchool().getMembers()) {
			if (other != this) {
				other.setHitpoints(other.getHitpoints() - Slime.getSchoolDamage());
			}
		}
	}

}
	
