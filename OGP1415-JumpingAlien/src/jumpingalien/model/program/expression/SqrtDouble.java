package jumpingalien.model.program.expression;

import jumpingalien.model.program.Program;


public class SqrtDouble extends UnaryExpression<Double, Double> {
	
	public SqrtDouble(Expression<Double> expression1) {
		super(expression1);
	}
	public Double evaluate(Program program) {
		return Math.sqrt(this.getExpression1().evaluate(program));
	}
}
