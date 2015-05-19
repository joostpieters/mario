package jumpingalien.model;

import jumpingalien.model.exceptions.IllegalPixelException;

public class Tile extends SuperObject {

	public Tile(int xCor, int yCor, World world) {
		this.setXCor(xCor);
		this.setYCor(yCor);
		this.setWorld(world);
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
	
	@Override
	public int getXDim() {
		return this.getWorld().getTileLength();
	}
	@Override
	public int getYDim() {
		return this.getWorld().getTileLength();
	}

	@Override
	public double getXPos() {
		return this.getWorld().getTileLength() * this.getXCor();
	}

	@Override
	public double getYPos() {
		return this.getWorld().getTileLength() * this.getYCor();
	}

	public int getGeologicalFeature() {
		try {
			return this.getWorld().getGeologicalFeature((int) getXPos(), (int) getYPos());
		} catch (IllegalPixelException e) {
			throw new IllegalArgumentException();
		}
	}

}
