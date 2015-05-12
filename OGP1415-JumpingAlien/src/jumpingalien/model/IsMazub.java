package jumpingalien.model;

public class IsMazub extends UnaryExpression<Boolean, GameObject> {
	
	public IsMazub(Expression<GameObject> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return (((GameObject) this.getExpression1().evaluate(program)) instanceof Mazub);
	}

}
