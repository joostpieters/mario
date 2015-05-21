package jumpingalien.part3.tests;
import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.model.Buzam;
import jumpingalien.model.Type;
import jumpingalien.model.exceptions.IllegalPositionException;
import jumpingalien.model.exceptions.IllegalSpriteException;
import jumpingalien.model.program.Program;
import jumpingalien.model.program.expression.Addition;
import jumpingalien.model.program.expression.AndBool;
import jumpingalien.model.program.expression.BoolFalse;
import jumpingalien.model.program.expression.BoolTrue;
import jumpingalien.model.program.expression.Constant;
import jumpingalien.model.program.expression.Division;
import jumpingalien.model.program.expression.Equals;
import jumpingalien.model.program.expression.Expression;
import jumpingalien.model.program.expression.GreaterEquals;
import jumpingalien.model.program.expression.GreaterThan;
import jumpingalien.model.program.expression.LessEquals;
import jumpingalien.model.program.expression.LessThan;
import jumpingalien.model.program.expression.Multiplication;
import jumpingalien.model.program.expression.NotEquals;
import jumpingalien.model.program.expression.ObjectNull;
import jumpingalien.model.program.expression.ObjectSelf;
import jumpingalien.model.program.expression.OrBool;
import jumpingalien.model.program.expression.Subtraction;
import jumpingalien.model.program.statement.PrintStatement;
import jumpingalien.model.program.statement.Statement;

import org.junit.Test;

@SuppressWarnings({"unchecked", "rawtypes"})
public class BinaryExpressionTest {
	
