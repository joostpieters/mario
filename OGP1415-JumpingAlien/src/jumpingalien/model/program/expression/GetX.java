package jumpingalien.model.program.expression;

import jumpingalien.model.SuperObject;
import jumpingalien.model.Type;
import jumpingalien.model.program.Program;


public class GetX extends UnaryExpression<Double, SuperObject> {
	
	public GetX(Expression<SuperObject> expression1) {
		super(expression1);
		if (expression1.getType() != Type.OBJECT) {
			throw new IllegalArgumentException();
		}
		this.setType(Type.DOUBLE);
	}
	public Double evaluate(Program program) {

		return this.getExpression1().evaluate(program).getXPos();

	}
}
