package jumpingalien.part1.facade;


import jumpingalien.util.ModelException;
import jumpingalien.model.IllegalDtException;
import jumpingalien.model.IllegalPositionException;
import jumpingalien.model.IllegalSpeedException;
import jumpingalien.model.IllegalSpriteException;
import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;


public class Facade implements IFacade {
	
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites){
		try {return new Mazub(pixelLeftX, pixelBottomY,sprites);}
		catch(IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException f) {
			throw new ModelException(f.getMessage());
		}
	}
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites,
			int initStartSpeed, int maxSpeed){
		try {return new Mazub(pixelLeftX, pixelBottomY, sprites, initStartSpeed, maxSpeed);}
		catch(IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException f) {
			throw new ModelException(f.getMessage());
		}
		catch (IllegalSpeedException g) {
			throw new ModelException(g.getMessage());
		}
	}
		
	
	public int[] getLocation(Mazub alien) {
		return alien.getLocation();
	}

	 
	public double[] getVelocity(Mazub alien) {
		return alien.getVelocity();
	}

	 
	public double[] getAcceleration(Mazub alien) {
		return alien.getAcceleration();
	}

	 
	public int[] getSize(Mazub alien) {
		return alien.getSize();
	}

	 
	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}

	 
	public void startJump(Mazub alien) {
		alien.startJump();
		
	}

	 
	public void endJump(Mazub alien) {
		alien.endJump();
	}

	 
	public void startMoveLeft(Mazub alien) {
		alien.startMoveLeft();
	}

	 
	public void endMoveLeft(Mazub alien) {
		alien.endMoveLeft();
	}

	 
	public void startMoveRight(Mazub alien) {
		alien.startMoveRight();
	}

	 
	public void endMoveRight(Mazub alien) {
		alien.endMoveRight();		
	}

	 
	public void startDuck(Mazub alien) {
		alien.startDuck();
	}

	 
	public void endDuck(Mazub alien) {
		alien.endDuck();
	}

	 
	public void advanceTime(Mazub alien, double dt) {
		try {alien.advanceTime(dt);}
		catch(IllegalDtException e) {
			throw new ModelException(e.getMessage());
		}		
	}

	public boolean isValidPosition(Mazub alien) {
		return alien.isValidPosition();
	}
	
	public boolean isValidSprite(Sprite[] sprites) {
		return ((sprites.length >= 8) && (sprites.length % 2 == 0));
	}

}