	@Test
	public void testAddition() {
		Expression expr1 = new Constant(5);
		Expression expr2 = new Constant(3);
		Expression<Double> addition = new Addition(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(addition);
		Program program = new Program(stat, map);
		assertEquals(addition.evaluate(program), (Double) 8.0);			
	}
	
	@Test
	public void testAddition2() {
		Expression expr1 = new Constant(5);
		Expression expr2 = new Constant(-8);
		Expression<Double> addition = new Addition(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(addition);
		Program program = new Program(stat, map);
		assertEquals(addition.evaluate(program), (Double) (-3.0));			
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddition3() {
		Expression expr1 = new Constant(5);
		// no double
		Expression expr2 = new BoolTrue();
		// 5 + true
		Expression<Double> addition = new Addition(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(addition);
		Program program = new Program(stat, map);
		assertEquals(addition.evaluate(program), (Double) 8.0);			
	}
	
	@Test
	public void testMultiplication() {
		Expression expr1 = new Constant(5);
		Expression expr2 = new Constant(3);
		Expression<Double> multiplication = new Multiplication(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(multiplication);
		Program program = new Program(stat, map);
		assertEquals(multiplication.evaluate(program), (Double) 15.0);			
	}
	
	@Test
	public void testMultiplication2() {
		Expression expr1 = new Constant(4);
		Expression expr2 = new Constant(-2);
		Expression<Double> multiplication = new Multiplication(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(multiplication);
		Program program = new Program(stat, map);
		assertEquals(multiplication.evaluate(program), (Double) (-8.0));			
	}
	
	@Test
	public void testDivision() {
		Expression expr1 = new Constant(5);
		Expression expr2 = new Constant(3);
		Expression<Double> division = new Division(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(division);
		Program program = new Program(stat, map);
		assertEquals(division.evaluate(program), (Double) (5.0/3));			
	}
	
	@Test
	public void testDivision2() {
		Expression expr1 = new Constant(-5);
		Expression expr2 = new Constant(3);
		Expression<Double> division = new Division(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(division);
		Program program = new Program(stat, map);
		assertEquals(division.evaluate(program), (Double) (-5.0/3));			
	}
	
	@Test
	public void testSubtraction() {
		Expression expr1 = new Constant(5);
		Expression expr2 = new Constant(3);
		Expression<Double> subtraction = new Subtraction(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(subtraction);
		Program program = new Program(stat, map);
		assertEquals(subtraction.evaluate(program), (Double) 2.0);			
	}
	
	@Test
	public void testSubtraction2() {
		Expression expr1 = new Constant(-5);
		Expression expr2 = new Constant(-3);
		Expression<Double> subtraction = new Subtraction(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(subtraction);
		Program program = new Program(stat, map);
		assertEquals(subtraction.evaluate(program), (Double) (-2.0));			
	}
	
	@Test
	public void testEquals() {
		Expression expr1 = new Constant(5);
		Expression expr2 = new Constant(3);
		Expression<Boolean> equals = new Equals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(equals);
		Program program = new Program(stat, map);
		assertEquals(equals.evaluate(program), false);		
	}
	
	@Test
	public void testEquals2() {
		Expression expr1 = new Constant(3);
		Expression expr2 = new Constant(3);
		Expression<Boolean> equals = new Equals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(equals);
		Program program = new Program(stat, map);
		assertTrue(equals.evaluate(program));		
	}
	
	@Test
	public void testEquals3() throws IllegalPositionException, IllegalSpriteException {
		Expression expr1 = new ObjectSelf();
		Expression expr2 = new ObjectSelf();
		Expression<Boolean> equals = new Equals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(equals);
		Program program = new Program(stat, map);
		Buzam buzam = new Buzam(20, 30, spriteArrayForSize(3, 3));
		program.setGameObject(buzam);
		assertTrue(equals.evaluate(program));		
	}
	
	@Test
	public void testEquals4() throws IllegalPositionException, IllegalSpriteException {
		Expression expr1 = new ObjectNull();
		Expression expr2 = new ObjectNull();
		Expression<Boolean> equals = new Equals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(equals);
		Program program = new Program(stat, map);
		Buzam buzam = new Buzam(20, 30, spriteArrayForSize(3, 3));
		program.setGameObject(buzam);
		assertFalse(equals.evaluate(program));		
	}
	
	@Test
	public void testNotEquals() {
		Expression expr1 = new Constant(5);
		Expression expr2 = new Constant(3);
		Expression<Boolean> notEquals = new NotEquals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(notEquals);
		Program program = new Program(stat, map);
		assertEquals(notEquals.evaluate(program), true);		
	}
	
	@Test
	public void testNotEquals2() {
		Expression expr1 = new Constant(3);
		Expression expr2 = new Constant(3);
		Expression<Boolean> equals = new NotEquals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(equals);
		Program program = new Program(stat, map);
		assertFalse(equals.evaluate(program));		
	}
	
	@Test
	public void testNotEquals3() throws IllegalPositionException, IllegalSpriteException {
		Expression expr1 = new ObjectSelf();
		Expression expr2 = new ObjectSelf();
		Expression<Boolean> equals = new NotEquals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(equals);
		Program program = new Program(stat, map);
		Buzam buzam = new Buzam(20, 30, spriteArrayForSize(3, 3));
		program.setGameObject(buzam);
		assertFalse(equals.evaluate(program));		
	}
	
	@Test
	public void testNotEquals4() throws IllegalPositionException, IllegalSpriteException {
		Expression expr1 = new ObjectNull();
		Expression expr2 = new ObjectNull();
		Expression<Boolean> notEquals = new NotEquals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(notEquals);
		Program program = new Program(stat, map);
		Buzam buzam = new Buzam(20, 30, spriteArrayForSize(3, 3));
		program.setGameObject(buzam);
		assertTrue(notEquals.evaluate(program));		
	}
	
	@Test
	public void testGreaterThan() {
		Expression expr1 = new Constant(5);
		Expression expr2 = new Constant(3);
		Expression<Boolean> greaterThan = new GreaterThan(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(greaterThan);
		Program program = new Program(stat, map);
		assertEquals(greaterThan.evaluate(program), true);			
	}
	
	@Test
	public void testGreaterThan2() {
		Expression expr1 = new Constant(3);
		Expression expr2 = new Constant(5);
		Expression<Boolean> greaterThan = new GreaterThan(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(greaterThan);
		Program program = new Program(stat, map);
		assertEquals(greaterThan.evaluate(program), false);			
	}
	
	@Test
	public void testLessThan() {
		Expression expr1 = new Constant(5);
		Expression expr2 = new Constant(3);
		Expression<Boolean> lessThan = new LessThan(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(lessThan);
		Program program = new Program(stat, map);
		assertEquals(lessThan.evaluate(program), false);			
	}
	
	@Test
	public void testLessThan2() {
		Expression expr1 = new Constant(3);
		Expression expr2 = new Constant(5);
		Expression<Boolean> lessThan = new LessThan(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(lessThan);
		Program program = new Program(stat, map);
		assertEquals(lessThan.evaluate(program), true);			
	}
	
	@Test
	public void testGreaterEquals() {
		Expression expr1 = new Constant(5);
		Expression expr2 = new Constant(3);
		Expression<Boolean> greaterEquals = new GreaterEquals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(greaterEquals);
		Program program = new Program(stat, map);
		assertEquals(greaterEquals.evaluate(program), true);			
	}
	
	@Test
	public void testGreaterEquals2() {
		Expression expr1 = new Constant(5);
		Expression expr2 = new Constant(5);
		Expression<Boolean> greaterEquals = new GreaterEquals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(greaterEquals);
		Program program = new Program(stat, map);
		assertEquals(greaterEquals.evaluate(program), true);			
	}
	
	@Test
	public void testGreaterEquals3() {
		Expression expr1 = new Constant(3);
		Expression expr2 = new Constant(5);
		Expression<Boolean> greaterEquals = new GreaterEquals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(greaterEquals);
		Program program = new Program(stat, map);
		assertEquals(greaterEquals.evaluate(program), false);			
	}
	
	@Test
	public void testLessEquals() {
		Expression expr1 = new Constant(5);
		Expression expr2 = new Constant(3);
		Expression<Boolean> lessEquals = new LessEquals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(lessEquals);
		Program program = new Program(stat, map);
		assertEquals(lessEquals.evaluate(program), false);			
	}
	
	@Test
	public void testLessEquals2() {
		Expression expr1 = new Constant(5);
		Expression expr2 = new Constant(5);
		Expression<Boolean> lessEquals = new LessEquals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(lessEquals);
		Program program = new Program(stat, map);
		assertEquals(lessEquals.evaluate(program), true);			
	}
	
	@Test
	public void testLessEquals3() {
		Expression expr1 = new Constant(3);
		Expression expr2 = new Constant(5);
		Expression<Boolean> lessEquals = new LessEquals(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(lessEquals);
		Program program = new Program(stat, map);
		assertEquals(lessEquals.evaluate(program), true);			
	}
	
	@Test
	public void testAndBool1() {
		Expression expr1 = new BoolTrue();
		Expression expr2 = new BoolTrue();
		Expression<Boolean> andBool = new AndBool(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(andBool);
		Program program = new Program(stat, map);
		assertEquals(andBool.evaluate(program), true);			
	}
	
	@Test
	public void testAndBool2() {
		Expression expr1 = new BoolTrue();
		Expression expr2 = new BoolFalse();
		Expression<Boolean> andBool = new AndBool(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(andBool);
		Program program = new Program(stat, map);
		assertEquals(andBool.evaluate(program), false);			
	}
	
	@Test
	public void testAndBool3() {
		Expression expr1 = new BoolFalse();
		Expression expr2 = new BoolFalse();
		Expression<Boolean> andBool = new AndBool(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(andBool);
		Program program = new Program(stat, map);
		assertEquals(andBool.evaluate(program), false);			
	}
	
	@Test
	public void testOrBool1() {
		Expression expr1 = new BoolTrue();
		Expression expr2 = new BoolTrue();
		Expression<Boolean> orBool = new OrBool(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(orBool);
		Program program = new Program(stat, map);
		assertEquals(orBool.evaluate(program), true);			
	}
	
	@Test
	public void testOrBool2() {
		Expression expr1 = new BoolTrue();
		Expression expr2 = new BoolFalse();
		Expression<Boolean> orBool = new OrBool(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(orBool);
		Program program = new Program(stat, map);
		assertEquals(orBool.evaluate(program), true);			
	}
	
	@Test
	public void testOrBool3() {
		Expression expr1 = new BoolFalse();
		Expression expr2 = new BoolFalse();
		Expression<Boolean> orBool = new OrBool(expr1, expr2);
		Map<String, Type> map = new HashMap<String, Type>();
		Statement stat = new PrintStatement(orBool);
		Program program = new Program(stat, map);
		assertEquals(orBool.evaluate(program), false);			
	}
	
	
}
