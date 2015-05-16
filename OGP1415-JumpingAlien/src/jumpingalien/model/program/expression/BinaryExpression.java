package jumpingalien.model.program.expression;

public abstract class BinaryExpression<T,S> extends Expression<T> {
	
	public BinaryExpression(Expression<S> expression1, Expression<S> expression2) {
		this.setExpression1(expression1);
		this.setExpression2(expression2);
	}
	
	private Expression<S> expression1;
	protected Expression<S> getExpression1() {
		return expression1;
	}
	private void setExpression1(Expression<S> expression1) {
		this.expression1 = expression1;
	}
	
	private Expression<S> expression2;
	protected Expression<S> getExpression2() {
		return expression2;
	}
	private void setExpression2(Expression<S> expr) {
		this.expression2 = expr;
	}
	
}
