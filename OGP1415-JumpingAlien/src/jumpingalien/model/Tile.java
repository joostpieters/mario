package jumpingalien.model;

public class Tile extends SuperObject {

	public Tile(int xPos, int yPos, World world) {
		this.setXPos(xPos);
		this.setYPos(yPos);
	}
	
	private int xPos;
	private int yPos;
	
	private void setXPos(int xPos) {
		this.xPos = xPos;		
	}
	
	private void setYPos(int yPos) {
		this.yPos = yPos;		
	}

	@Override
	protected double getXPos() {
		return this.xPos;
	}

	@Override
	protected double getYPos() {
		return this.yPos;
	}
	
	private World world;
	

	@Override
	protected int getXDim() {
		return 0;
	}

	@Override
	protected int getYDim() {
		return 0;
	}

	@Override
	public int getHitpoints() {
		return 0;
	}

}
