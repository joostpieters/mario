package jumpingalien.model.program.expression;

import jumpingalien.model.Shark;
import jumpingalien.model.SuperObject;
import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

public class IsShark extends UnaryExpression<Boolean, SuperObject> {
	
	public IsShark(Expression<SuperObject> expr) {
		super(expr);
		if (expr.getType() != Type.OBJECT) {
			throw new IllegalArgumentException();
		}
		this.setType(Type.BOOLEAN);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program) instanceof Shark);
	}

}
