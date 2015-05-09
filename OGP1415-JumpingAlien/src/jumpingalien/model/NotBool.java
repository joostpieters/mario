package jumpingalien.model;


public class NotBool extends UnaryExpression<Boolean, Boolean> {
	
	public NotBool(Expression<Boolean> expression1) {
		super(expression1);
	}
	public Boolean evaluate() {
		return  ( ! (this.getExpression1().evaluate()));
	}
}
