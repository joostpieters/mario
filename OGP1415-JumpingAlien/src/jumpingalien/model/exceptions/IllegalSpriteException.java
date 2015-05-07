package jumpingalien.model.exceptions;

import jumpingalien.util.Sprite;

/**
 * 
 * A class to check for an illegal sprite 
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus 
 *
 */
@SuppressWarnings("serial")
public class IllegalSpriteException extends Exception {
	
	/**
	 * The sprites
	 */	
	private final Sprite[] sprites;

	/**
	 * initialize this new illegal sprite exception with a given sprite
	 * @param	sprites
	 * 			the given sprites
	 * @post	the value of the new illegal sprite exception is equal to 
	 * 			the given sprites
	 * 			| this.sprites = sprites
	 */
	public IllegalSpriteException(Sprite[] sprites){
		this.sprites = sprites;
	}
	
	public Sprite[] getSprite() {
		return sprites;
	}
}
