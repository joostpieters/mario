package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.util.Sprite;

public class Slime {
	
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
	public Slime(int x_pos,int y_pos, Sprite[] sprites,School school) 
			throws IllegalPositionException, IllegalSpriteException {
				if(!isValidPosition(x_pos,y_pos))
					throw new IllegalPositionException(x_pos,y_pos); 
				if (!isValidSprite(sprites))
					throw new IllegalSpriteException(sprites);
		this.setXPos(x_pos);
		this.setYPos(y_pos);
		this.setSprite(sprites);
		this.setSchool(school);
	}
	

	private int x_pos;
	
	private int y_pos;
	
	private Sprite[] sprites;
	
	/**
	 * the orientation of the slime
	 */
	private Orientation orientation  = Orientation.LEFT;
	/**
	 * the minimal value for x_pos
	 */
	private static int MIN_X_VALUE = 0;
	/**
	 * the maximal value for x_pos
	 */
	private static int MAX_X_VALUE = 1023;
	/**
	 * the minimal value for y_pos
	 */
	private static int MIN_Y_VALUE = 0;
	/**
	 * the maximal value for y_pos
	 */
	private static int MAX_Y_VALUE = 767;
	
	private int movementDuration;

	private int INIT_HITPOINTS = 100;
	
	/**
	 * the horizontal acceleration of a slime
	 */
	private double xAcc = 0.7;
	/**
	 * the maximum horizontal speed a slime can reach
	 */
	private double MAX_X_SPEED = 2.5;
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
	private int CONTACT_DAMAGE = 50;
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
	
	private World world;
	
	private School school;
	
//	GETTERS	
	/**
	 * @return the x
	 */
	private int getXPos() {
		return x_pos;
	}

	/**
	 * @return the y
	 */
	private int getYPos() {
		return y_pos;
	}

	/**
	 * @return the sprites
	 */
	private Sprite[] getSprite() {
		return sprites;
	}
	private int getIinitHitpoints() {
		return INIT_HITPOINTS;
	}
	private double getXAcc() {
		return xAcc;
	}
	public World getWorld() {
		return this.world;
	}
	private double getMaxXSpeed() {
		return  MAX_X_SPEED;
	}
	private Orientation getOrientation() {
		return orientation;
	}
	/**
	 * returns the minimal value of x_pos
	 * @return MIN_X_VALUE
	 */
	@Basic @Immutable @Raw 
	private static int getMinXValue() {
		return MIN_X_VALUE;
	}
	/**
	 * returns the maximal value of x_pos
	 * @return MAX_X_VALUE
	 */
	@Basic @Immutable @Raw 
	private static int getMaxXValue() {
		return MAX_X_VALUE;
	}
	/**
	 * returns the minimal value of y_pos
	 * @return MIN_Y_VALUE
	 */
	@Basic @Immutable @Raw 
	private static int getMinYValue() {
		return MIN_Y_VALUE;
	}/**
	 * returns the maximal value of y_pos
	 * @return MAX_Y_VALUE
	 */
	@Basic @Immutable @Raw 
	private static int getMaxYValue() {
		return MAX_Y_VALUE;
	}
	/**
	 * Returns the current location of the given slime.
	 * 
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given slime's bottom left pixel in the world.
	 */
	public int[] getLocation(){
		return new int[]{this.getXPos(),this.getYPos()};
	}
	
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

	private int getMovementDuration() {
		return movementDuration;
	}
	private int getContactDamage() {
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
	

//	SETTERS
	/**
	 * @param x the x to set
	 */
	private void setXPos(int x) {
		this.x_pos = x;
	}

	/**
	 * @param y the y to set
	 */
	private void setYPos(int y) {
		this.y_pos = y;
	}
	
	/**
	 * @param sprites the sprites to set
	 */
	private void setSprite(Sprite[] sprites) {
		this.sprites = sprites;
	}
	/**
	 * Sets the orientation of the slime to right
	 * @post orientation == "right"
	 */
	@Raw 
	private void setOrientationRight() {
		this.orientation = Orientation.RIGHT;
	}
	/**
	 * Sets the orientation of the slime to left
	 * @post orientation == "left"
	 */
	@Raw 
	private void setOrientationLeft() {
		this.orientation =Orientation.LEFT;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	private void setSchool(School school) {
		this.school = school;
	}
	
//	VALIDATIONS
	
	private boolean isValidSprite(Sprite[] sprites) {
		return sprites.length == 2;
	}
	
	/**
	 * 	Checks whether the given positions are valid positions for 
	 *  any slime
	 * @return 	True if the horizontal position x_pos and 
	 *			and the vertical position y_pos stay in the 
	 *			game world.
	 *			| ((x_pos >= MIN_X_VALUE && x_pos <= MAX_X_VALUE
	 *				&& y_pos >= MIN_Y_VALUE && y_pos <= MAX_Y_VALUE))
	 */
	public boolean isValidPosition(int x_pos, int y_pos) {
		return ((x_pos >= Slime.getMinXValue())
				&& (x_pos <= Slime.getMaxXValue())
				 && (y_pos >= Slime.getMinYValue())
				 && (y_pos <= Slime.getMaxYValue()));
	}
	
	/**
	 * starts the action period for an object
	 */
//	public void start<action> {
//		
//	}
	
	/**
	 * ends the action period for an object
	 */
//	public void stop<action> {
//		
//	}
	
	
	public void advanceTime(double dt) {
		if (this.getNbHitPoints() <= 0) {
			this.die();
		}
	}

	private int getNbHitPoints() {
		// TODO Auto-generated method stub
		return 0;
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
	
	public void die() {

	}
	
	public void remove() {
		this.world.removeSlime(this);
		this.setWorld(null);
	}
}
	
