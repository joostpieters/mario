package jumpingalien.model;

public class Constant<T> extends UnaryExpression {

	public Constant(Expression<T> expression1) {
		super(expression1);
	}

	@Override
	protected Double evaluate() {
		return (double) this.getExpression1().evaluate();
	}
	
	
}
