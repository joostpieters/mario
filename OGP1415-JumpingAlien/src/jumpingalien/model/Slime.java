package jumpingalien.model;

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
	 * 
	 */
	public Slime(int x, int y, Sprite[] sprites){
		this.setX(x);
		this.setY(y);
		this.setSprites(sprites);
	}
	

	private int x;
	private int y;
	private Sprite[] sprites;
	

	
	
//	GETTERS	
	/**
	 * @return the x
	 */
	private int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	private int getY() {
		return y;
	}

	/**
	 * @return the sprites
	 */
	private Sprite[] getSprites() {
		return sprites;
	}

	/**
	 * Returns the current location of the given slime.
	 * 
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given slime's bottom left pixel in the world.
	 */
	public int[] getLocation(){
		return new int[]{this.getX(),this.getY()};
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
		
	}

		
		
	private int INITHITPOINTS = 100;
	/**
	 * the horizontal acceleration of a slime
	 */
	private double xAcc = 0.7;
	/**
	 * the maximum horizontal sopeed a slime can reach
	 */
	private double MAXXSPEED = 2.5;
	/**
	 * the duration of a movement period (2 to 6s)
	 */
	private int movementDuration;
	/**
	 * the amount of hitpoints a slime loses when touching 
	 * a shark or mazub.
	 */
	private int CONTACTDAMAGE = 50;
	/**
	 * the damage every slime in the school looses when a single
	 * slime looses some hitpoints
	 */
	private int SCHOOLDAMAGE = 1;
	/**
	 * the amount of hitpoints a slime hands over to the members of 
	 * the old school when joining a school
	 */
	private int JOININGHITPOINTSTOGIVE = 1;
	/**
	 * the amount of hitpoints a slime recieves when from every
	 * member of the new group when joining
	 */
	private int JOININGHITPOINTSTORECIEVE = 1;
	/**
	 * the maximum amount of slime schools in a game world
	 */
	private int MAXAMOUNTOFSCHOOLS = 10;
	
	private int getINITHITPOINTS() {
		return INITHITPOINTS;
	}
	private double getXAcc() {
		return xAcc;
	}
	private double getMAXXSPEED() {
		return  MAXXSPEED;
	}
	private int getMovementDuration() {
		return movementDuration;
	}
	private int getCONTACTDAMAGE() {
		return CONTACTDAMAGE;
	}
	private int getSCHOOLDAMAGE() {
		return SCHOOLDAMAGE;
	}
	private int getJOININGHITPOINTSTOGIVE() {
		return JOININGHITPOINTSTOGIVE;
	}
	private int getJOININGHITPOINTSTORECIEVE() {
		return JOININGHITPOINTSTORECIEVE;
	}
	private int getMAXAMOUNTOFSCHOOLS() {
		return MAXAMOUNTOFSCHOOLS;
	}
	
	
	/**
	 * starts the action period for an object
	 */
	public void start<action> {
		
	}
	
	/**
	 * ends the action period for an object
	 */
	public void stop<action> {
		
	}
	
	private void advanceTime() {
		
	}
	

//	SETTERS
	/**
	 * @param x the x to set
	 */
	private void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y the y to set
	 */
	private void setY(int y) {
		this.y = y;
	}
	
	/**
	 * @param sprites the sprites to set
	 */
	private void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	}
	
	private void advanceTime() {
		
	}

	/**
	 * Return the current sprite image for the given slime.
	 * 
	 * @return The current sprite image for the given slime, determined by its
	 *         orientation as defined in the assignment.
	 */
	public Sprite getCurrentSprite(){
		
	}
}
