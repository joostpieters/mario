package jumpingalien.model;

public class Subtraction extends BinaryExpression {

	public Subtraction(Expression expression1, Expression expression2) {
		super(expression1, expression2);
	}

	@Override
	protected double evaluate() {
		return this.getExpression1() - this.getExpression2();
	}

}
