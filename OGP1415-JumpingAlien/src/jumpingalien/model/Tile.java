package jumpingalien.model;

import jumpingalien.model.exceptions.IllegalPixelException;

public class Tile extends SuperObject {
	
	/**
	 * 
	 * @param xCor
	 * 			the horizontal coordinate of the tile
	 * @param yCor
	 * 			the vertical coordinate of the tile
	 * @param world
	 * 			the world in which the tile is located
	 * @effect the horizontal and vertical position are set, also the world is set
	 * 			to the given World 
	 * 			| setXCor(xCor)
	 * 			| setYCor(yCor)
	 * 			| setWorld(world)
	 */
	public Tile(int xCor, int yCor, World world) {
		this.setXCor(xCor);
		this.setYCor(yCor);
		this.setWorld(world);
	}
	
	/**
	 * the horizontal coordinate of the tile 
	 */
	private int xCor;
	/**
	 * 	the vertical coordinate of the tile
	 */
	private int yCor;
	
	/**
	 * sets the horizontal coordinate to the given value
	 * @param xPos
	 * 			the horizontal coordinate to set
	 * @effect the value xCor is set
	 * 			| xCor = xCor
	 */
	private void setXCor(int xCor) {
		this.xCor = xCor;		
	}
	/**
	 * sets the vertical coordinate to the given value
	 * @param xPos
	 * 			the vertical coordinate to set
	 * @effect the value yCor is set
	 * 			| yCor = yCor
	 */
	private void setYCor(int yCor) {
		this.yCor = yCor;		
	}
	
	/**
	 * returns the horizontal coordinate of the tile
	 * @return xCor
	 * 			the horizontal coordinate
	 */
	protected int getXCor() {
		return this.xCor;
	}
	/**
	 * returns the vertical coordinate of the tile
	 * @return yCor
	 * 			the vertical coordinate
	 */
	protected int getYCor() {
		return this.yCor;
	}
	
	/**
	 * returns the horizontal size (width) of the tile
	 * @return the width
	 * 			| world.getTileLength()
	 */
	@Override
	public int getXDim() {
		return this.getWorld().getTileLength();
	}
	/**
	 * returns the vertical size (height) of the tile
	 * @return the height
	 * 			| world.getTileLength()
	 */
	@Override
	public int getYDim() {
		return this.getWorld().getTileLength();
	}
	
	/**
	 * returns the horizontal position of the bottom left pixel of the tile
	 * @return the horizontal position
	 * 			| world.getTileLength() * this.getXCor()
	 */
	@Override
	public double getXPos() {
		return this.getWorld().getTileLength() * this.getXCor();
	}
	
	/**
	 * returns the vertical position of the bottom left pixel of the tile
	 * @return the vertical position
	 * 			| world.getTileLength() * this.getYCor()
	 */
	@Override
	public double getYPos() {
		return this.getWorld().getTileLength() * this.getYCor();
	}
	
	/**
	 * returns the geological feature of this tile
	 * @return the geological feature of the tile
	 * 			| world.getGeologicalFeature(getXPos(), getYPos())
	 * @throws IllegalPixelException
	 * 			the given position (xPos, yPos) is not valid
	 * 			| ( ! isValidBottomLeftPixel(getXPos(), getYPos())
	 */
	public int getGeologicalFeature() {
		try {
			return this.getWorld().getGeologicalFeature((int) getXPos(), (int) getYPos());
		} catch (IllegalPixelException e) {
			throw new IllegalArgumentException();
		}
	}

}
