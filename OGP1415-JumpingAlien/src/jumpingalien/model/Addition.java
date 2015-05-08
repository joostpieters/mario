package jumpingalien.model;

public class Addition<T> extends BinaryExpression {

	public Addition(Expression<T> expression1, Expression<T> expression2) {
		super(expression1, expression2);
	}
	
	public Double evaluate() {
		return  ((double) (this.getExpression1().evaluate()) + (double)(this.getExpression2().evaluate()));
		
		// mss hebben we gettype niet meer nodig
//		if (expression1.getType() == Type.DOUBLE && expression2.getType() == Type.DOUBLE) {
//			return this.getExpression1().evaluate() + this.getExpression2().evaluate();
//		}
//		else {
//			throw new IllegalArgumentException();
//		}
		
	}

}
