package jumpingalien.model.program.expression;

import jumpingalien.model.program.Program;

public class Division extends BinaryExpression<Double, Double> {

	public Division(Expression<Double> expression1, Expression<Double> expression2) {
		super(expression1, expression2);
	}

	@Override
	public Double evaluate(Program program) {
		return  (this.getExpression1().evaluate(program)) / (this.getExpression2().evaluate(program));
	}

}