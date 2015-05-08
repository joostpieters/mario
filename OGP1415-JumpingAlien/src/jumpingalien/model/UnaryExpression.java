package jumpingalien.model;

public abstract class UnaryExpression extends Expression {
	public UnaryExpression(Expression expression1) {
		this.setExpression1(expression1);
	}
	
	private Expression expression1;
	
	protected Expression getExpression1() {
		return expression1;
	}
	
	private void setExpression1(Expression expr) {
		this.expression1 = expr;
	}
}
