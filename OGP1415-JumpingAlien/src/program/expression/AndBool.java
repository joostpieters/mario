package program.expression;

import jumpingalien.model.BinaryExpression;
import jumpingalien.model.Expression;
import jumpingalien.model.Program;

public class AndBool extends BinaryExpression<Boolean, Boolean> {

	public AndBool(Expression<Boolean> expression1,
			Expression<Boolean> expression2) {
		super(expression1, expression2);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return this.getExpression1().evaluate(program) && this.getExpression2().evaluate(program);
	}
	
}
