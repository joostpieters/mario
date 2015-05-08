package jumpingalien.model;

public class Multiplication extends BinaryExpression{

	public Multiplication(Expression expression1, Expression expression2) {
		super(expression1, expression2);
	}

	@Override
	protected double evaluate() {
		return getExpression1().evaluate() * getExpression2().evaluate();
	}

}
