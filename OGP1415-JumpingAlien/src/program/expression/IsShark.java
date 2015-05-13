package program.expression;

import program.Program;
import jumpingalien.model.Shark;
import jumpingalien.model.SuperObject;

public class IsShark extends UnaryExpression<Boolean, SuperObject> {
	
	public IsShark(Expression<SuperObject> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program) instanceof Shark);
	}

}
