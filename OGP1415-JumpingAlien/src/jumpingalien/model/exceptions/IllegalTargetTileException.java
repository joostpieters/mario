package jumpingalien.model.exceptions;
/**
 * 
 * A class to check for an illegal TargetTile
 * 
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus
 * 
 *
 */
@SuppressWarnings("serial")
public class IllegalTargetTileException extends Exception {
	
	/**
	 * the horizontal coordinate of the target tile
	 */
	private final int targetTileX;
	
	/**
	 * the vertical coordinate of the target tile
	 */
	private final int targetTileY;
	/**
	 * the horizontal number of tiles in world
	 */
	private final int nbTilesX;
	
	/**
	 * the vertical number of tiles in world
	 */
	private final int nbTilesY;
	
	/**
	 * 
	 * @param targetTileX
	 * @param targetTileY
	 * @post the value of the new illegal targetTile exception is equal to 
	 * 			the given targetTileX and targetTileY
	 * 			| new.getTargetTileX = targetTileX
	 * 			| new.getTargetTileY = targetTileY
	 * 
	 */
	public IllegalTargetTileException(int targetTileX, int targetTileY, int nbTilesX, int nbTilesY) {
		this.targetTileX = targetTileX;
		this.targetTileY = targetTileY;
		this.nbTilesX = nbTilesX;
		this.nbTilesY = nbTilesY;
	}
	
	/**
	 * the horizontal coordinate of the target tile
	 * @return targetTileX
	 */
	public int getTargetTileX() {
		return targetTileX;
	}
	
	/**
	 * the vertical coordinate of the target tile
	 * @return targetTileY
	 */
	public int getTargetTileY() {
		return targetTileY;
	}

	/**
	 * the horizontal number of tiles in world
	 * @return the nbTilesX
	 */
	public int getNbTilesX() {
		return nbTilesX;
	}

	/**
	 * the vertical number of tiles in world
	 * @return the nbTilesY
	 */
	public int getNbTilesY() {
		return nbTilesY;
	}
}
