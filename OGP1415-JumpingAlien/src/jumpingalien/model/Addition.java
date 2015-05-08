package jumpingalien.model;

public class Addition extends Operator {

	public Addition(Expression expression1, Expression expression2) {
		super(expression1, expression2);
		// TODO Auto-generated constructor stub
	}
	
	public double evaluate(Expression expression1, Expression expression2) {
		if (expression1.getType() == Type.DOUBLE && expression2.getType() == Type.DOUBLE) {
			return expression1.evaluate() + expression2.evaluate();
		}
		else {
			throw new IllegalArgumentException();
		}
		
	}

}
