package jumpingalien.model;

public class Multiplication extends BinaryExpression<Double>{

	public Multiplication(Expression<Double> expression1, Expression<Double> expression2) {
		super(expression1, expression2);
	}

	@Override
	public Double evaluate() {
		return this.getExpression1().evaluate() * this.getExpression2().evaluate();
	}

}
