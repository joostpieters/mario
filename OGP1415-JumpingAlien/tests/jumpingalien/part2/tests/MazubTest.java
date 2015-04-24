package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.doubleArray;
import static jumpingalien.tests.util.TestUtils.intArray;
import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.World;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.part1.facade.IFacade;
import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Test;

public class MazubTest {
	
	public static final int FEATURE_AIR = 0;
	public static final int FEATURE_SOLID = 1;
	public static final int FEATURE_WATER = 2;
	public static final int FEATURE_MAGMA = 3;
	
	@Test
	public void startMoveRightMaxSpeedAtRightTime() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		facade.startMoveRight(alien);
		// maximum speed reached after 20/9 seconds
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(world, 0.2 / 9);
		}
		assertArrayEquals(doubleArray(3, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void startMoveLeftCorrect() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub alien = facade.createMazub(200, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		facade.startMoveLeft(alien);
		facade.advanceTime(world, 0.1);

		// x_new [m] = 0 + 1 [m/s] * 0.1 [s] + 1/2 0.9 [m/s^2] * (0.1 [s])^2 =
		// 0.1045 [m] --> 200- 10.45 [cm] = 189.55, which falls into pixel (189, 0)

		assertArrayEquals(intArray(189, 499), facade.getLocation(alien));
	}
// TODO tot hier al testen aangepast
	@Test 
	public void startMoveLeftMaxSpeedAtRightTime() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub alien = facade.createMazub(499, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		facade.startMoveLeft(alien);
		// maximum speed reached after 20/9 seconds
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(world, 0.2 / 9);
		}

		assertArrayEquals(doubleArray(3, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}


	@Test
	public void testAccellerationZeroWhenNotMoving() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub alien = facade.createMazub(499, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		assertArrayEquals(doubleArray(0.0, 0.0), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
	}

	@Test
	// TODO geen idee wat er fout gaat, sprites zijn vaag
	public void testWalkAnimationLastFrame() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(499, 499, sprites);
		facade.setMazub(world, alien);

		facade.startMoveRight(alien);
		facade.advanceTime(world, 0.005);
		for (int i = 0; i < m; i++) {
			facade.advanceTime(world, 0.075);
		}

		assertEquals(sprites[8+m], facade.getCurrentSprite(alien));
	}

	@Test
	public void testDuckSpriteNotMoving() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(499, 499, sprites);
		facade.setMazub(world, alien);

		facade.startDuck(alien);
		for (int i = 0; i < 6; i++) {
			facade.advanceTime(world, 0.2);
		}
		assertEquals(sprites[1], facade.getCurrentSprite(alien));
	}
	
	@Test
	public void testSolidFeatureWallRight() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 1, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(499, 499, sprites);
		facade.setMazub(world, alien);
		facade.startMoveRight(alien);
		// walking till the right end of the tile (and to a wall)
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(world, 0.2);
		}
		assertArrayEquals(intArray(498, 499), facade.getLocation(alien));
	}
	
	@Test
	public void testSolidFeatureWallLeft() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 0, 1, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(499, 600, sprites);
		facade.setMazub(world, alien);
		facade.startMoveLeft(alien);
		// walking till the leftht end of the tile (and to a left wall)
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(world, 0.2);
		}
		assertArrayEquals(intArray(500, 499), facade.getLocation(alien));
	}
	
	@Test
	public void testBoundaryGround() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(50, 750, sprites);
		facade.setMazub(world, alien);
		// falling to the ground
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(world, 0.2);
		}
		// alien has fallen and stands on the solid tile
		assertArrayEquals(intArray(50, 499), facade.getLocation(alien));
	}
	
	@Test
	public void testBoundaryGround2() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(50, 550, sprites);
		facade.setMazub(world, alien);
		// falling, jumping and falling to the ground
		for (int i = 0; i < 20; i++) {
			facade.advanceTime(world, 0.2);
		}
		facade.startJump(alien);
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(world, 0.2);
		}
		assertArrayEquals(intArray(50, 499), facade.getLocation(alien));
	}
	
	@Test
	public void testVelocityDucking() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(2, 499, sprites);
		facade.setMazub(world, alien);
		facade.startMoveRight(alien);
		// walking till maximum speed and then ducking
		for (int i = 0; i < 100 ; i++) {
			facade.advanceTime(world, 0.2/9);
		}
		facade.startDuck(alien);
		facade.advanceTime(world, 0.005);
		assertArrayEquals(doubleArray(1, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}
	@Test
	// TODO laten werken
	public void testVelocityJumpHighestPoint() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(50, 499, sprites);
		facade.setMazub(world, alien);
		facade.startJump(alien);
		// jumping till highest point
		for (int i = 0; i < 10 ; i++) {
			facade.advanceTime(world, 0.08);
		}
		// at the heighest point of the jump, the vertical velocity is equal to zero
		assertArrayEquals(doubleArray(0, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	// TODO laten werken
	public void testAccelerationJump() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(50, 499, sprites);
		facade.setMazub(world, alien);
		facade.startJump(alien);
		// jumping till highest point
		for (int i = 0; i < 12 ; i++) {
			facade.advanceTime(world, 0.08);
			assertArrayEquals(doubleArray(0, -10), facade.getAcceleration(alien),
					Util.DEFAULT_EPSILON);
		}		
	}
	
	@Test
	public void testFallAcceleration() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(50, 499, sprites);
		facade.setMazub(world, alien);
		facade.startJump(alien);
		// jumping till highest point
		for (int i = 0; i < 5; i++) {
			facade.advanceTime(world, 0.08);
		}
		// 739 = 499 + 240
		assertArrayEquals(intArray(50, 739), facade.getLocation(alien));
	}
	
	@Test
	public void testEndFallLocation() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(50, 499, sprites);
		facade.setMazub(world, alien);
		facade.startJump(alien);
		//jumping and falling
		for (int i = 0; i < 22 ; i++) {
			facade.advanceTime(world, 0.08);
		}
		assertArrayEquals(intArray(50, 499), facade.getLocation(alien));
	}
	
	@Test
	public void testSpeedWhenJumping() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(50, 499, sprites);
		facade.setMazub(world, alien);
		// start moving to the right and accelerating
		facade.startMoveRight(alien);
		// mazub reaches it's maximum horizontal speed after
		// 2.23s (=28*0.08)
		for (int i = 0; i < 29 ; i++) {
			facade.advanceTime(world, 0.08);
		}
		// mazub starts jumping when he is moving horizontally
		// at a speed of 3m/s (maxSpeed)
		facade.startJump(alien);
		for (int i = 0; i < 1 ; i++) {
			facade.advanceTime(world, 0.0001);
		}
		assertArrayEquals(doubleArray(3, 8), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testSpeedWhenJumpingAndDucking() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(50, 499, sprites);
		facade.setMazub(world, alien);
		// start moving to the right and accelerating
		facade.startMoveRight(alien);
		// mazub reaches it's maximum horizontal speed after
		// 2.23s (=28*0.08)
		for (int i = 0; i < 29 ; i++) {
			facade.advanceTime(world, 0.08);
		}
		// mazub starts jumping when he is moving horizontally
		// at a speed of 3m/s (maxSpeed)
		facade.startJump(alien);
		for (int i = 0; i < 7 ; i++) {
			facade.advanceTime(world, 0.08);
		}
		// mazub starts ducking when jumping
		facade.startDuck(alien);
		for (int i = 0; i < 3 ; i++) {
			facade.advanceTime(world, 0.08);
		}
		// the horizontal speed of mazub must be 1m/s
		assertEquals(1, facade.getVelocity(alien)[0], Util.DEFAULT_EPSILON);
	}
	
	@Test(expected = ModelException.class)
	public void illegalSprite() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 3, 11 + 2 * m);
		Mazub alien = facade.createMazub(50, 499, sprites);
		facade.setMazub(world, alien);
		facade.startMoveRight(alien);
		for (int i = 0; i < 29 ; i++) {
			facade.advanceTime(world, 0.08);
		}
	}
	
	@Test(expected = ModelException.class)
	public void illegalPosition() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		// an illegal horizontal position of mazub
		Mazub alien = facade.createMazub(-5, 5, sprites);
		facade.setMazub(world, alien);
		facade.startMoveRight(alien);
		facade.advanceTime(world, 0.08);
	}
	
	@Test(expected = ModelException.class)
	public void illegalInitStartSpeed() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(50, 499, sprites, -5, 3);
		facade.setMazub(world, alien);
		// the adapted initStartSpeed is negative
		facade.startMoveRight(alien);
	}
	
	@Test(expected = ModelException.class)
	public void illegalMaxSpeed() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		// a negative maxspeed
		Mazub alien = facade.createMazub(50, 499, sprites, 2, -666);
		facade.setMazub(world, alien);
		facade.startMoveRight(alien);
	}
	
	@Test(expected = ModelException.class)
	public void illegalSpeed() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		// the maxspeed is smaller than the initstartSpeed
		Mazub alien = facade.createMazub(50, 499, sprites, 2, 1);
		facade.setMazub(world, alien);
		facade.startMoveRight(alien);
	}
	
	@Test(expected = ModelException.class)
	public void illegalnegativeDt() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		facade.setGeologicalFeature(world, 1, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(50, 499, sprites);
		facade.setMazub(world, alien);
		facade.advanceTime(world, -1);
	}
	
	@Test(expected = ModelException.class)
	public void illegalbigDt() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(499, 499, sprites);
		facade.setMazub(world, alien);
		facade.advanceTime(world, 2.01);
	}
	
	@Test
	public void otherInitStartSpeed() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(499, 499, sprites);
		facade.setMazub(world, alien);
		facade.startMoveRight(alien);
		assertEquals(1, facade.getVelocity(alien)[0], Util.DEFAULT_EPSILON);
	}
	

	@Test
	public void MaxSpeedAfterDucking() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(2, 499, sprites);
		facade.setMazub(world, alien);
		facade.startDuck(alien);
		facade.endDuck(alien);
		facade.startMoveRight(alien);
		// maximum speed reached after 20/9 seconds
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(world, 0.2 / 9);
		}
		assertArrayEquals(doubleArray(3, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}

	@Test(expected = ModelException.class)
	// TODO dit is een fout in de code ergens
	public void illegalPosition2() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(4000, 666, sprites);
		facade.setMazub(world, alien);
		facade.startMoveRight(alien);
	}
	
	@Test
	public void JumpAndEndJump() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(499, 499, sprites);
		facade.setMazub(world, alien);
		// the alien starts jumping
		facade.startJump(alien);
		for (int i = 0; i < 4 ; i++) {
			facade.advanceTime(world, 0.08);
		}
		// while the alien is jumping, endJump is invoced,
		// ySpeed becomes 0 and the alien falls
		facade.endJump(alien);
		for (int i = 0; i < 11 ; i++) {
			facade.advanceTime(world, 0.08);
		}
		assertArrayEquals(intArray(499, 499), facade.getLocation(alien));
	}
	
	@Test
	public void testEndMoveRight() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		facade.startMoveRight(alien);
		for (int i = 0; i < 15; i++) {
			facade.advanceTime(world, 0.1);
		}
		alien.endMoveRight();
		assertArrayEquals(doubleArray(0, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
		assertArrayEquals(doubleArray(0, 0), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testEndMoveLeft() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub alien = facade.createMazub(400, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		facade.startMoveLeft(alien);
		for (int i = 0; i < 15; i++) {
			facade.advanceTime(world, 0.1);
		}
		alien.endMoveRight();
		assertArrayEquals(doubleArray(0, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
		assertArrayEquals(doubleArray(0, 0), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testJumpWhileJumping() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub alien = facade.createMazub(50, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		facade.startJump(alien);
		for (int i = 0; i < 5; i++) {
			facade.advanceTime(world, 0.1);
		}
		assertArrayEquals(doubleArray(0, -10), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
		assertArrayEquals(doubleArray(0, 3), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
		facade.startJump(alien);
		// the startJump didn't adapt the velocity or acceleration
		// because alien wasn't on the ground
		assertArrayEquals(doubleArray(0, -10), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
		assertArrayEquals(doubleArray(0, 3), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testEndJump() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub alien = facade.createMazub(50, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		facade.startJump(alien);
		for (int i = 0; i < 2; i++) {
			facade.advanceTime(world, 0.1);
		}
		facade.endJump(alien);
		assertArrayEquals(doubleArray(0, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	// TODO werkt nog niet
	public void testCollidingPlants() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub alien = facade.createMazub(50, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		Plant plant = facade.createPlant(130, 500, spriteArrayForSize(2, 2));
		facade.addPlant(world, plant);
		facade.startMoveRight(alien);
		for (int i = 0; i < 15; i++) {
			facade.advanceTime(world, 0.1);
		}
		// the alien collided with the plant, so his hitpoints augmented with 50
		assertEquals(150, facade.getNbHitPoints(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	// TODO werkt nog niet
	public void testCollidingSlimes() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub alien = facade.createMazub(50, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		Shark shark = facade.createShark(130, 500, spriteArrayForSize(2, 2));
		facade.addShark(world, shark);
		School school = facade.createSchool();
		Slime slime = facade.createSlime(130, 500, spriteArrayForSize(2, 2), school);
		facade.addSlime(world, slime);
		for (int i = 0; i < 15; i++) {
			facade.advanceTime(world, 0.1);
		}
		// the alien collided with the slime
		assertEquals(150, facade.getNbHitPoints(alien),
				Util.DEFAULT_EPSILON);
	}
	
	@Test
	// TODO werkt nog niet
	public void testCollidingSharks() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub alien = facade.createMazub(50, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		Shark shark = facade.createShark(130, 500, spriteArrayForSize(2, 2));
		facade.addShark(world, shark);
		facade.startMoveRight(alien);
		for (int i = 0; i < 15; i++) {
			facade.advanceTime(world, 0.1);
		}
		// the alien collided with the shark
		assertEquals(150, facade.getNbHitPoints(alien),
				Util.DEFAULT_EPSILON);
	}
	
	
}
