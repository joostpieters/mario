package jumpingalien.model;

public class DirectionExpression<T> extends UnaryExpression {
	
	
	public DirectionExpression(Expression<T> expression1) {
		super(expression1);
	}

	@Override
	protected jumpingalien.part3.programs.IProgramFactory.Direction evaluate() {
		return (jumpingalien.part3.programs.IProgramFactory.Direction) this.getExpression1().evaluate();
	}
	
}
