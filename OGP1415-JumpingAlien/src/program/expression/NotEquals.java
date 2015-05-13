package program.expression;

import program.Program;

public class NotEquals extends BinaryExpression<Boolean, Boolean> {

	public NotEquals(Expression<Boolean> expression1,
			Expression<Boolean> expression2) {
		super(expression1, expression2);
	}

	@Override
	public Boolean evaluate(Program program) {
		return this.getExpression1().evaluate(program) != this.getExpression2().evaluate(program);
	}
	
}
