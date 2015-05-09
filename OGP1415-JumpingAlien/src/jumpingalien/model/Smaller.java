package jumpingalien.model;

public class Smaller extends BinaryExpression<Boolean, Double> {

	public Smaller(Expression<Double> expression1,
			Expression<Double> expression2) {
		super(expression1, expression2);
	}

	@Override
	protected Boolean evaluate() {
		return (double) this.getExpression1().evaluate() < (double) this.getExpression2().evaluate();
	}
	
}
