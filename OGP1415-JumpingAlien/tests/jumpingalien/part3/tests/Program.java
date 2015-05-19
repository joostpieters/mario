package jumpingalien.part3.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import jumpingalien.part3.facade.Facade;
import jumpingalien.part3.facade.IFacadePart3;
import jumpingalien.part3.programs.ParseOutcome;

import org.junit.Test;

public class Program {

	@Test
	public void testWellformedBreak() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("double d := 1.0; if random 2 <= 1 then break; fi ");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
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
		ParseOutcome<?> outcome = facade.parse("double d := 1.0; while d < 3 do if random 2 <= 1 then start_duck(); fi done");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((jumpingalien.model.program.Program) outcome.getResult()));
	}
	
	
}
