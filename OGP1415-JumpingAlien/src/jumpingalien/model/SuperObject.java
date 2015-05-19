package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class SuperObject {
	
	public SuperObject() {
		
	}
	
	
	public abstract double getXPos();
	public abstract double getYPos();
	public abstract int getXDim();
	public abstract int getYDim();
	
	/**
	 * the world where the superobject appears
	 */
	private World world;
	/**
	 * returns the world where the superobject appears
	 * @return world
	 */
	@Basic @Raw
	public World getWorld() {
		return this.world;
	}
	/**
	 * Sets the world to the givan value world
	 * @param world
	 * 		the world to set
	 * @pre the given World world may not be null
	 * 			| world != null
	 * @post world is set
	 * 			| this.world = world
	 */
	@Raw
	public void setWorld(World world) {
		assert world != null;
		this.world = world;
	}	


}
