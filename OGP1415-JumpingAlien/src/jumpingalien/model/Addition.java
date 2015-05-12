package jumpingalien.model;

public class Addition extends BinaryExpression<Double, Double> {

	public Addition(Expression<Double> expression1, Expression<Double> expression2) {
		super(expression1, expression2);
	}
	
	public Double evaluate(Program program) {
		return  (this.getExpression1().evaluate(program)) + (this.getExpression2().evaluate(program));
		
		// mss hebben we gettype niet meer nodig
//		if (expression1.getType() == Type.DOUBLE && expression2.getType() == Type.DOUBLE) {
//			return this.getExpression1().evaluate() + this.getExpression2().evaluate();
//		}
//		else {
//			throw new IllegalArgumentException();
//		}
		
	}

}
