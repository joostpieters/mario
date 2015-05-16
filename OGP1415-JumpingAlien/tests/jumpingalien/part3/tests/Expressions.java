package jumpingalien.part3.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jumpingalien.model.Tile;
import jumpingalien.model.Type;
import jumpingalien.model.World;
import jumpingalien.model.exceptions.IllegalNbTilesException;
import jumpingalien.model.exceptions.IllegalTargetTileException;
import jumpingalien.model.exceptions.IllegalTileSizeException;
import jumpingalien.model.exceptions.IllegalVisibleWindowException;
import jumpingalien.model.program.Program;
import jumpingalien.model.program.expression.Addition;
import jumpingalien.model.program.expression.BoolTrue;
import jumpingalien.model.program.expression.Constant;
import jumpingalien.model.program.expression.Expression;
import jumpingalien.model.program.expression.GetTileExpression;
import jumpingalien.model.program.expression.IsPlant;
import jumpingalien.model.program.statement.PrintStatement;
import jumpingalien.model.program.statement.Statement;

import org.junit.Test;

public class Expressions {
	
	
	@Test
	public void testList() {
		List<Object> list = new ArrayList<Object>();
		list.add(5);
		list.add(8);
		list.add(9);
		System.out.println(list.toString());
		list.remove(list.get(0));
		System.out.println(list.toString());

	}
	
	@Test(expected = ClassCastException.class)
	public void testException() {
		Expression expr1 = new Constant(12);
		Expression expr2 = new BoolTrue();
		Expression<Double> add = new Addition(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(add);
		Program program = new Program(stat, map);
		add.evaluate(program);
	}
	
//	@Test(expected = ClassCastException.class)
//	public void testException2() throws IllegalArgumentException, IllegalTileSizeException, IllegalTargetTileException, IllegalVisibleWindowException, IllegalNbTilesException {
//		World world = new World(1,1,1,1,1,0,0);
//		Expression expr1 = new Constant(0);
//		Expression expr2 = new Constant(0);
//		Expression expr3 = new GetTileExpression(expr1, expr2);
//		Expression expr4 = new IsPlant(expr3);
//		Map<String, Type> map = new HashMap<String, Type>();
//		Statement stat = new PrintStatement(expr3);
//		Program program = new Program(stat, map);
//		expr4.evaluate(program);
//	}

}
