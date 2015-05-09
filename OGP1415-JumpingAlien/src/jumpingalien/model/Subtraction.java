package jumpingalien.model;

public class Subtraction<T> extends BinaryExpression {

	public Subtraction(Expression<T> expression1, Expression<T> expression2) {
		super(expression1, expression2);
	}

	@Override
	protected Double evaluate() {
		return  ((double) (this.getExpression1().evaluate()) - (double)(this.getExpression2().evaluate()));
	}

}