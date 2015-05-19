package jumpingalien.part3.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Shark;
import jumpingalien.model.Type;
import jumpingalien.model.World;
import jumpingalien.model.program.Program;
import jumpingalien.model.program.expression.Addition;
import jumpingalien.model.program.expression.Constant;
import jumpingalien.model.program.expression.Expression;
import jumpingalien.model.program.expression.GetX;
import jumpingalien.model.program.statement.PrintStatement;
import jumpingalien.part3.facade.Facade;
import jumpingalien.part3.facade.IFacadePart3;
import jumpingalien.part3.programs.ParseOutcome;

import org.junit.Test;

public class StatementTest {
	@Test
	public void ForEach() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("double d := 1.0; foreach (any, o) where (isshark o) sort getx o descending do print getx o; done");
		Program program = (Program) outcome.getResult();
		Plant plant1 = facade.createPlantWithProgram(0, 0, spriteArrayForSize(3, 3, 2), program);
		Plant plant2 = facade.createPlant(10, 0, spriteArrayForSize(3, 3, 2));
		Plant plant3 = facade.createPlant(20, 0, spriteArrayForSize(3, 3, 2));
		Mazub mazub = facade.createMazub(100, 100, spriteArrayForSize(3, 3));
		Shark shark = facade.createShark(200, 0, spriteArrayForSize(3, 3, 2));
		Shark shark2 = facade.createShark(250, 0, spriteArrayForSize(3, 3, 2));
		Shark shark3 = facade.createShark(300, 0, spriteArrayForSize(3, 3, 2));
		World world = facade.createWorld(500, 2, 2, 2, 2, 1, 1);
		facade.addPlant(world, plant1);
		facade.addPlant(world, plant2);
		facade.addPlant(world, plant3);
		facade.setMazub(world, mazub);
		facade.addShark(world, shark3);
		facade.addShark(world, shark2);
		facade.addShark(world, shark);
		program.execute(0.1);
		
	}
	
	
}
