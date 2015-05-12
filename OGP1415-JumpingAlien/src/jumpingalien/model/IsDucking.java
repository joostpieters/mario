package jumpingalien.model;

public class IsDucking extends UnaryExpression<Boolean, Mazub> {
	
	public IsDucking(Expression<Mazub> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return this.getExpression1().evaluate(program).isDucked();
	}

}
