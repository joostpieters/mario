package program.expression;

import program.Program;

public class LessThan extends BinaryExpression<Boolean, Double> {

	public LessThan(Expression<Double> expression1,
			Expression<Double> expression2) {
		super(expression1, expression2);
	}

	@Override
	public Boolean evaluate(Program program) {
		return this.getExpression1().evaluate(program) < this.getExpression2().evaluate(program);
	}
	
}
