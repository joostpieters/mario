package jumpingalien.model;

import java.util.List;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.part2.facade.IFacadePart2;

// TODO alles final maken wat final moet zijn
// TODO protected/ public /private maken #randomspaties
// TODO overal @override in facade (voor als iemands hoofd echt niet wil denken)
// TODO @Raw toevoegen overal waar het nodig is
// TODO checkers toevoegen in elke getter / setter
// TODO Kopie van sprites teruggeven, eigenlijk van elke array een kopie maken geloof ik
// TODO Commentaren
// TODO aparte testfiles
// TODO invariant van binnen grenzen
// TODO Laura nog eens muilen
// TODO alle classes doorzoeken op Mazub in de commentaar -> hoe bedoelde? Dat er soms nog mazub staat in gameobject en dat ik daar waarschijnlijk over lees

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
	 * @throws IllegalAmountOfCharactersException
	 * @throws IllegalTileSizeException
	 * @throws IllegalTargetTileException
	 * @throws IllegalVisibleWindowException
	 * @throws IllegalArgumentException
	 * @effect the new game world is created
	 * 			| setTileSize)
	 * 			| this.setNbTilesX(nbTilesX)
	 * 			| this.setNbTilesY(nbTilesY)
	 * 			| this.setVisibleWindowWidth(visibleWindowWidth)
	 * 			| this.setVisibleWindowHeight(visibleWindowHeight)
	 * 			| this.setTargetTileX(targetTileX)
	 * 			| this.setTargetTileY(targetTileY)
	 * 			| this.geologicalFeature = new int[nbTilesY][nbTilesX]
	 *  		| the geological features of the entire world are by default air (0)	
	 */
	public World(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) 
		throws IllegalAmountOfCharactersException,
				IllegalTileSizeException, IllegalTargetTileException,
				IllegalVisibleWindowException, IllegalArgumentException {
			if ( ! isValidAmountOfCharacters())
				throw new  IllegalAmountOfCharactersException();
			if ( ! isValidTileSize(tileSize))
				throw new  IllegalTileSizeException(tileSize);
			if  ( ! isValidNbTiles(nbTilesX))
				throw new IllegalArgumentException();
			if ( ! isValidNbTiles(nbTilesY))
				throw new IllegalArgumentException();
			if ( ! isValidTargetTile(targetTileX, targetTileY))
				throw new  IllegalTargetTileException(targetTileX, targetTileY);
			if ( ! isValidVisibleWindow(visibleWindowWidth, visibleWindowHeight,tileSize, nbTilesX, nbTilesY))
				throw new  IllegalVisibleWindowException(visibleWindowWidth, visibleWindowHeight);
		this.setTileSize(tileSize);
		this.setNbTilesX(nbTilesX);
		this.setNbTilesY(nbTilesY);
		this.setVisibleWindowWidth(visibleWindowWidth);
		this.setVisibleWindowHeight(visibleWindowHeight);
		this.setTargetTileX(targetTileX);
		this.setTargetTileY(targetTileY);
		this.geologicalFeature = new int[nbTilesY][nbTilesX];		
	}	
	
	/**
	 * the size of a side of a (square) tile
	 */
	private int tileSize;
	/**
	 * the amount of tiles in the x direction in the world
	 */
	private int nbTilesX;
	/**
	 * the amount of tiles in the y direction in the world
	 */
	private int nbTilesY;
	/**
	 * the width of the visible window in pixels
	 */
	private int visibleWindowWidth;
	/**
	 * the height of the visible window in pixels
	 */
	private int visibleWindowHeight;
	/**
	 * the horizontal position of the target tile
	 */
	private int targetTileX;
	/**
	 * the vertical position of the target tile
	 */
	private int targetTileY;
	/**
	 * the horizontal position of the visible window (of the bottom left pixel)
	 */
	private int xVisibleWindow;
	/**
	 * the vertical position of the visible window (of the bottom left pixel)
	 */
	private int yVisibleWindow;
	/**
	 * the geological feature of the tile with its bottom left pixel
	 * on the given position
	 */
	private int[][] geologicalFeature;
	/**
	 * the player character alien
	 */
	private Mazub alien;
	
	/**
	 * a list of all the sharks in the world
	 */
	private List<Shark> sharks = new CopyOnWriteArrayList<Shark>();
	/**
	 * a list of all the plants in the world
	 */
	private List<Plant> plants = new CopyOnWriteArrayList<Plant>();
	/**
	 * a list of all the slimes in the world
	 */
	private List<Slime> slimes = new CopyOnWriteArrayList<Slime>();	
	
