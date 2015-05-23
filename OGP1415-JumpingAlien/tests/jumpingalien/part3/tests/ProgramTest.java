package jumpingalien.part3.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Orientation;
import jumpingalien.model.Plant;
import jumpingalien.model.World;
import jumpingalien.model.program.Program;
import jumpingalien.part3.facade.Facade;
import jumpingalien.part3.facade.IFacadePart3;
import jumpingalien.part3.programs.ParseOutcome;
import jumpingalien.util.ModelException;

import org.junit.Test;


public class ProgramTest {

	@Test
	public void testBadformedBreak() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("double d := 1.0; if random 2 <= 1 then break; fi ");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
	}
	
	@Test
	public void testWellformedBreakWhile() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("double d := 1.0; while d > 1 do if random 2 <= 1 then break; fi done ");
		assumeTrue(outcome.isSuccess());
		assertTrue(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
	}
	
	@Test
	public void testWellformedBreakForEach() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("object o; foreach (any,o) do if random 2 <= 1 then break; fi done ");
		assumeTrue(outcome.isSuccess());
		assertTrue(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
	}
	
	@Test
	public void testWellformedActionStatement() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("double d := 1.0; if random 2 <= 1 then start_duck(); fi ");
		assumeTrue(outcome.isSuccess());
		assertTrue(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
	}
	
	@Test
	public void testWellformedActionStatement2() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("object o; foreach (any,o) do if random 2 <= 1 then start_duck; fi done");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
	}
	
	@Test
	public void testWellformedActionStatement3() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("object o; foreach (any,o) do if random 2 <= 1 then start_jump; fi done");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
	}
	
	@Test
	public void testWellformedActionStatement4() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("object o; foreach (any,o) do if random 2 <= 1 then start_run right; fi done");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
	}
	
	@Test
	public void testWellformedActionStatement5() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("object o; foreach (any,o) do if random 2 <= 1 then stop_duck; fi done");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
	}
	
	@Test
	public void testWellformedActionStatement6() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("object o; foreach (any,o) do if random 2 <= 1 then stop_jump; fi done");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
	}
	
	@Test
	public void testWellformedActionStatement7() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("object o; foreach (any,o) do if random 2 <= 1 then stop_run left; fi done");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
	}	
	
	@Test
	public void testWellformedActionStatementSkip() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("object o; foreach (any,o) do if random 2 <= 1 then skip; fi done");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
	}	
	
	@Test
	public void testWellformedActionStatementWait() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("object o; foreach (any,o) do if random 2 <= 1 then wait 1; fi done");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
	}
	
	
	@Test
	public void StopRunning() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("object o; double a; double b; double c; a:= 2; b:= 2; o := gettile(a,b); c := gethp o;");
		assumeTrue(outcome.isSuccess());
		Program program = (Program) outcome.getResult();
		World world = facade.createWorld(500, 5, 5, 5, 5, 1, 1);
		Buzam buzam = facade.createBuzamWithProgram(0, 0, spriteArrayForSize(3,3), program);
		facade.addBuzam(world, buzam);
		program.execute(0.1);
		assertFalse(program.isRunning());
	}
	
	@Test
	public void runProgramFromAdvanceTime() {
		IFacadePart3 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, 1);
		Mazub alien = facade.createMazub(200, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		ParseOutcome<?> outcome = facade.parse("object o; start_run left;");
		Program program = (Program) outcome.getResult();
		Buzam buzam = facade.createBuzamWithProgram(20, 499, spriteArrayForSize(3, 3), program);
		facade.addBuzam(world, buzam);
		facade.advanceTime(world, 0.001);
		// the start speed of buzam is 1m/s
		assertEquals(buzam.getXSpeed(), 1, 0.01);
		assertEquals(buzam.getOrientation(), Orientation.LEFT);
	}
	
	@Test
	public void runProgramWhithSmallDt() {
		IFacadePart3 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, 1);
		Mazub alien = facade.createMazub(200, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		ParseOutcome<?> outcome = facade.parse("object o; start_run left;");
		Program program = (Program) outcome.getResult();
		Buzam buzam = facade.createBuzamWithProgram(20, 499, spriteArrayForSize(3, 3), program);
		facade.addBuzam(world, buzam);
		facade.advanceTime(world, 0.0001);
		// the start speed of buzam is 1m/s
		assertEquals(buzam.getXSpeed(), 1, 0.01);
		assertEquals(buzam.getOrientation(), Orientation.LEFT);
	}
	
	@Test(expected = ModelException.class)
	public void PlantWithProgramIllegalPos() {
		IFacadePart3 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, 1);
		Mazub alien = facade.createMazub(200, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		ParseOutcome<?> outcome = facade.parse("object o; start_run left;");
		Program program = (Program) outcome.getResult();
		@SuppressWarnings("unused")
		Plant plant = facade.createPlantWithProgram(20, -2, spriteArrayForSize(3, 3, 2), program);
	}
	
	@Test(expected = ModelException.class)
	public void PlantWithProgramIllegalSprite() {
		IFacadePart3 facade = new Facade();
		World world = facade.createWorld(500, 3, 3, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, 1);
		Mazub alien = facade.createMazub(200, 499, spriteArrayForSize(3, 3));
		facade.setMazub(world, alien);
		ParseOutcome<?> outcome = facade.parse("object o; start_run left;");
		Program program = (Program) outcome.getResult();
		@SuppressWarnings("unused")
		Plant plant = facade.createPlantWithProgram(20, 2, spriteArrayForSize(3, 3), program);
	}
	
}
