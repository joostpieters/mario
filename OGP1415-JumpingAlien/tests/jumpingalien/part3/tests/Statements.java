package jumpingalien.part3.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.model.Plant;
import jumpingalien.model.Type;
import jumpingalien.model.World;
import jumpingalien.model.program.Program;
import jumpingalien.model.program.expression.Addition;
import jumpingalien.model.program.expression.Constant;
import jumpingalien.model.program.expression.Expression;
import jumpingalien.model.program.expression.GetX;
import jumpingalien.model.program.statement.PrintStatement;
import jumpingalien.model.program.statement.Statement;
import jumpingalien.part3.facade.Facade;
import jumpingalien.part3.facade.IFacadePart3;
import jumpingalien.part3.programs.ParseOutcome;

import org.junit.Test;

public class Statements {
	@Test
	public void ForEach() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("double d := 1.0; foreach (any, o) where (isshark o) do start_jump; done");
		Program program = (Program) outcome.getResult();
		Plant plant1 = facade.createPlant(0, 0, spriteArrayForSize(3, 3), program);
		Plant plant2 = facade.createPlant(10, 0, spriteArrayForSize(3, 3));
		Plant plant3 = facade.createPlant(20, 0, spriteArrayForSize(3, 3));
		facade.createMazub(100, 100, spriteArrayForSize(3, 3), program);
		facade.createShark(200, 0, spriteArrayForSize(3, 3));
		World world = facade.createWorld(500, 2, 2, 2, 2, 1, 1);
		// Dit zou helemaal niet werken
	}
	
	
}
