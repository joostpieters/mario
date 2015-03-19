package jumpingalien.model;

import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.util.ModelException;

public class World {
	
	/**
	 * Create a new world with the given parameters.
	 * 
	 * @param tileSize
	 *            Length (in pixels) of a side of each square tile in the world
	 * @param nbTilesX
	 *            Number of tiles in the horizontal direction
	 * @param nbTilesY
	 *            Number of tiles in the vertical direction
	 * @param visibleWindowWidth
	 *            Width of the visible window, in pixels
	 * @param visibleWindowHeight
	 *            Height of the visible window, in pixels
	 * @param targetTileX
	 *            Tile x-coordinate of the target tile of the created world
	 * @param targetTileY
	 *            Tile y-coordinate of the target tile of the created world
	 */
	public World(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
// TODO illegaltilesizeecxeption, illegalsetarget..., illegalvis... toevoegen
		this.setTileSize(tileSize);
		this.setNbTilesX(nbTilesX);
		this.setNbTilesY(nbTilesY);
		this.setVisibleWindowWidth(visibleWindowWidth);
		this.setVisibleWindowHeight(visibleWindowHeight);
		this.setTargetTileX(targetTileX);
		this.setTargetTileY(targetTileY);
	}
	
	private int tileSize;
	private int nbTilesX;
	private int nbTilesY;
	private int visibleWindowWidth;
	private int visibleWindowHeight;
	private int targetTileX;
	private int targetTileY;
	

	
	
//	GETTERS
	/**
	 * Returns the size of the given game world, in number of pixels.
	 * 
	 * @return The size of the game world, in pixels, as an array of two
	 *         elements: width (X) and height (Y), in that order.
	 */
	public int[] getWorldSizeInPixels() {
		return new int[] {getX(),getY()};
	}
	
	/**
	 * Returns the length of a square tile side in the given world.
	 * 
	 * @return The length of a square tile side, expressed as a number of
	 *         pixels.
	 */
	public int getTileLength() {
		return this.tileSize;
	}
	
	
	/**
	 * @return the nbTilesX
	 */
	private int getNbTilesX() {
		return nbTilesX;
	}

	/**
	 * @return the nbTilesY
	 */
	private int getNbTilesY() {
		return nbTilesY;
	}

	/**
	 * @return the visibleWindowWidth
	 */
	private int getVisibleWindowWidth() {
		return visibleWindowWidth;
	}

	/**
	 * @return the visibleWindowHeight
	 */
	private int getVisibleWindowHeight() {
		return visibleWindowHeight;
	}

	/**
	 * @return the targetTileX
	 */
	private int getTargetTileX() {
		return targetTileX;
	}

	/**
	 * @return the targetTileY
	 */
	private int getTargetTileY() {
		return targetTileY;
	}

	/**
	 * Return the coordinates of the rectangular visible window that moves
	 * together with Mazub.
	 * 
	 * @return The pixel coordinates of the visible window, in the order
	 *         <b>left, bottom, right, top</b>.
	 */
	public int[] getVisibleWindow(World world) {
		
	}
	
	/**
	 * Returns the bottom left pixel coordinate of the tile at the given tile
	 * position.
	 * 
	 * @param tileX
	 *            The x-position x_T of the tile
	 * @param tileY
	 *            The y-position y_T of the tile
	 * @return An array which contains the x-coordinate and y-coordinate of the
	 *         bottom left pixel of the given tile, in that order.
	 */
	public int[] getBottomLeftPixelOfTile(int tileX, int tileY){
		
	}
	
	/**
	 * Returns the tile positions of all tiles within the given rectangular
	 * region.
	 * 
	 * @param pixelLeft
	 *            The x-coordinate of the left side of the rectangular region.
	 * @param pixelBottom
	 *            The y-coordinate of the bottom side of the rectangular region.
	 * @param pixelRight
	 *            The x-coordinate of the right side of the rectangular region.
	 * @param pixelTop
	 *            The y-coordinate of the top side of the rectangular region.
	 * 
	 * @return An array of tile positions, where each position (x_T, y_T) is
	 *         represented as an array of 2 elements, containing the horizontal
	 *         (x_T) and vertical (y_T) coordinate of a tile in that order.
	 *         The returned array is ordered from left to right,
	 *         bottom to top: all positions of the bottom row (ordered from
	 *         small to large x_T) precede the positions of the row above that.
	 * 
	 */
	public int[][] getTilePositionsIn(int pixelLeft, int pixelBottom,
			int pixelRight, int pixelTop) {
		
	}
	
