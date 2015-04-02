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
	}

	
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
	
	private int getInitHitpoints() {
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
	/**
	 * Returns the current location of the given slime.
	 * 
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given slime's bottom left pixel in the world.
	 */
	public int[] getLocation(){
		return new int[]{(int) Position.getXPos(), (int) Position.getYPos()};
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
	
	public boolean isValidYSpeed(double ySpeed) {
		return ( ! Double.isNaN(ySpeed));
	}
	
	
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
		assert isValidSprite(GameObject.getSprite());
		if (Position.getOrientation() == Orientation.RIGHT) {
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
	
