package jumpingalien.model;

import java.util.ArrayList;
import java.util.List;

import program.Program;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.model.exceptions.IllegalDtException;
import jumpingalien.model.exceptions.IllegalPositionException;
import jumpingalien.model.exceptions.IllegalSpeedException;
import jumpingalien.model.exceptions.IllegalSpriteException;
import jumpingalien.util.Sprite;

/**
 * A class that describes the movement of Mazub's evil twin Buzam
 * @author Ward Romanus, Pieter Van den Berghe
 *
 */
public class Buzam extends Mazub {	
	
	/**
	 * Initialize this new Buzam with given x and y positions and given sprites.
	 * 
	 * @param xPos
	 * 			The x position in the field for the new Buzam
	 * @param yPos
	 * 			The y position in the field for the new Buzam
	 * @param sprites
	 * 			The sprites for the new Buzam
	 * @effect the Buzam is created at (xPos,yPos) with a given set of sprites
	 * 			| super(xPos, yPos, sprites)
	 * @effect the initial startspeed, maximum speed and hitpoints are
	 * 			set to the default values
	 * 			| new.setInitStartSpeed(START_SPEED)
	 * 			| new.setMaxSpeed(MAX_MOVING_SPEED)
	 * 			| new.setHitpoints(Buzam.getInitHitpoints())
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for Buzam
	 * 			| ! isValidPosition(xPos,yPos)
	 * @throws IllegalSpriteException
	 * 			The given sprite is not valid
	 * 			| ! isValidSprite(sprites) 
	 * 
	 */
	public Buzam(int xPos, int yPos, Sprite[] sprites)
			throws IllegalPositionException, IllegalSpriteException {
		super(xPos, yPos, sprites);
		this.setInitStartSpeed(Mazub.getStartSpeed());
		this.setMaxSpeed(Mazub.getMaxMovingSpeed());
		this.setHitpoints(Buzam.getInitHitpoints());
	}
	
	/**
	 * Initialize this new Buzam with given x and y positions, sprites,
	 * horizontal start speed and maximum horizontal speed.
	 * 
	 * @param xPos
	 * 			The x position in the field for the new Buzam
	 * @param yPos
	 * 			The y position in the field for the new Buzam
	 * @param sprites
	 * 			The sprites for the new Buzam
	 * @param initStartSpeed
	 *			The initial horizontal start speed
	 * @param maxSpeed
	 * 			The maximum horizontal speed 
	 * @effect the Buzam is created at (x_pos,y_pos) with a given set of sprites
	 * 			| super(xPos, yPos, sprites)
	 * @effect the initial startspeed and maximum speed are
	 * 			set to the given values and the hitpoints are 
	 * 			set to the default value
	 * 			| new.setInitStartSpeed(initStartSpeed)
	 * 			| new.setMaxSpeed(maxSpeed)
	 * 			| new.setHitpoints(Buzam.getInitHitpoints())
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for Buzam
	 * 			| ! isValidPosition(xPos,yPos)
	 * @throws IllegalSpriteException
	 * 			The given sprite is not valid
	 * 			| ! isValidSprite(sprites) 
	 * @throws IllegalSpeedException
	 * 			The given maxSpeed and initalStartSpeed are not valid
	 * 			| ! isValidSpeed(initStartSpeed, maxSpeed)
	 * The initial velocity will never be changed below 1 m/s so
	 * we don't need an IllegalInitStartSpeedException or a
	 * IllegalMaxSpeedException. 
	 * 
	 */	
	public Buzam(int xPos, int yPos, Sprite[] sprites, Program program)
			throws IllegalPositionException, IllegalSpriteException {
		super(xPos, yPos, sprites);
		this.setProgram(program);
		this.setInitStartSpeed(Mazub.getStartSpeed());
		this.setMaxSpeed(Mazub.getMaxMovingSpeed());
		this.setHitpoints(Buzam.getInitHitpoints());
	}
	
	
	/**
	 * the default amount of hitpoints buzam possesses in the beginning of a game
	 */
	private static final int INIT_HITPOINTS = 500;
	/**
	 * the initial amount of hitpoints
	 * @return INITHITPOINTS
	 */
	@Immutable
	protected
	static int getInitHitpoints() {
		return INIT_HITPOINTS;
	}	
	
	@Override
	@Raw
	public void advanceTime(double dt) throws IllegalDtException {
		System.out.println(this.getHitpoints());
		if ( ! isValidDt(dt))
			throw new IllegalDtException(dt);		
		if ( ! this.isDying()) {		
			this.changeTimeDependents(dt);
			
			if (this.getDuckShouldEnd()) {
				this.setDuckShouldEnd(false);
				this.endDuck();
			}
			
			this.loseHitpointsBecauseOfFeature(dt, this.getXPos(), this.getYPos());
			this.updateImmunity(dt);		
			
			if (this.getHitpoints() <= 0) {
				this.die();
			}
		}
		else {
				this.setTimeSinceDeath(this.getTimeSinceDeath() + dt);
				if (this.getTimeSinceDeath() > GameObject.getTimeUntilRemove()) {
					 this.remove();
			}
		}
	}
	
