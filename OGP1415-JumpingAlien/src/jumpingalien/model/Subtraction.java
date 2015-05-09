package jumpingalien.model;

public class Subtraction extends BinaryExpression<Double, Double> {

	public Subtraction(Expression<Double> expression1, Expression<Double> expression2) {
		super(expression1, expression2);
	}

	@Override
	protected Double evaluate() {
		return  (this.getExpression1().evaluate()) - (this.getExpression2().evaluate());
	}

}
