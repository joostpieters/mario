package jumpingalien.model;
import jumpingalien.model.Expression;
import jumpingalien.model.UnaryExpression;


public class NotBool extends UnaryExpression<Boolean> {
	
	public NotBool(Expression<Boolean> expression1) {
		super(expression1);
	}
	public Boolean evaluate() {
		return  ( ! (this.getExpression1().evaluate()));
	}
}