	/**
	 * 
	 * @param newXPos
	 * 			the newly calculated horizontal position
	 * @param newYPos
	 * 			the newly calculated vertical position
	 * @param dt
	 * 			the time interval
	 * @effect the new position of Bazum is calculated and a possible collision with
	 * 			a plant gets checked
	 * 			| let
	 * 			| 	newPos = collidingSlimesSharksMazub(newXPos, newYPos, dt)
	 * 			| in
	 * 			| collidingPlants(newXPos, newYPos)
	 * @return the adapted horizontal and vertical position of Buzam
	 * 			| {newPos[0], newPos[1]}
	 */
	@Raw @Override
	public double[] colliding(double newXPos, double newYPos, double dt) {	
		double[] newPos = this.collidingSlimesSharksMazub(newXPos, newYPos, dt);
		this.collidingPlants(newXPos, newYPos);
		return new double[] {newPos[0], newPos[1]};
	}
	
	/**
	 * adapts the position taking all Slimes, Sharks and Mazub into account, it also updates the hitpoints
	 * @param newXPos
	 * 			the newly calculated horizontal position
	 * @param newYPos
	 * 			the newly calculated vertical position
	 * @param dt
	 * 			the time interval
	 * @effect the list allSlimesSharksMazub contains all the slimes, sharks and the Mazub alien in the world
	 * 			| allSlimesSharksMazub = world.getSlimes()
	 * 			| allSlimesSharksMazub.addAll(world.getSharks())
	 * 			| allSlimesSharksMazub.add(world.getAlien())
	 * @effect calculates the new position of buzam taking all the slimes and sharks
	 * 			in the world into account
	 * 			| boolean onGameObject = false;
	 * 			| double[] newPos = {newXPos, newYPos}; 
	 * 			| for each other: allSlimesSharksMazub:
	 * 			| 	let 
	 * 			| 		double xDim1 = getXDim()
	 * 			|		double yDim1 = getYDim()
	 * 			| 		double x2 = other.getXPos()
	 * 			| 		double xDim2 = other.getXDim()
	 * 			| 		double y2 = other.getYPos()
	 * 			|		double yDim2 = other.getYPos()
	 * 			| 		boolean touched = false
	 * 			| 	in
	 * 			| 	newPos = collidesSomeWhere(newXpos, xDim1, newYPos, yDim1, x2, xDim2, y2)
	 * @effect the hitpoints of buzam and the sharks/slimes are updated taking the collisions into account
	 * 			| 	if newPos[3] == 1
	 * 			| 		then onGameObject = true
	 * 			| 	if newPos[2] == 1 && ( ! other.isDying()) 
	 * 			| 		then if ( ! other.collidesAbove(newPos[0], xDim1, newPos[1], yDim1,
	 * 			|						 x2, xDim2, y2, yDim2)) {
	 * 			|				other.contactDamage(dt)
	 * 			|			 if ( ! this.collidesUnder(newPos[0], xDim1, newPos[1], yDim1,
	 * 			|						 x2, xDim2, y2, yDim2)) {
	 * 			|				this.contactDamage(dt)
	 * @effect if Buzam is on top of another game object, his fall is ended, else Buzam falls
	 * 			| if (isFalling() && onGameObject())
	 * 			| 	then endFall()
	 * 			| else if ( ! isFalling() && ( ! onGameObject) && ( ! onFloor(newPos[0], newPos[1])
	 * 			| 	then fall()
	 * @return {newPos[0], newPos[1]}
	 */
	@Raw
	private double[] collidingSlimesSharksMazub(double newXPos, double newYPos, double dt) {
		List<GameObject> allSlimesSharksMazub =  new ArrayList<GameObject>(this.getWorld().getSlimes());
		allSlimesSharksMazub.addAll(this.getWorld().getSharks());
		allSlimesSharksMazub.add(this.getWorld().getAlien());
		boolean onGameObject = false;
		double[] newPos = {newXPos, newYPos};
		for(GameObject other: allSlimesSharksMazub) {
			double xDim1 = this.getXDim();
			double yDim1 = this.getYDim();
			double x2 = other.getXPos();
			double xDim2 = other.getXDim();
			double y2 = other.getYPos();
			double yDim2 = other.getYDim();
			newPos = collidesSomewhere(newXPos, xDim1, newYPos, yDim1, x2, xDim2, y2, yDim2);
			if (newPos[3] == 1) {
				onGameObject = true;
			}
			if ( newPos[2] == 1 && ( ! other.isDying())) {
				if ( ! other.collidesAbove(newPos[0], xDim1, newPos[1], yDim1, x2, xDim2, y2, yDim2)) {
					other.contactDamage(dt);
				}
				if ( ! this.collidesUnder(newPos[0], xDim1, newPos[1], yDim1, x2, xDim2, y2, yDim2)) {
					this.contactDamage(dt);
				}	
			}
		}
		if (this.isFalling() && onGameObject)  {
			this.endFall();
		}
		else if ( ! this.isFalling() && ( ! onGameObject) && ( ! this.onFloor(newPos[0],newPos[1]))) {
			fall();
		}
		return new double[] {newPos[0], newPos[1]};
	}
	
	/**
	 * removes Buzam from the game world
	 * @effect buzam gets removed
	 * 			| world.removeBuzam(this)
	 * 			| setWorld(null)
	 */
	@Raw
	protected void remove() {
		// TODO de sprite gaat niet weg, dit is heel cheap maar werkt wel :p 
		// ==> betere oplossing zoeken, volgens mij ligt het aan het feit dat
		// buzam een instanceof Mazub is en de sprite van mazub gaat ook niet 
		// weg als die dood is
//		this.setXPos(-500);
//		this.setYPos(-500);
		this.getWorld().removeBuzam(this);
		this.setWorld(null);
		
	}
	
}
