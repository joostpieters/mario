package jumpingalien.model;

public class Constant extends UnaryExpression<Double> {

	public Constant(Expression<Double> expression1) {
		super(expression1);
	}

	@Override
	protected Double evaluate() {
		return this.getExpression1().evaluate();
	}
	
	
}
