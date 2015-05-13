package program.expression;

import program.Program;

public class LessEquals extends BinaryExpression<Boolean, Double> {

	public LessEquals(Expression<Double> expression1,
			Expression<Double> expression2) {
		super(expression1, expression2);
	}

	@Override
	public Boolean evaluate(Program program) {
		return this.getExpression1().evaluate(program) <= this.getExpression2().evaluate(program);
	}
	
}
