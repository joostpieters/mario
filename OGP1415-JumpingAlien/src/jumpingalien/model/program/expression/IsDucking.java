package jumpingalien.model.program.expression;

import jumpingalien.model.Mazub;
import jumpingalien.model.program.Program;

public class IsDucking extends UnaryExpression<Boolean, Mazub> {
	
	public IsDucking(Expression<Mazub> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		return this.getExpression1().evaluate(program).isDucked();
	}

}
