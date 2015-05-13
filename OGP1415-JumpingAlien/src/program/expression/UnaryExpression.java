package program.expression;

public abstract class UnaryExpression<T,S> extends Expression<T> {
	public UnaryExpression(Expression<S> expr) {
		this.setExpression1(expr);
	}
	
	private Expression<S> expression1;
	
	protected Expression<S> getExpression1() {
		return expression1;
	}
	
	private void setExpression1(Expression<S> expr) {
		this.expression1 = expr;
	}
}
