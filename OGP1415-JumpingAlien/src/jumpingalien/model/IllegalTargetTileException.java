package jumpingalien.model;

public class IllegalTargetTileException {
	
	/**
	 * the horizontal coordinate of the target tile
	 */
	private final int targetTileX;
	
	/**
	 * the vertical coordinate of the target tile
	 */
	private final int targetTileY;
	
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
	public IllegalTargetTileException(int targetTileX, int targetTileY) {
		this.targetTileX = targetTileX;
		this.targetTileY = targetTileY;
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
}
