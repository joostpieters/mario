package jumpingalien.model;

public class IsMoving extends UnaryExpression<Boolean, GameObject> {
	
	public IsMoving(Expression<GameObject> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate() {
		return ((((GameObject) this.getExpression1().evaluate()).getXSpeed() != 0)
				|| ((((GameObject) this.getExpression1().evaluate()).getYSpeed()) != 0));
				
	}

}
