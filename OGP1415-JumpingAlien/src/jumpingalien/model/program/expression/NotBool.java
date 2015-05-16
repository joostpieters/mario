package jumpingalien.model.program.expression;

import jumpingalien.model.program.Program;


public class NotBool extends UnaryExpression<Boolean, Boolean> {
	
	public NotBool(Expression<Boolean> expression1) {
		super(expression1);
	}
	public Boolean evaluate(Program program) {
		return  ( ! (this.getExpression1().evaluate(program)));
	}
}
