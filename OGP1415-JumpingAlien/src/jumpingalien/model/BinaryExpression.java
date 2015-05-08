package jumpingalien.model;

public abstract class BinaryExpression<T> extends Expression<T> {
	
	public BinaryExpression(Expression expression1, Expression expression2) {
		this.setExpression1(expression1);
		this.setExpression2(expression2);
	}
	
	private Expression expression1;
	protected Expression getExpression1() {
		return expression1;
	}
	private void setExpression1(Expression expr) {
		this.expression1 = expr;
	}
	
	private Expression expression2;
	protected Expression getExpression2() {
		return expression2;
	}
	private void setExpression2(Expression expr) {
		this.expression2 = expr;
	}
}
