package jumpingalien.model;

public abstract class BinaryExpression<T> extends Expression<T> {
	
	public BinaryExpression(Expression<T> expression1, Expression<T> expression2) {
		this.setExpression1(expression1);
		this.setExpression2(expression2);
	}
	
	private Expression<T> expression1;
	protected Expression<T> getExpression1() {
		return expression1;
	}
	private void setExpression1(Expression<T> expr) {
		this.expression1 = expr;
	}
	
	private Expression<T> expression2;
	protected Expression<T> getExpression2() {
		return expression2;
	}
	private void setExpression2(Expression<T> expr) {
		this.expression2 = expr;
	}
}
