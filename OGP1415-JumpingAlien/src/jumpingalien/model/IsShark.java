package jumpingalien.model;

public class IsShark extends UnaryExpression<Boolean, SuperObject> {
	
	public IsShark(Expression<SuperObject> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program) instanceof Shark);
	}

}