//	GETTERS
	
	/**
	 * Returns the size of the given game world, in number of pixels.
	 * 
	 * @return The size of the game world, in pixels, as an array of two
	 *         elements: width (X) and height (Y), in that order.
	 * 			| {getX(), getY()}
	 */
	@Basic @Immutable 
	public int[] getWorldSizeInPixels() {
		return new int[] {getX(),getY()};
	}	
	/**
	 * Returns the length of a square tile side in the given world.
	 * 
	 * @return The length of a square tile side, expressed as a number of
	 *         pixels.
	 * 			| tilesize
	 */
	@Basic @Immutable 
	public int getTileLength() {
		return this.tileSize;
	}		
	/**
	 * @return the number of tiles in the horizontal direction
	 * 			| nbTilesX
	 */
	@Basic @Immutable 
	public int getNbTilesX() {
		return nbTilesX;
	}
	/**
	 * @return the number of tiles in the vertical direction
	 * 			| nbTilesY
	 */
	@Basic @Immutable 
	private int getNbTilesY() {
		return nbTilesY;
	}
	/**
	 * @return the width of the visible window
	 * 			| visibleWindowWidth
	 */
	@Basic @Immutable 
	private int getVisibleWindowWidth() {
		return visibleWindowWidth;
	}
	/**
	 * @return the height of the visible window
	 * 			| visibleWindowHeight
	 */
	@Basic @Immutable 
	private int getVisibleWindowHeight() {
		return visibleWindowHeight;
	}
	/**
	 * @return the horizontal position of the targetTile
	 * 			| targetTileX
	 */
	@Basic @Immutable 
	private int getTargetTileX() {
		return targetTileX;
	}
	/**
	 * @return the vertical position of the targetTile
	 * 			| targetTileY
	 */
	@Basic @Immutable 
	private int getTargetTileY() {
		return targetTileY;
	}
	/**
	 * Return the coordinates of the rectangular visible window that moves
	 * together with Mazub.
	 * 
	 * @return The pixel coordinates of the visible window, in the order
	 *         left, bottom, right, top
	 * 			| {getXVisibleWindow(), getYVisibleWindow(), getXVisibleWindow()
	 * 			|	+ getVisibleWindowWidth(), getYVisibleWindow() + getVisibleWindowHeight()}
	 */
	@Basic
	public int[] getVisibleWindow() {
		return new int[] {this.getXVisibleWindow(), this.getYVisibleWindow(),
				this.getXVisibleWindow() + this.getVisibleWindowWidth(),
				this.getYVisibleWindow() + this.getVisibleWindowHeight()};
	}
	/**
	 * returns the player character alien
	 * @return the player character alien
	 * 			| alien
	 */
	@Basic
	public Mazub getAlien() {
		return this.alien;
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
	 * 			| {tileX * getTileLength(), tileY * getTileLength()}
	 */
	@Basic
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
	@Basic
	private int[] getTileOfPixels(int pixelX, int pixelY) {
		return new int[] {(pixelX - pixelX % this.getTileLength()) / this.getTileLength(),
				(pixelY - pixelY % this.getTileLength()) / this.getTileLength()};
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
	// TODO moet hier nog andere documentatie bij? alles wat in die functie staat zeker
	@Basic
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
	 *         | this.geologicalFeature[pixelY/this.getTileLength()][pixelX/this.getTileLength()]
	 * 
	 * @note This method must return its result in constant time.
	 * 
	 * @throw IllegalPixelException if the given position does not correspond to the
	 *        bottom left pixel of a tile.
	 */
	@Basic
	public int getGeologicalFeature(int pixelX, int pixelY)
			throws IllegalPixelException {
				if ( ! isValidBottomLeftPixel(pixelX, pixelY))
					throw new IllegalPixelException(pixelX,pixelY);
		return this.geologicalFeature[pixelY/this.getTileLength()][pixelX/this.getTileLength()];
	}	
	/**
	 * 
	 * @return the amount of horizontal pixels in the world
	 * 			| nbTilesX * tileLength
	 */
	@Basic @Immutable 
	public int getX() {
		return this.getNbTilesX() * this.getTileLength();
	}	
	/**
	 * 
	 * @return the amount of vertical pixels in the world
	 * 			| nbTilesY * tileLength
	 */
	@Basic @Immutable 
	public int getY() {
		return this.getNbTilesY() * this.getTileLength();
	}
	/**
	 * the collection of all the plants in the world
	 * @return the list containing every plant in the world
	 * 			| plants
	 */
	@Basic
	public Collection<Plant> getPlants() {
		return this.plants;
	}
	/**
	 * the collection of all the sharks in the world
	 * @return the list containing every shark in the world
	 * 			| sharks
	 */
	@Basic
	public Collection<Shark> getSharks() {
		return this.sharks;
	}
	/**
	 * the collection of all the slimes in the world
	 * @return the list containing every slime in the world
	 * 			| slime
	 */
	@Basic
	public Collection<Slime> getSlimes() {
		return this.slimes;
	}
	/**
	 * the amount of plants in the world
	 * @return number of plants
	 * 			| plants.size()
	 */
	@Basic
	private int getNbPlants() {
		return plants.size();
	}
	/**
	 * the amount of sharks in the world
	 * @return number of sharks
	 * 			| sharks.size()
	 */
	@Basic
	private int getNbSharks() {
		return sharks.size();
	}
	/**
	 * the amount of slimes in the world
	 * @return number of slimes
	 * 			| slimes.size()
	 */
	@Basic
	private int getNbSlimes() {
		return slimes.size();
	}
	/**
	 * the amount of aliens in the world
	 * @return number of aliens
	 * 			| 1
	 */
	@Basic
	private int getNbAliens() {
		return 1;
	}	
	/**
	 * @return the horizontal amount of pixels of the VisibleWindow
	 * 			| XVisibleWindow
	 */
	@Basic
	private int getXVisibleWindow() {
		return xVisibleWindow;
	}
	/**
	 * @return the vertical amount of pixels of the VisibleWindow
	 * 			| YVisibleWindow
	 */
	@Basic
	private int getYVisibleWindow() {
		return yVisibleWindow;
	}
	
//	SETTERS	
	
	/**
	 * @param tileSize the tileSize to set
	 * @post the tileSize is set to the given value
	 * 			| this.tileSize = tileSize
	 */
	private void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
	/**
	 * @param nbTilesX the nbTilesX to set
	 * @post the number of tiles (horizontally) is set to the given amount
	 * 			| this.nbTilesX = nbTilesX
	 */
	private void setNbTilesX(int nbTilesX) {
		this.nbTilesX = nbTilesX;
	}
	/**
	 * @param nbTilesY the nbTilesY to set
	 * @post the number of tiles (vertically) is set to the given amount
	 * 			| this.nbTilesY = nbTilesY
	 */
	private void setNbTilesY(int nbTilesY) {
		this.nbTilesY = nbTilesY;
	}
	/**
	 * @param visibleWindowWidth the visibleWindowWidth to set
	 * @post the width of the visible window is equal to the given int
	 * 			| this.visibleWindowWidth = visibleWindowWidth
	 */
	private void setVisibleWindowWidth(int visibleWindowWidth) {
		this.visibleWindowWidth = visibleWindowWidth;
	}
	/**
	 * @param visibleWindowHeight the visibleWindowHeight to set
	 * @post the height of the visible window is equal to the given int
	 * 			| this.visibleWindowHeight = visibleWindowHeight
	 */
	private void setVisibleWindowHeight(int visibleWindowHeight) {
		this.visibleWindowHeight = visibleWindowHeight;
	}
	/**
	 * @param targetTileX the targetTileX to set
	 * @post the horizontal coordinate of the targetTile is equal to the given int
	 * 			| this.targetTileX =  targetTileX
	 */
	private void setTargetTileX(int targetTileX) {
		this.targetTileX = targetTileX;
	}
	/**
	 * @param targetTileY the targetTileY to set
	 * @post the vertical coordinate of the targetTile is equal to the given int
	 * 			| this.targetTileY = targetTileY
	 */
	private void setTargetTileY(int targetTileY) {
		this.targetTileY = targetTileY;
	}	
	/**
	 * @param xVisibleWindow the xVisibleWindow to set
	 * @post the horizontal position of the bottom left pixel of the visible window
	 * 		 is equal to the given value
	 * 			| this.xVisibleWindow = xVisibleWindow
	 */
	private void setXVisibleWindow(int xVisibleWindow) {
		this.xVisibleWindow = xVisibleWindow;
	}
	/**
	 * @param yVisibleWindow the yVisibleWindow to set
	 * @post the vertical position of the bottom left pixel of the visible window
	 * 		 is equal to the given value
	 * 			| this.yVisibleWindow = yVisibleWindow
	 */
	private void setYVisibleWindow(int yVisibleWindow) {
		this.yVisibleWindow = yVisibleWindow;
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
	 * @post the geological feature of the tile at the given position is equal to 
	 * 		 the given tileType
	 * 			| this.geologicalFeature[tileY][tileX] = tileType
	 */
	public void setGeologicalFeature(int tileX, int tileY, int tileType)
		throws IllegalTileException {
		if ( ! isValidTile(tileX, tileY, tileType)) {
			throw new IllegalTileException(tileX, tileY, tileType);
		}
		this.geologicalFeature[tileY][tileX] = tileType;
	}
	/**
	 * @param the player character
	 * 			| alien
	 * @post the player character alien is equal to the given Mazub
	 * 			| this.alien = alien
	 */
	public void setAlien(Mazub alien) {
		this.alien = alien;
	}
	/**
	 * removes the Mazub alien from the game world
	 * @param the player character
	 * 			| alien
	 * @pre the alien has to exist
	 * 			| alien != null
	 * @post the alien is set to null
	 * 			| this.alien = null
	 */
	public void removeAlien(@Raw Mazub alien) {
		assert alien != null;
		this.alien = null;
	}
	/**
	 * removes the Slime slime from the game world
	 * @param the Slime to remove
	 * 			| slime
	 * @pre this has to contain a slime
	 * 			| hasSlime(slime)
	 * @pre the slime may not be equal to null
	 * 			| slime != null
	 * @post the Slime slime is removed from the game world
	 * 			| this.slimes.remove(slime)
	 */
	public void removeSlime(@Raw Slime slime) {
		assert this.hasSlime(slime);
		assert slime != null;
		this.slimes.remove(slime);
	}
	/**
	 * removes the Plant plant from the game world
	 * @param the Plant to remove
	 * 			| plant
	 * @pre this has to contain a plant
	 * 			| hasPlant(plant)
	 * @pre the plant may not be equal to null
	 * 			| plant != null
	 * @post the Plant plant is removed from the game world
	 * 			| this.plants.remove(plant)
	 */
	public void removePlant(@Raw Plant plant) {
		assert this.hasPlant(plant);
		assert plant != null;
		this.plants.remove(plant);
	}
	/**
	 * removes the Shark shark from the game world
	 * @param the Shark to remove
	 * 			| shark
	 * @pre this has to contain a shark
	 * 			| hasShark(shark)
	 * @pre the shark may not be equal to null
	 * 			| shark != null
	 * @post the Shark shark is removed from the game world
	 * 			| this.sharks.remove(shark)
	 */
	public void removeShark(@Raw Shark shark) {
		assert this.hasShark(shark);
		assert shark != null;
		this.sharks.remove(shark);
	}
	/**
	 * adds the Slime slime to the world
	 * @param the slime to add
	 * 			| slime
	 * @pre the slime may not be equal to null
	 * 			| slime != null
	 * @pre the slime must be added to this world
	 * 			| slime.getWorld() == this
	 * @pre the game world may not already contain this slime
	 * 			| ! hasSlime(slime)
	 * @effect the slime is added to slimes
	 * 			| slimes.add(slime)
	 */
	public void addSlime(Slime slime) {
		assert slime != null;
	    assert slime.getWorld() == this;
	    assert ! this.hasSlime(slime);
		this.slimes.add(slime);
	}
	/**
	 * adds the Shark shark to the world
	 * @param the shark to add
	 * 			| shark
	 * @pre the shark may not be equal to null
	 * 			| shark != null
	 * @pre the shark must be added to this world
	 * 			| shark.getWorld() == this
	 * @pre the game world may not already contain this shark
	 * 			| ! hasShark(shark)
	 * @effect the shark is added to shark
	 * 			| sharks.add(shark)
	 */
	public void addShark(Shark shark) {
		assert shark != null;
	    assert shark.getWorld() == this;
	    assert ! this.hasShark(shark);
		this.sharks.add(shark);
	}
	/**
	 * adds the Plant slime to the world
	 * @param the plant to add
	 * 			| plant
	 * @pre the plant may not be equal to null
	 * 			| plant != null
	 * @pre the plant must be added to this world
	 * 			| plant.getWorld() == this
	 * @pre the game world may not already contain this plant
	 * 			| ! hasPlant(plant)
	 * @effect the plant is added to plants
	 * 			| plants.add(plant)
	 */
	public void addPlant(Plant plant) {
		assert plant != null;
	    assert plant.getWorld() == this;
	    assert ! this.hasPlant(plant);
		this.plants.add(plant);
	}
	/**
	 * returns true if this world has a Plant plant
	 * @param the plant to check
	 * 			| plant
	 * @return this.plants.contains(plant)
	 */
	private boolean hasPlant(Plant plant) {
		return this.plants.contains(plant);
	}
	/**
	 * returns true if this world has a Shark shark
	 * @param the shark to check
	 * 			| shark
	 * @return this.sharks.contains(shark)
	 */
	private boolean hasShark(Shark shark) {
		return this.sharks.contains(shark);
	}
	/**
	 * returns true if this world has a Slime slime
	 * @param the slime to check
	 * 			| slime
	 * @return this.slimes.contains(slime)
	 */
	private boolean hasSlime(Slime slime) {
		return this.slimes.contains(slime);
	}	
	
//	VALIDATIONS
	
	/**
	 * returns true if the given tileSize is valid (greater than 0)
	 * @param the tileSize to check
	 * 			| tileSize
	 * @return (tileSize > 0)
	 */
	private boolean isValidTileSize(int tileSize){
		return tileSize > 0;
	}
	/**
	 * returns true if the given number of tiles nbTiles is valid (greater than 0)
	 * @param the given nbTiles to check
	 * 			| nbTiles
	 * @return (nbTiles > 0)
	 */
	private boolean isValidNbTiles(int nbTiles){
		return nbTiles > 0;
	}
	/**
	 * returns true if the given visible window is valid
	 * @param visiblewindowWidth
	 * 			the width of the visible window
	 * @param visibleWindowHeight
	 * 			the height of the visible window
	 * @param tileSize
	 * 			the size of the tiles in the world
	 * @param nbTilesX
	 * 			the number of horizontal tiles
	 * @param nbTilesY
	 * 			the number of vertical tiles
	 * @return ((visiblewindowWidth <=  tileSize * nbTilesX) && (visibleWindowHeight
				<=  tileSize * nbTilesY))
	 */
	private boolean isValidVisibleWindow(int visiblewindowWidth,int visibleWindowHeight,
				int tileSize, int nbTilesX, int nbTilesY){
		return  ((visiblewindowWidth <=  tileSize * nbTilesX) && (visibleWindowHeight
				<=  tileSize * nbTilesY));
	}
	/**
	 * returns true if the given coordinate of the bottom left pixel is valid
	 * @param pixelX
	 * 			the horizontal position
	 * @param pixelY
	 * 			the vertical position
	 * @return ((pixelX % this.getTileLength() == 0) && (pixelY % this.getTileLength() == 0))
	 */
	private boolean isValidBottomLeftPixel(int pixelX, int pixelY) {
		return ((pixelX % this.getTileLength() == 0) && (pixelY % this.getTileLength() == 0));
	}
	/**
	 * returns true if there is at least one player character Mazub and if there are no
	 * more than 100 other game objects
	 * @return ((getNbPlants() + getNbSharks() + getNbSlimes() <= 100) && (getNbAliens() >= 1) )
	 */
	private boolean isValidAmountOfCharacters() {
		return ((this.getNbPlants() + this.getNbSharks() + 
				this.getNbSlimes() <= 100) && (this.getNbAliens() >= 1) );
	}
	/**
	 * returns true if the given target tile is valid
	 * @param x
	 * 			the horizontal position of the tile
	 * @param y
	 * 			the vertical position of the tile
	 * @return 
	 */
	// TODO dit nog maken. Ik denk dat het zo goed is eigenlijk
	private boolean isValidTargetTile(int x, int y) {
		return (x >= 0 && y >= 0); 
	}
	/**
	 * returns true if the given tile is valid
	 * @param tileX
	 * 			the horizontal position of the tile
	 * @param tileY
	 * 			the vertical position of the tile
	 * @param tileType
	 * 			the type of geological feature this tile possesses
	 * @return ((tileX < getNbTilesX()) && (tileY < getNbTilesY())
				&& (tileType >= 0) && (tileType <= 4))
	 */
	private boolean isValidTile(int tileX, int tileY, int tileType) {
		return (tileX < this.getNbTilesX()) && (tileY < this.getNbTilesY())
				&& (tileType >= 0) && (tileType <= 4);
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
	// TODO moeten we hier nu nog iets mee doen?
	// ja ik geloof van we :(
	public void startGame() {
		
	}
	
	/**
	 * Returns whether the game, played in the given game world, is over.
	 * The game is over when Mazub has died, or has reached the target tile.
	 * 
	 * @return true if the game is over, false otherwise.
	 * 			| (getAlien == null || didPlayerWint())
	 */
	public boolean isGameOver() {
		return (this.getAlien() == null || this.didPlayerWin());
	}
	
	/**
	 * Returns whether the game played in the given world has finished and the
	 * player has won. The player wins when Mazub has reached the target tile.
	 * 
	 * @return true if the game is over and the player has won, false otherwise.
	 * 			| if (getAlien != null)
	 * 			| 	then for each tile in getTilePositionsIn(alien.getXPos(), alien.getYPos(),
	 * 			|							alien.getXPos() + alien.getXDim()
	 * 			|							alien.getYPos() + alien.getYDim()
	 * 			|			if (tile[0] == getTargetTileX() && tile[1] == getTargetTileY())
	 * 			|				then return true
	 * 			| else 
	 * 			| 	then return false
	 */
	public boolean didPlayerWin() {
		if (this.getAlien() != null) {
			for (int[] tile: this.getTilePositionsIn( (int) this.getAlien().getXPos(), 
					(int) this.getAlien().getYPos(), (int) this.getAlien().getXPos() + this.getAlien().getXDim(),
					(int) this.getAlien().getYPos() + this.getAlien().getYDim())) {
				if (tile[0] == this.getTargetTileX() && tile[1] == this.getTargetTileY()) {
					return true;
				}
			}
		}		
		return false;
	}
	
	/**
	 * calculates the dt for every object in the game world
	 * the maximal value for dt is 0.2
	 * @return the minimal value for dt
	 */
	// TODO dit moet toch niet gedocumenteerd worden omdat dit deel van advanceTime is he?
	// Geen idee eigenlijk. Alles is deel van advanceTime :D Geen commentaar nodig. Jeeeeej!!!
	private double computeMinimalDt(double dt) {
		double dtGiven = dt;
		for (Plant plant : this.getPlants()) {
			if (plant.computeDt(dtGiven) < dt) {
				dt = plant.computeDt(dtGiven);				
			}			 
		}
		for (Shark shark: this.getSharks()) {
			if (shark.computeDt(dtGiven) < dt) {
				dt = shark.computeDt(dtGiven);			
			}		
		}
		for (Slime slime: this.getSlimes()) {
			if (slime.computeDt(dtGiven) < dt) {
				dt = slime.computeDt(dtGiven);			
			}		
		}
		if (alien.computeDt(dtGiven) < dt) {
			dt = alien.computeDt(dtGiven);
		}		
		return dt;
	}	
	
	/**
	 * NO DOCUMENTATION MUST BE WORKED OUT
	 * @throws IllegalDtException 
	 */
	public void advanceTime(double dt) throws IllegalDtException {
				
		double minDt = computeMinimalDt(dt);
		double timePassed = 0;
		if(minDt < dt) {
			for (timePassed = 0; timePassed < (dt - minDt); timePassed += minDt) {
				advanceEveryGameObject(minDt);
			}
		}
		advanceEveryGameObject(dt - timePassed);
	}
	/**
	 * NO DOCUMENTATION
	 * @param dt
	 * @throws IllegalDtException
	 */
	// TODO dit moet toch niet gedocumenteerd worden omdat dit deel van advanceTime is he?
	private void advanceEveryGameObject(double dt) throws IllegalDtException {
		if(this.getAlien() != null) {
			for (Plant plant : this.getPlants()) {
				 plant.advanceTime(dt);
			}
			for (Shark shark: this.getSharks()) {
				shark.advanceTime(dt);
			}
			for (Slime slime: this.getSlimes()) {
				slime.advanceTime(dt);
			}
			this.getAlien().advanceTime(dt);
			
	
			positioningVisibleWindow();
		}
	}
	
	/**
	 * positions the visible window of the game world as Mazub is moving
	 * @effect 
	 * 			| if (getAlien != null)
	 * 			| 	then ... (see next @effect's)
	 * @effect positions the visible window in the horizontal direction
	 * 			| if (alien.getLocation[0] < 200) 
	 * 			| 	then setXVisibleWindow(0)
	 * 			| else if (alien.getLocation[0] + alien.getSize()[0] > getX() - 200)
	 * 			| 	then setXVisibleWindow(getX() - getVisibleWindowWidth())
	 * 			| else if (alien.getLocation()[0] - 200 < getXVisibleWindow())
	 * 			| 	then setXVisibleWindow(alien.getLocation[0] - 200)
	 * 			| else if (alien.getLocation()[0] + alien.getSize()[0] +200 
	 * 			| 			> getXVisibleWindow() + getVisibleWindowWidth())
	 * 			| 	then setXVisibleWindow(alien.getLocation()[0] + alien.getSize()[0]
	 * 			|							- getVisibleWindowWidth() + 200)
	 * @effect positions the visible window in the vertical direction
	 * 			| if (alien.getLocation[1] < 200) 
	 * 			| 	then setYVisibleWindow(0)
	 * 			| else if (alien.getLocation[1] + alien.getSize()[1] > getY() - 200)
	 * 			| 	then setYVisibleWindow(getY() - getVisibleWindowHeight())
	 * 			| else if (alien.getLocation()[1] - 200 < getYVisibleWindow())
	 * 			| 	then setYVisibleWindow(alien.getLocation[0] - 200)
	 * 			| else if (alien.getLocation()[1] + alien.getSize()[1] +200 
	 * 			| 			> getYVisibleWindow() + getVisibleWindowHeight())
	 * 			| 	then setYVisibleWindow(alien.getLocation()[1] + alien.getSize()[1]
	 * 			|							- getVisibleWindowHeight() + 200)
	 */
	private void positioningVisibleWindow() {
		if (this.getAlien() != null) {
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
				this.setXVisibleWindow(this.getAlien().getLocation()[0] + this.getAlien().getSize()[0]
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
	}
	
	/**
	 * returns true if the objects touch each other
	 * @param x1
	 * 			the horizontal position of the first object
	 * @param xDim1
	 * 			the horizontal dimension of the first object
	 * @param y1
	 * 			the vertical position of the first object
	 * @param yDim1
	 * 			the horizontal dimension of the first object
	 * @param x2
	 * 			the horizontal position of the first object
	 * @param xDim2
	 * 			the horizontal dimension of the first object
	 * @param y2
	 * 			the vertical position of the first object
	 * @param yDim2
	 * 			the vertical dimension of the first object
	 * @return ( (x2 >= x1) && (x2 <= x1 + xDim1) ) 
	 * 		|| ( (x2 + xDim2 >= x1) && (x2 + xDim2 <= x1 + xDim1) )
	 * 		&& 	( (y2 >= y1) && (y2 <= y1 + yDim1) ) 
			|| ( (y2 + yDim2 >= y1) && (y2 + yDim2 <= y1 + yDim1) )		
	 */
	private boolean touches(double x1, double xDim1, double y1, double yDim1,
			double x2, double xDim2, double y2, double yDim2) {
		
		boolean xStatement = ( (x2 >= x1) && (x2 <= x1 + xDim1) ) 
				|| ( (x2 + xDim2 >= x1) && (x2 + xDim2 <= x1 + xDim1) );
		boolean yStatement = ( (y2 >= y1) && (y2 <= y1 + yDim1) ) 
				|| ( (y2 + yDim2 >= y1) && (y2 + yDim2 <= y1 + yDim1) );
		
		return ((xStatement) && (yStatement));
	}
	
	//TODO volgens mij gebruiken we alle methodes hieronder niet -> kan wel eens, de eerste en de laatste sowieso niet
	/**
	 * returns the amount of Plants the player character touched
	 * @param xPos
	 * 			the horizontal position of the player character
	 * @param yPos
	 * 			the vertical position of the player character
	 * @param xDim
	 * 			the horizontal dimension of the player character
	 * @param yDim
	 * 			the vertical dimension of the player character
	 * @return 
	 */
	private int touchedPlants(double xPos, double yPos, double xDim, double yDim) {
		int amountOfDeadPlants = 0;
		for (Plant plant : this.getPlants()) {
			 if (this.touches(xPos, xDim, yPos, yDim, plant.getXPos(),
					 plant.getXDim(), plant.getYPos(), plant.getYDim())) {
				 plant.die();
				 amountOfDeadPlants += 1;
			 }
		}
		return amountOfDeadPlants;
	}
	
	public int touchedSharks(double xPos, double yPos, double xDim, double yDim) {
		int nbTouchedSharks = 0;
		for (Shark shark : this.getSharks()) {
			 if (this.touches(xPos, xDim, yPos, yDim, shark.getXPos(),
					 shark.getXDim(), shark.getYPos(), shark.getYDim())) {
				 shark.setHitpoints(shark.getHitpoints() - Shark.getContactDamage());
				 nbTouchedSharks += 1;
			 }
		}
		return nbTouchedSharks;
	}	
	
	public int touchedSlimes(double xPos, double yPos, double xDim, double yDim) {
		int nbTouchedSlimes = 0;
		for (Slime slime : this.getSlimes()) {
			 if (this.touches(xPos, xDim, yPos, yDim, slime.getXPos(),
					 slime.getXDim(), slime.getYPos(), slime.getYDim())) {
				 slime.setHitpoints(slime.getHitpoints() - Slime.getContactDamage());
				 nbTouchedSlimes += 1;
			 }
		}
		return nbTouchedSlimes;
	}
	
	public int touchedAliens(double xPos, double yPos, double xDim, double yDim) {
		int nbTouchedAliens = 0;
		if (this.touches(xPos, xDim, yPos, yDim, alien.getXPos(),
				alien.getXDim(), alien.getYPos(), alien.getYDim())) {
			nbTouchedAliens += 1;
		}
		return nbTouchedAliens;
	}
	
	private boolean mazubCollidesAboveWithSharkOrSlime() {
		for (Shark shark: this.getSharks()) {
			if (alien.collidesAbove(shark.getXPos(), shark.getXDim(), shark.getYPos(), shark.getYDim(),
					alien.getXPos(), alien.getXDim(), alien.getYPos(), alien.getYDim())) {
				return true;
			}
		}
		for (Slime slime: this.getSlimes()) {
			if (alien.collidesAbove(alien.getXPos(), alien.getXDim(), alien.getYPos(), alien.getYDim(),
					slime.getXPos(), slime.getXDim(), slime.getYPos(), slime.getYDim())) {
				return true;
			}
		}
		return false;
	}
	
}
