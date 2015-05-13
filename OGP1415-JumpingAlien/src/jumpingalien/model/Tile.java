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
		// TODO Mss hier een exeption throwen -> of gewoon uit superObject halen en enkel voor een gameObject maken?
		// throw new IllegalArgumentException();		
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
			// TODO moet hier air worden teruggegeven? want normaal geeft die hierboven dat toch al terug als het air is?
			// mss iets throwen
			return 0;
		}
	}

}
