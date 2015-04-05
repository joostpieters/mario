package jumpingalien.model;


import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
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
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for the plant
	 * 			| !isValidPosition(x_pos,y_pos)
	 * @throws IllegalSpriteException
	 * 			The given sprite is not valid
	 * 			| !isValidSprite(sprites) 
	 */
	public Plant(int xPos,int yPos, Sprite[] sprites ) 
			throws IllegalPositionException, IllegalSpriteException {
		super(xPos,yPos,sprites);
	}
	

	/**
	 * the initial amount of hitpoints a plant possesses
	 */	
	private int INIT_HITPOINTS = 1;
	/**
	 * every 0.5 seconds the orientation of the plant changes
	 */
	private double TIME_CHANGE_ORIENTATION = 0.5;	
	/**
	 * the time a plant is moving in 1 direction
	 */
	private double timeSameOrientation = 0;

	private double REMAINING_TIME = 0.6;
	
	private double timeSinceDeath = 0;
	
	private int MAX_HIT_POINTS;
	/**
	 * the maximum amount of hitpoints
	 * @returnMAXHITPOINTS
	 */
	private int getMaxHitpoints() {
		return MAX_HIT_POINTS;
	}

// GETTERS
	
	
	/**
	 * 
	 */
	private double getTimeSameOrientation() {
		return timeSameOrientation;
	}
	public void setNbHitpoints(int number) {
		if ( ! (number > this.getMaxHitpoints())) {
			this.hitpoints = number;
		}		
	}	
	private int getInitHitpoints() {
		return INIT_HITPOINTS;
	}
	private double getTimeChangeOrientation() {
		return TIME_CHANGE_ORIENTATION;
	}

	
	private double getRemainingTime() {
		return this.REMAINING_TIME;
	}
	

//	SETTERS
	
	
	
	/**
	 * 
	 */
	private void setTimeSameOrientation(double t) {
		this.timeSameOrientation = t;
	}
	
	
// VALIDATIONS
	@Override
	protected boolean isValidSprite(Sprite[] sprites) {
		return sprites.length == 2;
	}
	

	public void advanceTime(double dt) {
		
		this.setTimeSameOrientation(this.getTimeSameOrientation() + dt);
		
		if (Position.getOrientation() == Orientation.RIGHT) {
			this.setNewXPos(Position.getXPos() + this.getXSpeed()*100*dt);	
		}
		else if (Position.getOrientation() == Orientation.RIGHT) {
			this.setNewXPos(Position.getXPos() - this.getXSpeed()*100*dt);
		}
		
		if ((this.getNewXPos() < this.getMinXValue()) || 
				(this.getNewXPos() > this.getMaxXValue())){
			this.remove();
		}
		
		Position.setXPos(this.getNewXPos());
		
		if (this.getTimeSameOrientation() > this.getTimeChangeOrientation()) {
			this.changeOrientation();
			this.setTimeSameOrientation(this.getTimeSameOrientation()
					- this.getTimeChangeOrientation());
		}		
		
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
		this.getWorld().removePlant(this);
		this.setWorld(null);
	}
	

	
	/**
	 * Return the current sprite image for the given plant.
	 * 
	 * @return The current sprite image for the given plant, determined by its
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
