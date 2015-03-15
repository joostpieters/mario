package jumpingalien.part1.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import jumpingalien.part1.facade.Facade;
import jumpingalien.part1.facade.IFacade;
import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Test;

import static jumpingalien.tests.util.TestUtils.*;

public class PartialFacadeTest {

	@Test
	public void startMoveRightCorrect() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.1);

		// x_new [m] = 0 + 1 [m/s] * 0.1 [s] + 1/2 0.9 [m/s^2] * (0.1 [s])^2 =
		// 0.1045 [m] = 10.45 [cm], which falls into pixel (10, 0)

		assertArrayEquals(intArray(10, 0), facade.getLocation(alien));
	}

	@Test
	public void startMoveRightMaxSpeedAtRightTime() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		// maximum speed reached after 20/9 seconds
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2 / 9);
		}

		assertArrayEquals(doubleArray(3, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void startMoveLeftCorrect() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(200, 0, spriteArrayForSize(2, 2));
		facade.startMoveLeft(alien);
		facade.advanceTime(alien, 0.1);

		// x_new [m] = 0 + 1 [m/s] * 0.1 [s] + 1/2 0.9 [m/s^2] * (0.1 [s])^2 =
		// 0.1045 [m] --> 200- 10.45 [cm] = 189.55, which falls into pixel (189, 0)

		assertArrayEquals(intArray(189, 0), facade.getLocation(alien));
	}

	@Test 
	public void startMoveLeftMaxSpeedAtRightTime() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 500, spriteArrayForSize(2, 2));
		facade.startMoveLeft(alien);
		// maximum speed reached after 20/9 seconds
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2 / 9);
		}

		assertArrayEquals(doubleArray(3, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}


	@Test
	public void testAccellerationZeroWhenNotMoving() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		assertArrayEquals(doubleArray(0.0, 0.0), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
	}

	@Test
	public void testWalkAnimationLastFrame() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);

		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.005);
		for (int i = 0; i < m; i++) {
			facade.advanceTime(alien, 0.075);
		}

		assertEquals(sprites[8+m], facade.getCurrentSprite(alien));
	}

	@Test
	public void testDuckSpriteNotMoving() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);

		facade.startDuck(alien);
		for (int i = 0; i < 6; i++) {
			facade.advanceTime(alien, 0.2);
		}
		assertEquals(sprites[1], facade.getCurrentSprite(alien));
	}
	
	@Test
	public void testBoundaryRight() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);
		facade.startMoveRight(alien);
		// walking till past the last pixel
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2);
		}
		assertArrayEquals(intArray(1023, 0), facade.getLocation(alien));
	}
	
	@Test
	public void testBoundaryLeft() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);
		facade.startMoveLeft(alien);
		// walking to the left, so out of the field
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2);
		}
		assertArrayEquals(intArray(0, 0), facade.getLocation(alien));
	}
	
	@Test
	public void testBoundaryLeft2() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(800, 200, sprites);
		facade.startMoveLeft(alien);
		// falling and walking to the left
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2);
		}
		assertArrayEquals(intArray(0, 0), facade.getLocation(alien));
	}
	
	@Test
	public void testBoundaryGround() {
		IFacade facade = new Facade();
		
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 300, sprites);
		// falling to the ground
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2);
		}
		assertArrayEquals(intArray(0, 0), facade.getLocation(alien));
	}
	
	@Test
	public void testBoundaryGround2() {
		IFacade facade = new Facade();
		
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(250, 0, sprites);
		// jumping and falling to the ground
		facade.startJump(alien);
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2);
		}
		assertArrayEquals(intArray(250, 0), facade.getLocation(alien));
	}
	
	@Test
	public void testVelocityDucking() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);
		facade.startMoveLeft(alien);
		// walking till stop steep and then ducking
		for (int i = 0; i < 100 ; i++) {
			facade.advanceTime(alien, 0.2/9);
		}
		facade.startDuck(alien);
		assertArrayEquals(intArray(0, 0), facade.getLocation(alien));
	}
	
	@Test
	public void testVelocityJumpHighestPoint() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);
		facade.startJump(alien);
		// jumping till highest point
		for (int i = 0; i < 11 ; i++) {
			facade.advanceTime(alien, 0.08);
		}
		assertArrayEquals(doubleArray(0, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testAccelerationJump() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);
		facade.startJump(alien);
		// jumping till highest point
		for (int i = 0; i < 11 ; i++) {
			facade.advanceTime(alien, 0.08);
		}
		assertArrayEquals(doubleArray(0, -10), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testEndFallLocation() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);
		facade.startJump(alien);
		//jumping and falling
		for (int i = 0; i < 22 ; i++) {
			facade.advanceTime(alien, 0.08);
		}
		assertArrayEquals(intArray(0, 0), facade.getLocation(alien));
	}
		
	
	
	// TODO: add more tests
	// junit: @test exception (synthax opzoeken)
}
