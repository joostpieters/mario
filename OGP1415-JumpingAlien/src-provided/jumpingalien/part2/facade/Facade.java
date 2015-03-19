package jumpingalien.part2.facade;

import java.util.Collection;

import jumpingalien.model.IllegalPositionException;
import jumpingalien.model.IllegalSpeedException;
import jumpingalien.model.IllegalSpriteException;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacadePart2 {
//  Hier zou een default constructor moeten staan!!
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
	@Override
	public void advanceTime(Mazub alien, double dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getNbHitPoints(Mazub alien) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public World createWorld(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int[] getWorldSizeInPixels(World world) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getTileLength(World world) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void startGame(World world) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isGameOver(World world) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean didPlayerWin(World world) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void advanceTime(World world, double dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int[] getVisibleWindow(World world) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int[][] getTilePositionsIn(World world, int pixelLeft,
			int pixelBottom, int pixelRight, int pixelTop) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getGeologicalFeature(World world, int pixelX, int pixelY)
			throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setGeologicalFeature(World world, int tileX, int tileY,
			int tileType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setMazub(World world, Mazub alien) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isImmune(Mazub alien) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Plant createPlant(int x, int y, Sprite[] sprites) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addPlant(World world, Plant plant) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Collection<Plant> getPlants(World world) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int[] getLocation(Plant plant) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Sprite getCurrentSprite(Plant plant) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Shark createShark(int x, int y, Sprite[] sprites) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addShark(World world, Shark shark) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Collection<Shark> getSharks(World world) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int[] getLocation(Shark shark) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Sprite getCurrentSprite(Shark shark) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public School createSchool() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Slime createSlime(int x, int y, Sprite[] sprites, School school) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addSlime(World world, Slime slime) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Collection<Slime> getSlimes(World world) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int[] getLocation(Slime slime) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Sprite getCurrentSprite(Slime slime) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public School getSchool(Slime slime) {
		// TODO Auto-generated method stub
		return null;
	}

}
