package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.doubleArray;
import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Test;

public class GameObjectTest {

	@Test
	public void testCollidesSomeWhereRight() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
		assertArrayEquals(doubleArray(1.5, 2, 1, 0),alien.collidesSomewhere(2, 8, 2, 8, 9.5, 10, 0, 10),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testCollidesSomeWhereAbove() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
		assertArrayEquals(doubleArray(0, 0, 1, 0),alien.collidesSomewhere(0, 10, 0, 10, 5, 4, 9, 10),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testCollidesSomeWhereUnder() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
		assertArrayEquals(doubleArray(0, 10, 1, 0),alien.collidesSomewhere(0, 10, 10, 10, 3, 5, 0, 10),
				Util.DEFAULT_EPSILON);
	}
}
