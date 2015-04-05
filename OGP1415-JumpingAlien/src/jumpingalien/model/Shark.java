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
		super(xPos,yPos,sprites);
		this.setNbHitpoints(INIT_HIT_POINTS);
	}
	

	public void setNbHitpoints(int number) {
		if ( ! (number > this.getMaxHitpoints())) {
			this.hitpoints = number;
		}		
	}	
	/**
	 * the initial amount of hitpoints a shark possesses
	 */
	private static int INIT_HIT_POINTS = 100;
	private static int getInitHitpoints() {
		return INIT_HIT_POINTS;
	}
	
	private int MAX_HIT_POINTS;
	/**
	 * the maximum amount of hitpoints
	 * @returnMAXHITPOINTS
	 */
	private int getMaxHitpoints() {
		return MAX_HIT_POINTS;
	}
	
	/**
	 * the maximum horizontal speed a shark can reach
	 */
	private static double MAX_X_SPEED = 4;
	private static double getMaxXSpeed() {
		return  MAX_X_SPEED;
	}
	/**
	 * the vertical speed at which the shark moves when 
	 * startJump() is initiated
	 */
	private static int JUMP_SPEED = 2;
	private static double getJumpSpeed() {
		return JUMP_SPEED;
	}
	/**
	 * the vertical acceleration at which mazub falls
	 */
	private static double FALL_ACC = -10;
	/**
	 * Returns the acceleration when the shark falls
	 * @return FALL_ACC
	 */
	@Basic @Immutable @Raw 
	private double getFallAcc() {
		return FALL_ACC;
	}
	/**
	 * the minimal duration of a movement period (1s)
	 */
	private static int MIN_MOVEMENT_DURATION = 1;
	private static int getMinMovementDuration() {
		return MIN_MOVEMENT_DURATION;
	}	
	/**
	 * the maximal duration of a movement period (4s)
	 */
	private static int MAX_MOVEMENT_DURATION = 4;
	private static int getMaxMovementDuration() {
		return MAX_MOVEMENT_DURATION;
	}
	/**
	 * the amount of hitpoints a shark loses when touching 
	 * a mazub or a slime
	 */
	private int CONTACT_DAMAGE = 50;
	
	private int HIT_POINTS = 1;

	
	private double REMAINING_TIME = 0.6;
	private double getRemainingTime() {
		return this.REMAINING_TIME;
	}
	
	
	
	
	
	/**
	 * The boolean to reflect or shark is falling or not
	 */
	private boolean falling = false;	
	
	private boolean isFalling() {
		return falling;
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
	@Override
	protected boolean isValidSprite(Sprite[] sprites) {
		return sprites.length == 2;
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
			//TODO nog toepassen op shark, maar ik wil eerst beeld
			if (this.onFloor(newXPos,newYPos) && this.isFalling()) {
				this.endFall();
				newYPos = ((this.getTilesUnder(newXPos,newYPos)[0][1] +1) * getWorld().getTileLength() -1);
			}
			
			if (( ! onFloor(newXPos,newYPos)) && ( ! this.isFalling())){
				fall();
			}
			
			return new double[] {newXPos, newYPos};
		}
	
	
	
	public void advanceTime(double dt) throws IllegalDtException {
		if ( ! isValidDt(dt))
			throw new IllegalDtException(dt);

		//TODO dis is mss nogal inefficient, waarom?, omdat 2 keer newpos wordt uitgerekend
		double newXPos = this.calculateNewPos(dt)[0];
		double newYPos = this.calculateNewPos(dt)[1];
		
		if( ! isWithinBoundaries(newXPos,newYPos)) {
			this.remove();
		}
		
		double[] newPos = checkSurroundings(newXPos,newYPos);
		
		this.setNewSpeed(dt);
		
		this.setXPos(newPos[0]);
		this.setYPos(newPos[1]);
		
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

	
}
