package program.expression;

import program.Program;
import jumpingalien.model.Mazub;

public class IsDucking extends UnaryExpression<Boolean, Mazub> {
	
	public IsDucking(Expression<Mazub> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		return this.getExpression1().evaluate(program).isDucked();
	}

}
