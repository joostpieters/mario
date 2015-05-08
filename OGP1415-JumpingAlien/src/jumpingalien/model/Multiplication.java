package jumpingalien.model;

public class Multiplication<T> extends BinaryExpression<T>{

	public Multiplication(Expression<T> expression1, Expression<T> expression2) {
		super(expression1, expression2);
	}

	@Override
	protected T evaluate() {
		return getExpression1().evaluate() * getExpression2().evaluate();
	}

}
