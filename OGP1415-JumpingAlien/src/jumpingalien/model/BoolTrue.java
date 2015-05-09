package jumpingalien.model;
import jumpingalien.model.Expression;
import jumpingalien.model.UnaryExpression;


public class BoolTrue extends Expression {
	
	public BoolTrue() {
		
	}
	public Boolean evaluate() {
		return  true;
	}
}
