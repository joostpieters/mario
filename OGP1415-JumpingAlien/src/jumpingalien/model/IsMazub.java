package jumpingalien.model;

public class IsMazub extends UnaryExpression<Boolean, SuperObject> {
	
	public IsMazub(Expression<SuperObject> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program) instanceof Mazub);
	}

}
