package jumpingalien.model.program.expression;

import jumpingalien.model.Mazub;
import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

public class IsDucking extends UnaryExpression<Boolean, Mazub> {
	
	public IsDucking(Expression<Mazub> expr) {
		super(expr);
		if (expr.getType() != Type.OBJECT) {
			throw new IllegalArgumentException();
		}
		this.setType(Type.BOOLEAN);
	}

	@Override
	public Boolean evaluate(Program program) {
		return this.getExpression1().evaluate(program).isDucked();
	}

}
