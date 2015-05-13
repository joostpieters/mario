package program.expression;

import program.Program;

public class Subtraction extends BinaryExpression<Double, Double> {

	public Subtraction(Expression<Double> expression1, Expression<Double> expression2) {
		super(expression1, expression2);
	}

	@Override
	public Double evaluate(Program program) {
		return  (this.getExpression1().evaluate(program)) - (this.getExpression2().evaluate(program));
	}

}
