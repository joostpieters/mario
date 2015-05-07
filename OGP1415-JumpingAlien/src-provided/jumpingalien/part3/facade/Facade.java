package jumpingalien.part3.facade;

import java.util.Collection;

import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Program;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.model.exceptions.IllegalAmountOfCharactersException;
import jumpingalien.model.exceptions.IllegalDtException;
import jumpingalien.model.exceptions.IllegalNbTilesException;
import jumpingalien.model.exceptions.IllegalPixelException;
import jumpingalien.model.exceptions.IllegalPositionException;
import jumpingalien.model.exceptions.IllegalSchoolException;
import jumpingalien.model.exceptions.IllegalSettingException;
import jumpingalien.model.exceptions.IllegalSpeedException;
import jumpingalien.model.exceptions.IllegalSpriteException;
import jumpingalien.model.exceptions.IllegalTargetTileException;
import jumpingalien.model.exceptions.IllegalTileException;
import jumpingalien.model.exceptions.IllegalTileSizeException;
import jumpingalien.model.exceptions.IllegalVisibleWindowException;
import jumpingalien.part3.programs.ParseOutcome;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacadePart3 {
	
	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites){
		try {return new Mazub(pixelLeftX, pixelBottomY,sprites);}
		catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException f) {
			throw new ModelException(f.getMessage());
		}
	}
