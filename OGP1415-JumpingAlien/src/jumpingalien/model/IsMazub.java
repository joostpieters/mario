package jumpingalien.model;

public class IsMazub extends UnaryExpression<Boolean, GameObject> {
	
	public IsMazub(Expression<GameObject> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate() {
		return (((GameObject) this.getExpression1().evaluate()) instanceof Mazub);
	}

}
