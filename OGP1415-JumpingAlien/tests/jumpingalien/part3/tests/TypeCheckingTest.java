package jumpingalien.part3.tests;

import static org.junit.Assert.assertFalse;
import jumpingalien.part3.facade.Facade;
import jumpingalien.part3.facade.IFacadePart3;
import jumpingalien.part3.programs.ParseOutcome;

import org.junit.Before;
import org.junit.Test;

public class TypeCheckingTest {
	
	private IFacadePart3 facade;
	
	@Before
	public void createFacade() {
		facade = new Facade();
	}
	// Expressions
	@Test
	public void illegalAndBool() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; bool c; a := 5; b := true; c:= a && b;");
		assertFalse(outcome.isSuccess());

	}
	
	@Test
	public void illegalAddition() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; double c; a := 5; b := true; c:= 1; c:= a + b;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void illegalDivision() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; bool c; a := 5; b := true; c:= a / b;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void illegalEquals() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; bool c; a := 5; b := true; c:= a == b;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void getHeightObject() {
		ParseOutcome<?> outcome = facade.parse("double a; double c; a := 2; c:= getheight a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void getWidthObject() {
		ParseOutcome<?> outcome = facade.parse("double a; double c; a := 2; c:= getwidth a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void getHpObject() {
		ParseOutcome<?> outcome = facade.parse("double a; double c; a := 2; c:= gethp a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void getTileExpr() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; object c; a := 2; b := false; c:= gettile(a,b) ;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void getX() {
		ParseOutcome<?> outcome = facade.parse("double a; double c; a := 2; c:= getx a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void getY() {
		ParseOutcome<?> outcome = facade.parse("double a; double c; a := 2; c:= getx a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void greaterEquals() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; double c; a := 2; b:= true; c:= a >= b;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void greaterThan() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; double c; a := 2; b:= true; c:= a > b;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void isAir() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; a:= 2; b:= isair a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void isDead() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; a:= 2; b:= isdead a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void isDucking() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; a:= 2; b:= isducking a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void isJumping() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; a:= 2; b:= isjumping a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void isMagma() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; a:= 2; b:= ismagma a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void isMazub() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; a:= 2; b:= ismazub a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void isMoving() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; a:= 2; b:= ismoving a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void isPassable() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; a:= 2; b:= ispassable a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void isPlant() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; a:= 2; b:= isplant a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void isShark() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; a:= 2; b:= isshark a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void isSlime() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; a:= 2; b:= isslime a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void isTerrain() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; a:= 2; b:= isterrain a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void isWater() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; a:= 2; b:= iswater a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void lessEquals() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; double c; a := 2; b:= true; c:= a <= b;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void lessThan() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; double c; a := 2; b:= true; c:= a < b;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void illegalMultiplication() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; double c; a := 5; b := true; c:= 1; c:= a * b;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void notBool() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; b := ! a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void illegalNotEquals() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; bool c; a := 5; b := true; c:= a != b;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void orBool() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; bool c; a := 5; b := true; c:= a || b;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void randomDouble() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; b := true; a:= random b;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void searchObject() {
		ParseOutcome<?> outcome = facade.parse("double a; object b; a:= 2; b:= searchobj a;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void sqrtDouble() {
		ParseOutcome<?> outcome = facade.parse("double a; object b; b:= self; a:= sqrt b;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void illegalSubtraction() {
		ParseOutcome<?> outcome = facade.parse("double a; bool b; double c; a := 5; b := true; c:= 1; c:= a - b;");
		assertFalse(outcome.isSuccess());
	}
	
	
	//Statements
	@Test
	public void assignVariable() {
		ParseOutcome<?> outcome = facade.parse("double a; a:= false;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void forEachIllegalWhere() {
		ParseOutcome<?> outcome = facade.parse("object o; foreach (any, o) where "
				+ "5 sort getx o descending do print getx o; done");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void forEachIllegalSort() {
		ParseOutcome<?> outcome = facade.parse("object o; foreach (any, o) "
				+ " sort isair a descending do print getx o; done");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void ifStatement() {
		ParseOutcome<?> outcome = facade.parse("object o; o:= self; if o then print o; fi");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void startRun() {
		ParseOutcome<?> outcome = facade.parse("object o; o:= self; start_run o;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void stopRun() {
		ParseOutcome<?> outcome = facade.parse("object o; o:= self; stop_run o;");
		assertFalse(outcome.isSuccess());
	}
	
	@Test
	public void waitStatement() {
		ParseOutcome<?> outcome = facade.parse("object o; o:= self; wait o;");
		assertFalse(outcome.isSuccess());
	}
	
}
