package jumpingalien.model;

public class IsJumping extends UnaryExpression<Boolean, Mazub> {
	
	public IsJumping(Expression<Mazub> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate() {
		return (((Mazub) this.getExpression1().evaluate()).getYSpeed() > 0);
	}

}
