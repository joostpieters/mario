package jumpingalien.model.program.expression;

import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

public class Addition extends BinaryExpression<Double, Double> {

	public Addition(Expression<Double> expression1, Expression<Double> expression2) {
		super(expression1, expression2);
		if (expression1.getType() != Type.DOUBLE || expression2.getType() != Type.DOUBLE) {
			throw new IllegalArgumentException();
		}
		this.setType(Type.DOUBLE);
	}
	
	public Double evaluate(Program program) {
		return  (this.getExpression1().evaluate(program)) + (this.getExpression2().evaluate(program));		
	}

}
