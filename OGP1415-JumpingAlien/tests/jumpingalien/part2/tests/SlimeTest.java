package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Slime;
import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.util.ModelException;

import org.junit.Test;

public class SlimeTest {
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
}
