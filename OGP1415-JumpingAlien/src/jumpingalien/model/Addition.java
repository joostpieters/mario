package jumpingalien.model;

public class Addition<T> extends BinaryExpression<T> {

	public Addition(Expression expression1, Expression expression2) {
		super(expression1, expression2);
	}
	
	public double evaluate() {
		// mss hebben we gettype niet meer nodig
		if (expression1.getType() == Type.DOUBLE && expression2.getType() == Type.DOUBLE) {
			return this.getExpression1().evaluate() + this.getExpression2().evaluate();
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}

}
