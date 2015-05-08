package jumpingalien.model;

public class Constant extends UnaryExpression{

	public <T> Constant(Expression<T> expression1) {
		super(expression1);
	}

	@Override
	protected double evaluate() {
		return this.getExpression1();
	}
	
	
}
