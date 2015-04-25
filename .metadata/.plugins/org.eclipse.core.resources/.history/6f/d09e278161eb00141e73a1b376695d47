package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.assertEquals;
import jumpingalien.model.Mazub;
import jumpingalien.model.School;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.facade.IFacadePart2;

import org.junit.Test;

public class SchoolTest {
	
	public static final int FEATURE_AIR = 0;
	public static final int FEATURE_SOLID = 1;
	public static final int FEATURE_WATER = 2;
	public static final int FEATURE_MAGMA = 3;
	@Test 
	public void testLoseHitpoints() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 2, 2);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		School school = facade.createSchool();
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
		Slime slime1 = facade.createSlime(3, 499, spriteArrayForSize(3, 3, 2), school);
		Slime slime2 = facade.createSlime(50, 499, spriteArrayForSize(3, 3, 2), school);
		facade.setMazub(world, alien);
		facade.addSlime(world, slime1);
		facade.addSlime(world, slime2);
		alien.startMoveRight();
		facade.advanceTime(world, 0.1);
		// Mazub has hit the slime1, so slime1 loses 50 hitpoints
		assertEquals(slime1.getHitpoints(), 50);
		// Slime2 is in the same school, so he loses 1 hitpoint
		assertEquals(slime2.getHitpoints(), 99);
	}
	
	@Test 
	public void testJoinSchool() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 2, 2);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		//Two schools, newSchool with three members and oldSchool with two members. 
		// Slime will go from the oldSchool to the newSchool
		School oldSchool = facade.createSchool();
		School newSchool = facade.createSchool();
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
		Slime newSlime1 = facade.createSlime(50, 499, spriteArrayForSize(3, 3, 2), newSchool);
		Slime newSlime2 = facade.createSlime(55, 499, spriteArrayForSize(3, 3, 2), newSchool);
		Slime newSlime3 = facade.createSlime(60, 499, spriteArrayForSize(3, 3, 2), newSchool);
		Slime slime = facade.createSlime(50, 502, spriteArrayForSize(3, 3, 2), oldSchool);
		Slime oldSlime = facade.createSlime(300, 499, spriteArrayForSize(3, 3, 2), oldSchool);

		facade.setMazub(world, alien);
		facade.addSlime(world, newSlime1);
		facade.addSlime(world, newSlime2);
		facade.addSlime(world, newSlime3);
		facade.addSlime(world, slime);
		facade.addSlime(world, oldSlime);
		facade.advanceTime(world, 0.1);
		// slime collides with newSlime1, so he joins their school (newSchool) 
		assertEquals(slime.getSchool(), newSlime1.getSchool());
		// He handed one hitpoint over to all members (oldSlime) of his old school (oldSchool)
		assertEquals(oldSlime.getHitpoints(), 101);
		//And he got 1 hitpoint from every member of his new school (newSchool)
		assertEquals(newSlime1.getHitpoints(), 99);
		assertEquals(newSlime2.getHitpoints(), 99);
		assertEquals(newSlime3.getHitpoints(), 99);
		//So slime has now 100 - 1 + 3 hitpoints
		assertEquals(slime.getHitpoints(), 102);
	}
	
	@Test 
	public void testEquallyLargeSchools() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 2, 2);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		//Two schools, newSchool with three members and oldSchool with two members. 
		// Slime will go from the oldSchool to the newSchool
		School oldSchool = facade.createSchool();
		School newSchool = facade.createSchool();
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
		Slime newSlime1 = facade.createSlime(50, 499, spriteArrayForSize(3, 3, 2), newSchool);
		Slime newSlime2 = facade.createSlime(55, 499, spriteArrayForSize(3, 3, 2), newSchool);
		Slime oldSlime1 = facade.createSlime(50, 502, spriteArrayForSize(3, 3, 2), oldSchool);
		Slime oldSlime2 = facade.createSlime(300, 499, spriteArrayForSize(3, 3, 2), oldSchool);

		facade.setMazub(world, alien);
		facade.addSlime(world, newSlime1);
		facade.addSlime(world, newSlime2);
		facade.addSlime(world, oldSlime1);
		facade.addSlime(world, oldSlime2);
		facade.advanceTime(world, 0.1);
		// oldSlime1 collides with newSlime1, but the schools are the same size, so they stay in
		// their own school
		assertEquals(newSlime1.getSchool(), newSchool);
		assertEquals(oldSlime1.getSchool(), oldSchool);
		// And their hitpoints stay the same
		assertEquals(oldSlime1.getHitpoints(), 100);
		assertEquals(newSlime1.getHitpoints(), 100);
	}
}
