package jumpingalien.model;

public abstract class UnaryExpression<T> extends Expression<T> {
	public UnaryExpression(Expression<T> expr) {
		this.setExpression1(expr);
	}
	
	private Expression<T> expression1;
	
	protected Expression<T> getExpression1() {
		return expression1;
	}
	
	private void setExpression1(Expression<T> expr) {
		this.expression1 = expr;
	}
}
