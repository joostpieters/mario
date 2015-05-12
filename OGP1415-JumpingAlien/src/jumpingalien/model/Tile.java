package jumpingalien.model;

import jumpingalien.model.exceptions.IllegalPixelException;

public class Tile extends SuperObject {

	public Tile(int xCor, int yCor, World world) {
		this.setXCor(xCor);
		this.setYCor(yCor);
	}
	
	private int xCor;
	private int yCor;
	
	private void setXCor(int xPos) {
		this.xCor = xPos;		
	}
	private void setYCor(int yPos) {
		this.yCor = yPos;		
	}

	protected int getXCor() {
		return this.xCor;
	}
	protected int getYCor() {
		return this.yCor;
	}
	
	private World world;
	

	@Override
	protected int getXDim() {
		return world.getTileLength();
	}

	@Override
	protected int getYDim() {
		return world.getTileLength();
	}

	@Override
	public int getHitpoints() {
		// Mss hier een exeption throwen
		return 0;
	}

	@Override
	protected double getXPos() {
		return world.getTileLength() * this.getXCor();
	}

	@Override
	protected double getYPos() {
		return world.getTileLength() * this.getYCor();
	}

	public int getGeologicalFeature() {
		try {
			return world.getGeologicalFeature((int) getXPos(), (int) getYPos());
		} catch (IllegalPixelException e) {
			return 0;
		}
	}

}
