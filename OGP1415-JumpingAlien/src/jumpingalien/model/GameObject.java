package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.util.Sprite;

/**
 * 
 * @author Pieter
 *
 */

public class GameObject {
	/**
	 * Creates a new gameObject, located at the provided pixel location (x, y).
	 * The returned gameObject should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the gameObject's initial position
	 * @param y
	 *            The y-coordinate of the gameObject's initial position
	 * @param sprites
	 *            An array of sprites for the new gameObject
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for the gameObject
	 * 			| !isValidPosition(x_pos,y_pos)
	 * @throws IllegalSpriteException
	 * 			The given sprite is not valid
	 * 			| !isValidSprite(sprites) 
	 */
	public GameObject(int xPos,int yPos, Sprite[] sprites ) 
			throws IllegalPositionException, IllegalSpriteException {
				if(!isValidPosition(xPos,yPos))
					throw new IllegalPositionException(xPos,yPos); 
				if (!isValidSprite(sprites))
					throw new IllegalSpriteException(sprites);
		this.position  = new Position(xPos, yPos);
		this.setSprite(sprites);
	}
	
	Position position;
	
	private Sprite[] sprites;
	/**
	 * @return the sprites
	 */
	private Sprite[] getSprite() {
		return sprites;
	}
	/**
	 * @param sprites the sprites to set
	 */
	private void setSprite(Sprite[] sprites) {
		this.sprites = sprites;
	}
	
	private World world;
	public World getWorld() {
		return this.world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	
	private double xSpeed;
	private double getXSpeed() {
		return this.xSpeed;
	}
	private void setXSpeed(double speed) {
		this.xSpeed = speed;
	}
	
	private double ySpeed;
	private double getYSpeed() {
		return this.ySpeed;
	}
	private void setYSpeed(double speed) {
		this.ySpeed = speed;
	}
	
	/**
	 * the horizontal acceleration 
	 */
	private double xAcc = 1.5;
	private double getXAcc() {
		return xAcc;
	}
	/**
	 * the vertical acceleration 
	 */
	private double yAcc;
	/**
	 *  Returns the vertical acceleration 
	 * @return the vertical acceleration
	 * 			| YAcc
	 */
	@Basic @Raw 
	private double getYAcc() {
		return yAcc;
	}
	/**
	 * Sets the vertical acceleration to a new value
	 * @param yAcc
	 * 			the new value of the vertical acceleration
	 */
	@Raw 
	private void setYAcc(double yAcc) {
		this.yAcc = yAcc;
	}
	
	private boolean dying = false;
	
	private boolean isDying() {
		return this.dying;
	}
	private void setDying() {
		this.isDying = true;
	}
	
	/**
	 * Returns the current location of the given shark.
	 * 
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given shark's bottom left pixel in the world.
	 */
	public int[] getLocation(){
		return new int[]{(int) this.getXPos(), (int) this.getYPos()};
	}	
	
	/**
	 * a variable containing the amount of hitpoints a Shark possesses
	 */
	private int hitpoints;
	private int getNbHitpoints() {
		return this.hitpoints;
	}
	private void setNbHitpoints(int nb) {
		this.hitpoints = nb;
	}
	private double timeSinceDeath = 0;
	private double getTimeSinceDeath() {
		return this.timeSinceDeath;
	}
	private void setTimeSinceDeath(double t) {
		this.timeSinceDeath += t;
	}
	
	
	

//	Validations
	private boolean isValidSprite(Sprite[] sprites) {
		return sprites.length == 2;
	}
	
	//TODO
	public boolean isValidYSpeed(double ySpeed) {
		return ( ! Double.isNaN(ySpeed));
	}
	
	
	
	private void die() {
		this.setXSpeed(0);
		this.setYSpeed(0);
		this.setDying();
	}
	
	
}
