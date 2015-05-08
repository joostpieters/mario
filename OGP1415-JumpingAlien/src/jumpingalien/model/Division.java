package jumpingalien.model;

public class Division extends BinaryExpression {

	public Division(Expression expression1, Expression expression2) {
		super(expression1, expression2);
	}

	@Override
	protected double evaluate() {
		return this.getExpression1() / this.getExpression2();
	}

}
