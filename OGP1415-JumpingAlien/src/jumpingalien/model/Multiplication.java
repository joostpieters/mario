package jumpingalien.model;

public class Multiplication<T> extends BinaryExpression{

	public Multiplication(Expression<T> expression1, Expression<T> expression2) {
		super(expression1, expression2);
	}

	@Override
	public Double evaluate() {
		return ((double) (this.getExpression1().evaluate()) * (double)(this.getExpression2().evaluate()));
	}

}
