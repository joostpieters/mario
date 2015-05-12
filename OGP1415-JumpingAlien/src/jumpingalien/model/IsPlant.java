package jumpingalien.model;

public class IsPlant extends UnaryExpression<Boolean, GameObject> {
	
	public IsPlant(Expression<GameObject> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return (((GameObject) this.getExpression1().evaluate(program)) instanceof Plant);
	}

}
