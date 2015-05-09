package jumpingalien.model;

public class IsShark extends UnaryExpression<Boolean, GameObject> {
	
	public IsShark(Expression<GameObject> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate() {
		return (((GameObject) this.getExpression1().evaluate()) instanceof Shark);
	}

}
