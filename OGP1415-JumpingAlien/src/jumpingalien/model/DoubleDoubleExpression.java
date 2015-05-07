package jumpingalien.model;

public class DoubleDoubleExpression extends DoubleExpression {

	

	public DoubleDoubleExpression(DoubleOperation operation,
			Expression expression1, Expression expression2) throws IllegalArgumentException {
		super(operation, expression1);
		if (expression2.getType() != Type.DOUBLE) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public double evaluate() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
