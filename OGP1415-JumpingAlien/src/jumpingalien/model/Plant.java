package jumpingalien.model;


import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.model.exceptions.IllegalDtException;
import jumpingalien.model.exceptions.IllegalPositionException;
import jumpingalien.model.exceptions.IllegalSpriteException;
import jumpingalien.util.Sprite;

public class Plant extends GameObject {
	
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
	 * @effect the plant is created at the given position with the given sprites
	 * 			| super(xPos, yPos, sprites)
	 * @effect the horizontal speed is set and the hitpoints are set to the default value
	 * 			| new.setXSpeed(0.5)
	 * 			| new.setHitpoitns(Plant.getInitHitpoints())
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for the plant
	 * 			| ! isValidPosition(xPos,yPos)
	 * @throws IllegalSpriteException
	 * 			The given sprite is not valid
	 * 			| ! isValidSprite(sprites) 
	 */
	@Raw
	public Plant(int xPos,int yPos, Sprite[] sprites ) 
			throws IllegalPositionException, IllegalSpriteException {
		super(xPos,yPos,sprites);
		this.setXSpeed(0.5);
		this.setHitpoints(Plant.getInitHitpoints());
	}
	
	public Plant(int xPos,int yPos, Sprite[] sprites, Program program ) 
			throws IllegalPositionException, IllegalSpriteException {
		super(xPos,yPos,sprites, program);
		this.setXSpeed(0.5);
		this.setHitpoints(Plant.getInitHitpoints());
	}

	/**
	 * the initial amount of hitpoints a plant possesses
	 */	
	private static final int INIT_HITPOINTS = 1;
	/**
	 * returns the initial amount of hitpoints of a plant
	 * @return INIT_HITPOINTS
	 */
	@Basic @Immutable 
	private static final int getInitHitpoints() {
		return INIT_HITPOINTS;
	}
	/**
	 * every 0.5 seconds the orientation of the plant changes
	 */
	private static final double TIME_CHANGE_ORIENTATION = 0.5;	
	/**
	 * the time a plant is moving in 1 direction
	 */
	private double timeSameOrientation = 0;
	/**
	 * the maximum amount of hitpoints a plant can possess
	 */
	private static final int MAX_HITPOINTS = 1;	
	/**
	 * the maximum amount of hitpoints
	 * @return MAXHITPOINTS
	 */
	@Basic @Immutable 
	protected final int getMaxHitpoints() {
		return Plant.MAX_HITPOINTS;
	}

// GETTERS	
	
	/**
	 * returns the total amount of seconds a plant has the
	 * same orientation
	 * @return timeSameOrientation
	 */
	@Basic @Raw
	private double getTimeSameOrientation() {
		return timeSameOrientation;
	}
	/**
	 * returns the amount of seconds a plant has to move with the
	 * same orientation
	 * @return TIME_CHANGE_ORIENTATION
	 */
	@Basic @Immutable 
	private static final double getTimeChangeOrientation() {
		return TIME_CHANGE_ORIENTATION;
	}
	
//	SETTERS	
	
	/**
	 * sets the double timeSameOrientation to the given value
	 * @param t
	 * 			the new value
	 * @pre t should always be bigger than or equal to zero
	 * 		| t >= 0 
	 * @post timeSameOrientation = t
	 */
	@Raw
	private void setTimeSameOrientation(double t) {
		assert t >= 0;
		this.timeSameOrientation = t;
	}	
	
// VALIDATIONS
	
	/**
	 * returns true if the sprites are valid
	 * @param sprites
	 * 			the sprites
	 * @return sprits.length == 2
	 */
	@Override @Raw
	protected boolean isValidSprite(Sprite[] sprites) {
		return sprites.length == 2;
	}	
	
	/**
	 * checks if a new position of the plant is within the boundaries of the
	 * game world and removes the plant if this is not the case
	 * @param newXPos
	 * @param newYPos
	 * @effect removes plant if the new position is not within the boundaries
	 * 			| if ( ! isWithinBoundaries(newXPos, newYPos) 
	 * 			| 	then remove()
	 */
	private void checkIfWithinBoundaries(double newXPos, double newYPos) {
		if ( ! isWithinBoundaries(newXPos,newYPos)) {
			this.remove();
		}
	}
	
