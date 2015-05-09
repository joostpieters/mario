package jumpingalien.model;

public class AndBool extends BinaryExpression<Boolean, Boolean> {

	public AndBool(Expression<Boolean> expression1,
			Expression<Boolean> expression2) {
		super(expression1, expression2);
	}

	@Override
	protected Boolean evaluate() {
		return this.getExpression1().evaluate() && this.getExpression2().evaluate();
	}
	
}