//	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites,
			int initStartSpeed, int maxSpeed) {
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
	@Override
	public int[] getLocation(Mazub alien) {
		return alien.getLocation();
	}
	@Override 
	public double[] getVelocity(Mazub alien) {
		return alien.getVelocity();
	}
	@Override
	public double[] getAcceleration(Mazub alien) {
		return alien.getAcceleration();
	}
	@Override
	public int[] getSize(Mazub alien) {
		return alien.getSize();
	}
	@Override
	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}
	@Override
	public void startJump(Mazub alien) {
		alien.startJump();		
	}
	@Override
	public void endJump(Mazub alien) {
		alien.endJump();
	}
	@Override
	public void startMoveLeft(Mazub alien) {
		alien.startMoveLeft();
	}
	@Override
	public void endMoveLeft(Mazub alien) {
		alien.endMoveLeft();
	}
	@Override
	public void startMoveRight(Mazub alien) {
		alien.startMoveRight();
	}
	@Override
	public void endMoveRight(Mazub alien) {
		alien.endMoveRight();		
	}
	@Override
	public void startDuck(Mazub alien) {
		alien.startDuck();
	}
	@Override
	public void endDuck(Mazub alien) {
		alien.endDuck();
	}	
	@Override
	public void advanceTime(Mazub alien, double dt) {
		try {alien.advanceTime(dt);}
		catch(IllegalDtException e) {
			throw new ModelException(e.getMessage());
		}		
	}	
	@Override
	public int getNbHitPoints(Mazub alien) {
		return alien.getHitpoints();
	}	
	@Override
	public World createWorld(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		try { return new World(tileSize, nbTilesX,nbTilesY, visibleWindowWidth, 
				visibleWindowHeight, targetTileX, targetTileY);}
		catch(IllegalTileSizeException e) {
			throw new ModelException(e.getMessage());
		}
		catch(IllegalTargetTileException g) {
			throw new ModelException(g.getMessage());
		}
		catch(IllegalVisibleWindowException h) {
			throw new ModelException(h.getMessage());
		}
		catch(IllegalNbTilesException i) {
			throw new ModelException(i.getMessage());
		}
	}
	@Override
	public int[] getWorldSizeInPixels(World world) {
		return world.getWorldSizeInPixels();
	}
	@Override
	public int getTileLength(World world) {
		return world.getTileLength();
	}
	@Override
	public void startGame(World world) {
		world.startGame();
	}
	@Override
	public boolean isGameOver(World world) {
		return world.isGameOver();
	}
	@Override
	public boolean didPlayerWin(World world) {
		return world.didPlayerWin();
	}
	@Override
	public void advanceTime(World world, double dt) {
		try{world.advanceTime(dt);}
		catch(IllegalDtException e) {
			throw new ModelException(e.getMessage());
		}
	}
	@Override
	public int[] getVisibleWindow(World world) {
		return world.getVisibleWindow();
	}
	
	public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY) {
		return world.getBottomLeftPixelOfTile(tileX,tileY);
	}
	@Override
	public int[][] getTilePositionsIn(World world, int pixelLeft,
			int pixelBottom, int pixelRight, int pixelTop) {
		return world.getTilePositionsIn(pixelLeft, pixelBottom, pixelRight, pixelTop);
	}
	@Override
	public int getGeologicalFeature(World world, int pixelX, int pixelY) {
		try {return world.getGeologicalFeature(pixelX, pixelY);}
		catch(IllegalPixelException e) {
			throw new ModelException(e.getMessage());
		}	
	}
	@Override
	public void setGeologicalFeature(World world, int tileX, int tileY,
			int tileType) {
		try{world.setGeologicalFeature(tileX, tileY, tileType);}
		catch(IllegalTileException e) {
			throw new ModelException(e.getMessage());
		} catch (IllegalSettingException f) {
			throw new ModelException(f.getMessage());
		}
	}
	@Override
	public void setMazub(World world, Mazub alien) {
		try {
			world.setAlien(alien);
		} catch (IllegalSettingException f) {
			throw new ModelException(f.getMessage());
		} catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
	}
	@Override
	public boolean isImmune(Mazub alien) {
		return alien.isImmune();
	}
	@Override
	public Plant createPlant(int x, int y, Sprite[] sprites) {
		try { return new Plant(x,y,sprites);}
		catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException e) {
			throw new ModelException(e.getMessage());
		}
	}
	@Override
	public void addPlant(World world, Plant plant) {
	try {
		world.addPlant(plant);
	} catch (IllegalSettingException f) {
		throw new ModelException(f.getMessage());
	} catch (IllegalPositionException e) {
		throw new ModelException(e.getMessage());
	} catch (IllegalAmountOfCharactersException g) {
		throw new ModelException(g.getMessage());
	}
		
		
		
	}
	@Override
	public Collection<Plant> getPlants(World world) {
		return world.getPlants();
	}
	@Override
	public int[] getLocation(Plant plant) {
		return plant.getLocation();
	}
	@Override
	public Sprite getCurrentSprite(Plant plant) {
		return plant.getCurrentSprite();
	}
	@Override
	public Shark createShark(int x, int y, Sprite[] sprites) {
		try{ return new Shark(x,y,sprites);}
		catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException e) {
			throw new ModelException(e.getMessage());
		}
	}
	@Override
	public void addShark(World world, Shark shark) {
		try {
			world.addShark(shark);
		} catch (IllegalSettingException f) {
			throw new ModelException(f.getMessage());
		} catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		} catch (IllegalAmountOfCharactersException g) {
			throw new ModelException(g.getMessage());
		}
		
		
	}
	@Override
	public Collection<Shark> getSharks(World world) {
		return world.getSharks();
	}
	@Override
	public int[] getLocation(Shark shark) {
		return shark.getLocation();
	}
	@Override
	public Sprite getCurrentSprite(Shark shark) {
		return shark.getCurrentSprite();
	}
	@Override
	public School createSchool() {
		return new School();
	}
	@Override
	public Slime createSlime(int x, int y, Sprite[] sprites, School school) {
		try{ return new Slime(x,y,sprites,school);}
		catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSchoolException f) {
			throw new ModelException(f.getMessage());
		}
		
	}
	@Override
	public void addSlime(World world, Slime slime) {
		try {
			world.addSlime(slime);
		} catch (IllegalSettingException f) {
			throw new ModelException(f.getMessage());
		} catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		} catch (IllegalAmountOfCharactersException g) {
			throw new ModelException(g.getMessage());
		}
	}
	@Override
	public Collection<Slime> getSlimes(World world) {
		return world.getSlimes();
	}
	@Override
	public int[] getLocation(Slime slime) {
		return slime.getLocation();
	}
	@Override
	public Sprite getCurrentSprite(Slime slime) {
		return slime.getCurrentSprite();
	}
	@Override
	public School getSchool(Slime slime) {
		return slime.getSchool();
	}
	@Override
	public Buzam createBuzam(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		try {return new Buzam(pixelLeftX, pixelBottomY,sprites);}
		catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException f) {
			throw new ModelException(f.getMessage());
		}		
	}
	@Override
	public Buzam createBuzamWithProgram(int pixelLeftX, int pixelBottomY,
			Sprite[] sprites, Program program) {
		try {return new Buzam(pixelLeftX, pixelBottomY,sprites, program);}
		catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException f) {
			throw new ModelException(f.getMessage());
		}	}
	@Override
	public Plant createPlantWithProgram(int x, int y, Sprite[] sprites,
			Program program) {
		try { return new Plant(x,y,sprites, program);}
		catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException e) {
			throw new ModelException(e.getMessage());
		}
	}
	@Override
	public Shark createSharkWithProgram(int x, int y, Sprite[] sprites,
			Program program) {
		try{ return new Shark(x,y,sprites, program);}
		catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException e) {
			throw new ModelException(e.getMessage());
		}
	}
	@Override
	public Slime createSlimeWithProgram(int x, int y, Sprite[] sprites,
			School school, Program program) {
		try{ return new Slime(x,y,sprites,school, program);}
		catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSpriteException e) {
			throw new ModelException(e.getMessage());
		}
		catch (IllegalSchoolException f) {
			throw new ModelException(f.getMessage());
		}
	}
	@Override
	public ParseOutcome<?> parse(String text) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isWellFormed(Program program) {
		return program.isWellFormed();
	}
	@Override
	public void addBuzam(World world, Buzam buzam) {
		try {
			world.setBuzam(buzam);
		} catch (IllegalSettingException f) {
			throw new ModelException(f.getMessage());
		} catch (IllegalPositionException e) {
			throw new ModelException(e.getMessage());
		}		
	}
	@Override
	public int[] getLocation(Buzam alien) {
		return alien.getLocation();
	}
	@Override
	public double[] getVelocity(Buzam alien) {
		return alien.getVelocity();
	}
	@Override
	public double[] getAcceleration(Buzam alien) {
		return alien.getAcceleration();
	}
	@Override
	public int[] getSize(Buzam alien) {
		return alien.getSize();
	}
	@Override
	public Sprite getCurrentSprite(Buzam alien) {
		return alien.getCurrentSprite();
	}
	@Override
	public int getNbHitPoints(Buzam alien) {
		return alien.getHitpoints();
	}

}
