package jumpingalien.model;

public class IsSlime extends UnaryExpression<Boolean, GameObject> {
	
	public IsSlime(Expression<GameObject> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return (((GameObject) this.getExpression1().evaluate(program)) instanceof Slime);
	}

}
