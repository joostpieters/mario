package jumpingalien.model;

public class IsSlime extends UnaryExpression<Boolean, SuperObject> {
	
	public IsSlime(Expression<SuperObject> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program) instanceof Slime);
	}

}