	/**
	 * Returns the geological feature of the tile with its bottom left pixel at
	 * the given position.
	 * 
	 * @param world
	 *            The world containing the tile for which the
	 *            geological feature should be returned.
	 * 
	 * @param pixelX
	 *            The x-position of the pixel at the bottom left of the tile for
	 *            which the geological feature should be returned.
	 * @param pixelY
	 *            The y-position of the pixel at the bottom left of the tile for
	 *            which the geological feature should be returned.
	 * 
	 * @return The type of the tile with the given bottom left pixel position,
	 *         where
	 *         <ul>
	 *         <li>the value 0 is returned for an <b>air</b> tile;</li>
	 *         <li>the value 1 is returned for a <b>solid ground</b> tile;</li>
	 *         <li>the value 2 is returned for a <b>water</b> tile;</li>
	 *         <li>the value 3 is returned for a <b>magma</b> tile.</li>
	 *         </ul>
	 * 
	 * @note This method must return its result in constant time.
	 * 
	 * @throw Exception if the given position does not correspond to the
	 *        bottom left pixel of a tile.
	 */
//  juiste exeption maken en aanpassen in commentaar
	public int getGeologicalFeature(int pixelX, int pixelY)
			throws Exception {
		
	}
	
	
	
	
	/**
	 * 
	 * @return
	 */
	private int getX() {
		return this.getNbTilesX() * this.getTileLength();
	}
	
	/**
	 * 
	 * @return
	 */
	private int getY() {
		return this.getNbTilesY() * this.getTileLength();
	}
//	SETTERS
	
	
	/**
	 * @param tileSize the tileSize to set
	 */
	private void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	/**
	 * @param nbTilesX the nbTilesX to set
	 */
	private void setNbTilesX(int nbTilesX) {
		this.nbTilesX = nbTilesX;
	}

	/**
	 * @param nbTilesY the nbTilesY to set
	 */
	private void setNbTilesY(int nbTilesY) {
		this.nbTilesY = nbTilesY;
	}

	/**
	 * @param visibleWindowWidth the visibleWindowWidth to set
	 */
	private void setVisibleWindowWidth(int visibleWindowWidth) {
		this.visibleWindowWidth = visibleWindowWidth;
	}

	/**
	 * @param visibleWindowHeight the visibleWindowHeight to set
	 */
	private void setVisibleWindowHeight(int visibleWindowHeight) {
		this.visibleWindowHeight = visibleWindowHeight;
	}

	/**
	 * @param targetTileX the targetTileX to set
	 */
	private void setTargetTileX(int targetTileX) {
		this.targetTileX = targetTileX;
	}

	/**
	 * @param targetTileY the targetTileY to set
	 */
	private void setTargetTileY(int targetTileY) {
		this.targetTileY = targetTileY;
	}
	
	/**
	 * Modify the geological type of a specific tile in the given world to a
	 * given type.
	 * 
	 * @param tileX
	 *            The x-position x_T of the tile for which the type needs to be
	 *            modified
	 * @param tileY
	 *            The y-position y_T of the tile for which the type needs to be
	 *            modified
	 * @param tileType
	 *            The new type for the given tile, where
	 *            <ul>
	 *            <li>the value 0 is provided for an <b>air</b> tile;</li>
	 *            <li>the value 1 is provided for a <b>solid ground</b> tile;</li>
	 *            <li>the value 2 is provided for a <b>water</b> tile;</li>
	 *            <li>the value 3 is provided for a <b>magma</b> tile.</li>
	 *            </ul>
	 */
	public void setGeologicalFeature(int tileX, int tileY, int tileType);


	/**
	 * 
	 * @param position
	 * @return
	 */
	private Mazub pixelPositioned(int[] position) {
		
	}
	
	/**
	 * Starts the game that is played in the given world.
	 * After this method has been invoked, no further game objects will be added
	 * via {@link IFacadePart2#addPlant(World, Plant)},
	 * {@link IFacadePart2#addShark(World, Shark)},
	 * {@link IFacadePart2#addSlime(World, Slime)}, or
	 * {@link IFacadePart2#setMazub(World, Mazub)}), and no geological features
	 * will be changed via
	 * {@link IFacadePart2#setGeologicalFeature(World, int, int, int)}.
	 */
	public void startGame() {
		
	}
	
	/**
	 * Returns whether the game, played in the given game world, is over.
	 * The game is over when Mazub has died, or has reached the target tile.
	 * 
	 * @return true if the game is over, false otherwise.
	 */
	public boolean isGameOver() {
	}
	
	/**
	 * Returns whether the game played in the given world has finished and the
	 * player has won. The player wins when Mazub has reached the target tile.
	 * 
	 * @return true if the game is over and the player has won; false otherwise.
	 */
	boolean didPlayerWin() {
		
	}
	
	
	/**
	 * NO DOCUMENTATION MUST BE WORKED OUT
	 */
	private void advanceTime(double dt) {
		
	}
	

}
