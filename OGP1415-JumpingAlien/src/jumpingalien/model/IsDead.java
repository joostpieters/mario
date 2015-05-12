package jumpingalien.model;

public class IsDead extends UnaryExpression<Boolean, GameObject> {
	
	public IsDead(Expression<GameObject> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return (((GameObject) this.getExpression1().evaluate(program)).getHitpoints() <= 0);
	}

}
