package jumpingalien.part2.facade;

import java.util.Collection;

import jumpingalien.model.IllegalAmountOfCharactersException;
import jumpingalien.model.IllegalDtException;
import jumpingalien.model.IllegalPixelException;
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
// TODO Hier zou een default constructor moeten staan!!
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
	
	public int getNbHitPoints(Mazub alien) {
		return alien.getNbHitPoints();
	}
	
	
	public World createWorld(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		try{ return new World(tileSize, nbTilesX,nbTilesY, visibleWindowWidth, 
				visibleWindowHeight, targetTileX, targetTileY);}
// TODO andere exceptions nog toevoegen
		catch(IllegalArgumentException e){
			throw new ModelException(e.getMessage());
		}
		catch(IllegalAmountOfCharactersException f){
			throw new ModelException(f.getMessage());
		}
	}

	
	public int[] getWorldSizeInPixels(World world) {
		return world.getWorldSizeInPixels();
	}
	
	public int getTileLength(World world) {
		return world.getTileLength();
	}
	
	public void startGame(World world) {
		world.startGame();
	}
	
	public boolean isGameOver(World world) {
		return world.isGameOver();
	}
	
	public boolean didPlayerWin(World world) {
		return world.didPlayerWin();
	}
	
	public void advanceTime(World world, double dt) {
		try{world.advanceTime(dt);}
		catch(IllegalDtException e) {
			throw new ModelException(e.getMessage());
		}
	}
	
	public int[] getVisibleWindow(World world) {
		return world.getVisibleWindow();
	}
	
	public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY) {
		return world.getBottomLeftPixelOfTile(tileX,tileY);
	}
	
	public int[][] getTilePositionsIn(World world, int pixelLeft,
			int pixelBottom, int pixelRight, int pixelTop) {
		return world.getTilePositionsIn(pixelLeft, pixelBottom, pixelRight, pixelTop);
	}
	
	public int getGeologicalFeature(World world, int pixelX, int pixelY) {
		try {return world.getGeologicalFeature(pixelX, pixelY);}
		catch(IllegalPixelException e) {
			throw new ModelException(e.getMessage());
		}	
	}
	
	public void setGeologicalFeature(World world, int tileX, int tileY,
			int tileType) {
		world.setGeologicalFeature(tileX, tileY, tileType);		
	}
	
	public void setMazub(World world, Mazub alien) {
		alien.setWorld(world);
		world.setAlien(alien);
		
	}
	
	public boolean isImmune(Mazub alien) {
		return alien.isImmune();
	}
	
	public Plant createPlant(int x, int y, Sprite[] sprites) {
		try{ return new Plant(x,y,sprites);}
		catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException e) {
			throw new ModelException(e.getMessage());
		}
	}
	@Override
	public void addPlant(World world, Plant plant) {
		plant.setWorld(world);
		world.addPlant(plant);
		
	}
	@Override
	public Collection<Plant> getPlants(World world) {
		return world.getPlants();
	}
	
	public int[] getLocation(Plant plant) {
		return plant.getLocation();
	}
	
	public Sprite getCurrentSprite(Plant plant) {
		return plant.getCurrentSprite();
	}
	
	public Shark createShark(int x, int y, Sprite[] sprites) {
		try{ return new Shark(x,y,sprites);}
		catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException e) {
			throw new ModelException(e.getMessage());
		}
	}

	public void addShark(World world, Shark shark) {
		shark.setWorld(world);
		world.addShark(shark);
		
	}
	@Override
	public Collection<Shark> getSharks(World world) {
		return world.getSharks();
	}
	
	public int[] getLocation(Shark shark) {
		return shark.getLocation();
	}
	
	public Sprite getCurrentSprite(Shark shark) {
		return shark.getCurrentSprite();
	}

	public School createSchool() {
		return new School();
	}

	public Slime createSlime(int x, int y, Sprite[] sprites, School school) {
		try{ return new Slime(x,y,sprites,school);}
		catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException e) {
			throw new ModelException(e.getMessage());
		}
		
	}

	public void addSlime(World world, Slime slime) {
		slime.setWorld(world);
		world.addSlime(slime);
		
	}

	public Collection<Slime> getSlimes(World world) {
		return world.getSlimes();
	}
	
	public int[] getLocation(Slime slime) {
		return slime.getLocation();
	}
	
	public Sprite getCurrentSprite(Slime slime) {
		return slime.getCurrentSprite();
	}
	
	public School getSchool(Slime slime) {
		return slime.getSchool();
	}

}
