package jumpingalien.model;

import java.util.Arrays;
import java.util.Collection;

import jumpingalien.part2.facade.IFacadePart2;


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
			int targetTileY) 
		throws IllegalArgumentException, IllegalAmountOfCharactersException {
			if (!isValidTileSize(tileSize))
				throw new IllegalArgumentException();
			if ( ! isValidAmountOfCharacters())
				throw new  IllegalAmountOfCharactersException();
// TODO illegaltilesizeecxeption, illegalsetarget..., illegalvis... toevoegen
		this.setTileSize(tileSize);
		this.setNbTilesX(nbTilesX);
		this.setNbTilesY(nbTilesY);
		this.setVisibleWindowWidth(visibleWindowWidth);
		this.setVisibleWindowHeight(visibleWindowHeight);
		this.setTargetTileX(targetTileX);
		this.setTargetTileY(targetTileY);
		this.geologicalFeature = new int[nbTilesY][nbTilesX];
	}
	
	

	private int tileSize;
	private int nbTilesX;
	private int nbTilesY;
	private int visibleWindowWidth;
	private int visibleWindowHeight;
	private int targetTileX;
	private int targetTileY;
	private int XVisibleWindow;
	private int YVisibleWindow;
	private int[][] geologicalFeature;// = new int[this.nbTilesY][this.nbTilesX];
	private Mazub alien;
	private Plant plant;
	private Shark shark;
	private Slime slime;
	

	
	
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
	public int[] getVisibleWindow() {
		return new int[] {this.getXVisibleWindow(), this.getYVisibleWindow(),
				this.getXVisibleWindow() + this.getVisibleWindowWidth(),
				this.getYVisibleWindow() + this.getVisibleWindowHeight()};
	}
	
	private Mazub getAlien() {
		return this.alien;
	}
	
	private Shark getShark() {
		return this.shark;
	}
	
	private Plant getPlant() {
		return this.plant;
	}
	
	private Slime getSlime() {
		return this.slime;
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
	public int[] getBottomLeftPixelOfTile(int tileX, int tileY) {
		return new int[] {tileX * this.getTileLength(),
				tileY * this.getTileLength()};
	}
		
	/**
	 * Returns the tile coordinate of the tile at the given pixels.
	 * 
	 * @param pixelX
	 *            The x-pixel
	 * @param pixelY
	 *            The y-pixel
	 * @return An array which contains the x-coordinate and y-coordinate of the
	 *          given tile, in that order.
	 */
	private int[] getTileOfPixels(int pixelX, int pixelY) {
		return new int[] {(pixelX - pixelX % this.getTileLength())/this.getTileLength(),
				(pixelY - pixelY % this.getTileLength())/this.getTileLength()};
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
		int posLeft = this.getTileOfPixels(pixelLeft,pixelBottom)[0];
		int posBottom = this.getTileOfPixels(pixelLeft,pixelBottom)[1];
		int posRight = this.getTileOfPixels(pixelRight,pixelTop)[0];
		int posTop = this.getTileOfPixels(pixelRight,pixelTop)[1];
		
		int[][] array = new int[(posRight - posLeft + 1) * (posTop - posBottom +1)][2];
		
		int counter = 0;
		for (int i=posBottom;i <= posTop;i++ ) {
			for (int j=posLeft;j <= posRight;j++){
				array[counter][0] = j;
				array[counter][1] = i;
				counter++;
			}
		}
		return array;
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
	 * @throw IllegalPixelException if the given position does not correspond to the
	 *        bottom left pixel of a tile.
	 */
//  TODO exception maken
	public int getGeologicalFeature(int pixelX, int pixelY)
			throws IllegalPixelException {
				if(!isValidBottomLeftPixel(pixelX, pixelY))
					throw new IllegalPixelException(pixelX,pixelY);
		return this.geologicalFeature[pixelY/this.getTileLength()][pixelX/this.getTileLength()];
	}
	
	/**
	 * 
	 * @return
	 */
	public int getX() {
		return this.getNbTilesX() * this.getTileLength();
	}
	
	/**
	 * 
	 * @return
	 */
	public int getY() {
		return this.getNbTilesY() * this.getTileLength();
	}
//	SETTERS
	
	
	/**
	 * @return the xVisibleWindow
	 */
	private int getXVisibleWindow() {
		return XVisibleWindow;
	}

	/**
	 * @return the yVisibleWindow
	 */
	private int getYVisibleWindow() {
		return YVisibleWindow;
	}

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
	 * @param xVisibleWindow the xVisibleWindow to set
	 */
	private void setXVisibleWindow(int xVisibleWindow) {
		XVisibleWindow = xVisibleWindow;
	}

	/**
	 * @param yVisibleWindow the yVisibleWindow to set
	 */
	private void setYVisibleWindow(int yVisibleWindow) {
		YVisibleWindow = yVisibleWindow;
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
// 	TODO exception toevoegen
	public void setGeologicalFeature(int tileX, int tileY, int tileType) {
		
		this.geologicalFeature[tileY][tileX] = tileType;
	}
	
	public void setAlien(Mazub alien) {
		this.alien = alien;
	}
	
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	
	public void setShark(Shark shark) {
		this.shark = shark;
	}
	
	public void setSlime(Slime slime) {
		this.slime = slime;
	}

//	VALIDATIONS
	
	private boolean isValidTileSize(int tileSize){
		return tileSize > 0;
	}
	
	private boolean isValidNbTiles(int nbTiles){
		return nbTiles >0;
	}
	
	private boolean isValidVisibleWindow(int visiblewindowWidth,int visibleWindowHeight,
				int tileSize, int nbTilesX, int nbTilesY){
		return ((visiblewindowWidth <= tileSize * nbTilesX) && (visibleWindowHeight
				<= tileSize * nbTilesY));
	}
	private boolean isValidBottomLeftPixel(int pixelX, int pixelY) {
		return (new int[] {pixelX, pixelY} == this.getBottomLeftPixelOfTile(pixelX, pixelY));
	}
	
	private boolean isValidAmountOfCharacters() {
		return true;
			//	((alien.getNumberOfMazubs() > 1) && (plant.getNumberOfPlants() + 
				//slime.getNumberOfSlimes() + shark.getNumberOfSharks() <= 100));
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
		return (this.getAlien().isDeath() || didPlayerWin());
	}
	
	/**
	 * Returns whether the game played in the given world has finished and the
	 * player has won. The player wins when Mazub has reached the target tile.
	 * 
	 * @return true if the game is over and the player has won; false otherwise.
	 */
	public boolean didPlayerWin() {
		
		return (this.getAlien().getLocation() == new int[] {this.getTargetTileX(),this.getTargetTileY()});
	}
	
	
	/**
	 * NO DOCUMENTATION MUST BE WORKED OUT
	 * @throws IllegalDtException 
	 */
// TODO uitzoeken hoe dit moet
	public void advanceTime(double dt) throws IllegalDtException {
		alien.advanceTime(dt);
		plant.advanceTime(dt);
		shark.advanceTime(dt);
		slime.advanceTime(dt);
		
		positioningVisibleWindow();
	}
	
	private void positioningVisibleWindow() {
		//x
		if (this.getAlien().getLocation()[0] < 200) {
			this.setXVisibleWindow(0);
		}
		else if (this.getAlien().getLocation()[0] + this.getAlien().getSize()[0] > this.getX() - 200) {
			this.setXVisibleWindow(this.getX() - this.getVisibleWindowWidth());
		}
		else if (this.getAlien().getLocation()[0] - 200 < this.getXVisibleWindow()) {
			this.setXVisibleWindow(this.getAlien().getLocation()[0] - 200);
		}
		else if (this.getAlien().getLocation()[0] + this.getAlien().getSize()[0] + 200 >
				this.getXVisibleWindow() + this.getVisibleWindowWidth()){
			this.setXVisibleWindow(this.getAlien().getLocation()[0] +this.getAlien().getSize()[0]
								- this.getVisibleWindowWidth() + 200);
		}
		//y
		if (this.getAlien().getLocation()[1] < 200) {
			this.setYVisibleWindow(0);
		}
		else if (this.getAlien().getLocation()[1] + this.getAlien().getSize()[1] > this.getY() - 200) {
			this.setYVisibleWindow(this.getY() - this.getVisibleWindowHeight());
		}
		else if (this.getAlien().getLocation()[1] - 200 < this.getYVisibleWindow()) {
			this.setYVisibleWindow(this.getAlien().getLocation()[1] - 200);
		}
		else if (this.getAlien().getLocation()[1] + this.getAlien().getSize()[1] + 200 >
				this.getYVisibleWindow() + this.getVisibleWindowHeight()){
			this.setYVisibleWindow(this.getAlien().getLocation()[1] + this.getAlien().getSize()[1]
								- this.getVisibleWindowHeight() + 200);
		}
		
	}
	
	// TODO dit maken
	private double computeDt() {
		return Math.min();
	}
	
	public Collection<Plant> getPlants() {
		// TODO Auto-generated method stub
		return null;
	}
	public Collection<Shark> getSharks() {
		// TODO Auto-generated method stub
		return null;
	}
	public Collection<Slime> getSlimes() {
		// TODO Auto-generated method stub
		return null;
	}



}
