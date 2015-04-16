package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.assertEquals;
import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.util.Sprite;

import org.junit.Test;

public class GameObjectTest {

	@Test
	public void testCollidesSomeWhere() {
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(3, 3, 10 + 2 * m);
		Mazub alien = new Mazub(0,0,sprites);
		assertEquals(alien.collidesSomewhere(0, 10, 0, 10, 0, 10, 0, 10), true);
	}
}
