package jumpingalien.model.program.expression;

import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

public class Equals extends BinaryExpression<Boolean, Object> {

	public Equals(Expression<Object> expression1,
			Expression<Object> expression2) {
		super(expression1, expression2);
		if (expression1.getType() != expression2.getType()) {
			throw new IllegalArgumentException();
		}
		this.setType(Type.BOOLEAN);
	}

	@Override
	public Boolean evaluate(Program program) {
		return this.getExpression1().evaluate(program) == this.getExpression2().evaluate(program);
	}
	
}
