package jumpingalien.model;
import jumpingalien.model.Expression;
import jumpingalien.model.UnaryExpression;


public class BoolFalse extends Expression {
	
	public BoolFalse() {
		
	}
	public Boolean evaluate() {
		return  false;
	}
}