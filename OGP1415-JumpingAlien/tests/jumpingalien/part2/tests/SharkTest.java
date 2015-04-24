package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.doubleArray;
import static jumpingalien.tests.util.TestUtils.intArray;
import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.CopyOnWriteArrayList;

import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.World;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Test;

public class SharkTest {
	public static final int FEATURE_AIR = 0;
	public static final int FEATURE_SOLID = 1;
	public static final int FEATURE_WATER = 2;
	public static final int FEATURE_MAGMA = 3;

	@Test 
	public void testShortInMagma() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 2, 2);
		// s m s
		// a s a
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 2, 1, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 0, 1, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 1, FEATURE_MAGMA);		
		Mazub alien = facade.createMazub(0, 999, spriteArrayForSize(50, 3));
		Shark shark = facade.createShark(500, 499, spriteArrayForSize(3, 3, 2));
		facade.setMazub(world, alien);
		facade.addShark(world, shark);
		facade.advanceTime(world, 0.1);
				
		//0.1 second, so the shark loses 50 hitpoints
		assertEquals(shark.getHitpoints(), 75);
	}
	
	@Test 
	public void testLongInMagma() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 2, 2);
		// s m s
		// a s a
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 2, 1, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 0, 1, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 1, FEATURE_MAGMA);		
		Mazub alien = facade.createMazub(0, 999, spriteArrayForSize(50, 3));
		Shark shark = facade.createShark(500, 499, spriteArrayForSize(3, 3, 2));
		facade.setMazub(world, alien);
		facade.addShark(world, shark);
		for (int i = 0; i < 4; i++) {
			facade.advanceTime(world, 0.1);
		}
		// 0.4 seconds in magma, so the shark loses 2 * 50 hitpoints
		assertEquals(shark.getHitpoints(), 0);
	}
	
	@Test 
	public void testRemoveAfterDeath() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 2, 2);
		// s w s
		// a s a
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 2, 1, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 0, 1, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 1, FEATURE_MAGMA);		
		Mazub alien = facade.createMazub(0, 999, spriteArrayForSize(50, 3));
		Shark shark = facade.createShark(500, 499, spriteArrayForSize(3, 3, 2));
		facade.setMazub(world, alien);
		facade.addShark(world, shark);
		for (int i = 0; i < 10; i++) {
			facade.advanceTime(world, 0.1);
		}
		// 1.1 seconds in magma, the shark dies and is removed after 0.6 seconds, so no part anymore of world
		assertEquals(world.getSharks(),  new CopyOnWriteArrayList<Shark>());
	}
}
