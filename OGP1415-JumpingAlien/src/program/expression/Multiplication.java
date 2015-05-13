package program.expression;

import program.Program;

public class Multiplication extends BinaryExpression<Double, Double>{

	public Multiplication(Expression<Double> expression1, Expression<Double> expression2) {
		super(expression1, expression2); 
	}

	@Override
	public Double evaluate(Program program) {
		return this.getExpression1().evaluate(program) * this.getExpression2().evaluate(program);
	}

}
