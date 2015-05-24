package jumpingalien.model.program.expression;

import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

public class NotEquals extends BinaryExpression<Boolean, Object> {

	public NotEquals(Expression<Object> expression1,
					Expression<Object> expression2) {
		super(expression1, expression2);
		if (expression1.getType() != expression2.getType()) {
			throw new IllegalArgumentException();
		}
		this.setType(Type.BOOLEAN);
	}

	@Override
	public Boolean evaluate(Program program) {
		if (getExpression1().evaluate(program) == null && getExpression2().evaluate(program) == null ) {
			return false;
		}
		else if (getExpression1().evaluate(program) == null) {
			return true;
		}
		return ! this.getExpression1().evaluate(program).equals(this.getExpression2().evaluate(program));	
	}
	
}
