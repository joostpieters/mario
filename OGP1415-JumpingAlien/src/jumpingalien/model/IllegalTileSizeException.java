package jumpingalien.model;
/**
 * 
 * A class to check for an illegal tileSize
 * 
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus
 * 
 *
 */
@SuppressWarnings("serial")
public class IllegalTileSizeException extends Exception {
	
	/**
	 * the size  of the tiles
	 */
	private final int tilesize;
	
	/**
	 * initialize this new illegal tilesize exception with a given tilesize
	 * @param tilesize
	 * @post the value of the illegal tilesize exception is equal to the 
	 * 			given tilesize
	 * 			| new. getTileSize = tilesize
	 */
	public IllegalTileSizeException(int tilesize) {
		this.tilesize = tilesize;
	}
	
	/**
	 * 
	 * @return tilesize
	 */
	public int getTileSize() {
		return tilesize;
	}
}
