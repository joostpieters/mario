package jumpingalien.model;

// TODO femke muilen

public class DoubleExpression extends Expression {
	
	public DoubleExpression(DoubleOperation operation, Expression expression) throws IllegalArgumentException {
		if(expression.getType() != Type.DOUBLE) {
			throw new IllegalArgumentException();
		}
		
		this.setValue(operation, expression);
	}
	
//	private Expression expression1;
//	
//	private Expression getExpression1() {
//		return expression1;
//	}
//	
//	private void setExpression1(Expression expr) {
//		this.expression1 = expr;
//	}
//	
	
	private double value;
	
	private void setValue(DoubleOperation operation, Expression expression) {
		if (expression.getType() == Type.DOUBLE) {
			if (operation == DoubleOperation.CONSTANT) {
				this.value = expression.evaluate();
			}
			else if (operation == DoubleOperation.RANDOM) {
				this.value = Math.random() * expression.evaluate();
			}
			else if (operation == DoubleOperation.SQRT) {
				this.value = Math.sqrt(expression.evaluate());
			}
			else {
				// TODO throw
			}
		else if (expression.getType() != Type.OBJECT) {
			if (operation == DoubleOperation.GETX) {
				
			}
			}
		else {
			//TODO throw...
		}
	}

	
//	@Override
	public Type type = Type.DOUBLE;
	
	public double evaluate() {
		return this.value;
	}
	
}