	/**
	 * Advanced the time with dt seconds, the new horizontal and vertical 
	 * positions are calculated and updated
	 * 
	 * @param dt: the amount of seconds the time has to be advanced
	 * @throws IllegalDtException
	 * 			the given dt is not valid 
	 * 			| ! isValidDt(dt) 
	 * @effect timeSameOrientation is updated
	 * 			| setTimeSameOrientation(getTimeSameOrientation() + dt)
	 * @effect the new horizontal position newXPos is calculated
	 * 			| if ((getOrientation() == Orientation.RIGHT) &&
	 * 			| 		( ! againstRightWall(getXPos(), getYPos())))
	 * 			| 	then newXPos = (getXPos() + getXSpeed() * 100 * dt)
	 * 			| else if ((this.getOrientation() == Orientation.LEFT) &&
	 * 			| 		( ! againstLeftWall(getXPos(), getYPos())))
	 * 			| 	then newXPos = newXPos = (getXPos() - getXSpeed() * 100 * dt)
	 * @effect checks if the new position of the plant is within the game world and removes
	 * 			the plant is this is not the case
	 * 			| checkIfWithinBoundaries(newXPos, getYPos())
	 * @effect updates the position of the plant
	 * 			| setXPos(newXPos)
	 * @effect changes the orientation if the time is right and updates
	 * 			timeSameOrientation
	 * 			| if (getTimeSameOrientation() > getTimeChangeOrientation())
	 * 			| 	then changeOrientation()
	 * 			|		 setTimeSameOrientation(getTimeSameOrientation() - Plant.getTimeChangeOrientation())
	 * @effect removes the plant if the plant is already dying long enough or sets the plant dying
	 * 			| if (isDying())
	 * 			| 	then setTimeSinceDeath(getTimeSinceDeath() + dt)
	 * 			| 		 if (getTimeSincedeath() >= GameObject.getTimeUntilRemove())
	 * 			| 			then this.remove();
	 * 			| else if (getHitpoints() <= 0)
	 * 			| 	then die()
	 */
	@Raw
	public void advanceTime(double dt)throws IllegalDtException {
		if ( ! isValidDt(dt))
			throw new IllegalDtException(dt);	
		
		double newXPos = this.getXPos();		
		this.setTimeSameOrientation(this.getTimeSameOrientation() + dt);
		
		if ((this.getOrientation() == Orientation.RIGHT) &&
				( ! this.againstRightWall(this.getXPos(), this.getYPos()))) {
				newXPos = (this.getXPos() + this.getXSpeed() * 100 * dt);
		}
		else if ((this.getOrientation() == Orientation.LEFT) &&
				( ! this.againstLeftWall(this.getXPos(), this.getYPos()))) {
				newXPos = (this.getXPos() - this.getXSpeed() * 100 * dt);
		}
		
		this.checkIfWithinBoundaries(newXPos, this.getYPos());		
		this.setXPos(newXPos);
		
		if (this.getTimeSameOrientation() > Plant.getTimeChangeOrientation()) {
			this.changeOrientation();
			this.setTimeSameOrientation(this.getTimeSameOrientation()
					- Plant.getTimeChangeOrientation());
		}		
		if (this.isDying()) {
			this.setTimeSinceDeath(this.getTimeSinceDeath() + dt);
			if (this.getTimeSinceDeath() >= GameObject.getTimeUntilRemove()) {
				this.remove();
			}
		}
		else if (this.getHitpoints() <= 0) {
			this.die();
		}
	}
	
	/**
	 * removes the plant from the game world
	 * @effect the plant gets removed
	 * 			| world.removePlant(this)
	 * 			| setWorld(null)
	 */
	private void remove() {
		this.getWorld().removePlant(this);
		this.setWorld(null);
	}
	
}
