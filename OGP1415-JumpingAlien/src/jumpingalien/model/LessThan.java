package jumpingalien.model;

public class LessThan extends BinaryExpression<Boolean, Double> {

	public LessThan(Expression<Double> expression1,
			Expression<Double> expression2) {
		super(expression1, expression2);
	}

	@Override
	protected Boolean evaluate() {
		return this.getExpression1().evaluate() < this.getExpression2().evaluate();
	}
	
}
