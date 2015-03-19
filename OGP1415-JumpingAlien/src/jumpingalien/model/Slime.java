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
	pbulic School getSchool() {
		
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
