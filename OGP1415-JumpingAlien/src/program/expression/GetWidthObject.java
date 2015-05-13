package program.expression;

import program.Program;
import jumpingalien.model.GameObject;

public class GetWidthObject extends UnaryExpression<Double, GameObject>{

	public GetWidthObject(Expression<GameObject> expression1) {
		super(expression1); 
	}

	@Override
	public Double evaluate(Program program) {
		return (double) ((GameObject) this.getExpression1().evaluate(program)).getXDim();
	}

}