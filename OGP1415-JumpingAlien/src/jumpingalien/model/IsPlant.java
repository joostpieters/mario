package jumpingalien.model;

public class IsPlant extends UnaryExpression<Boolean, SuperObject> {
	
	public IsPlant(Expression<SuperObject> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program) instanceof Plant);
	}

}
