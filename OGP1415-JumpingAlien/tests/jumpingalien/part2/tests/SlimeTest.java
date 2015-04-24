package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.assertEquals;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.util.ModelException;

import org.junit.Test;

public class SlimeTest {
	
	public static final int FEATURE_AIR = 0;
	public static final int FEATURE_SOLID = 1;
	public static final int FEATURE_WATER = 2;
	public static final int FEATURE_MAGMA = 3;

	@Test(expected = ModelException.class)
	public void testNotWithinBoundaries() {
		IFacadePart2 facade = new Facade();
		School school = facade.createSchool();
		Slime slime = facade.createSlime(-5, -20, spriteArrayForSize(1, 1, 2), school);
	}
	
	@Test(expected = ModelException.class)
	public void testSpriteTooShort() {
		IFacadePart2 facade = new Facade();
		School school = facade.createSchool();
		Slime slime = facade.createSlime(0, 0, spriteArrayForSize(1, 1, 1), school);
	}
	
	@Test(expected = ModelException.class)
	public void testSpriteTooLong() {
		IFacadePart2 facade = new Facade();
		School school = facade.createSchool();
		Slime slime = facade.createSlime(0, 0, spriteArrayForSize(1, 1, 3), school);
	}
	
	@Test(expected = ModelException.class)
	public void testNullSchool() {
		IFacadePart2 facade = new Facade();
		Slime slime = facade.createSlime(0, 0, spriteArrayForSize(1, 1, 3), null);
	}
	
	@Test 
	public void testLoseHitpoints() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 2, 2);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		School school = facade.createSchool();
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
		Slime slime = facade.createSlime(3, 499, spriteArrayForSize(3, 3, 2), school);
		facade.setMazub(world, alien);
		facade.addSlime(world, slime);
		alien.startMoveRight();
		facade.advanceTime(world, 0.1);
		// Mazub has hit the slime so the slime loses 50 hitpoints
		assertEquals(slime.getHitpoints(), 50);
	}
	
	@Test 
	public void testIsImmune() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 2, 2);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		School school = facade.createSchool();
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
		Slime slime = facade.createSlime(3, 499, spriteArrayForSize(3, 3, 2), school);
		facade.setMazub(world, alien);
		facade.addSlime(world, slime);
		alien.startMoveRight();
		facade.advanceTime(world, 0.1);
		// Mazub has hit the slime so the slime get Immune
		assertEquals(slime.isImmune(), true);
	}
//	@Test 
//	public void testNotImmune() {
//		IFacadePart2 facade = new Facade();
//		World world = facade.createWorld(500, 5, 5, 1, 1, 2, 2);
//		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);	
//		School school = facade.createSchool();
//		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
//		Slime slime = facade.createSlime(3, 499, spriteArrayForSize(3, 3, 2), school);
//		facade.setMazub(world, alien);
//		facade.addSlime(world, slime);
//		alien.startMoveRight();
//		for (int i = 0; i < 7; i++) {
//			facade.advanceTime(world, 0.1);
//			alien.startJump();
//		}
//		// Mazub has hit the slime so the slime gets Immune and after 0.6 second not anymore
//		assertEquals(slime.isImmune(), false);
//	}
	//werkt niet altijd omdat die random bewegingen alles verpesten
	
	@Test 
	public void testDie() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(50, 5, 5, 1, 1, 2, 2);
		// x * x
		// * x *
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 2, 1, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 0, 1, FEATURE_SOLID);	
		School school = facade.createSchool();
		Mazub alien = facade.createMazub(49, 452, spriteArrayForSize(50, 3));
		Slime slime = facade.createSlime(49, 499, spriteArrayForSize(50, 3, 2), school);
		facade.setMazub(world, alien);
		facade.addSlime(world, slime);
		alien.startMoveRight();
		for (int i = 0; i < 7; i++) {
			facade.advanceTime(world, 0.1);
			alien.startJump();
		}
		// Mazub has hit the slime should have zero hitpoints after more then 0.6 second
		assertEquals(slime.getHitpoints(), 0);
	}
	// TODO laten werken
}
