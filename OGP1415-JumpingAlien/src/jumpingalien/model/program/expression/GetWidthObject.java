package jumpingalien.model.program.expression;

import jumpingalien.model.GameObject;
import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

public class GetWidthObject extends UnaryExpression<Double, GameObject>{

	public GetWidthObject(Expression<GameObject> expression1) {
		super(expression1);
		if (expression1.getType() != Type.OBJECT) {
			throw new IllegalArgumentException();
		}
		this.setType(Type.DOUBLE);
	}

	@Override
	public Double evaluate(Program program) {
		return (double) this.getExpression1().evaluate(program).getXDim();
	}

}
