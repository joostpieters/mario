package jumpingalien.model.program.expression;

import jumpingalien.model.GameObject;
import jumpingalien.model.program.Program;


public class GetHpObject extends UnaryExpression<Double, GameObject> {
	
	public GetHpObject(Expression<GameObject> expression1) {
		super(expression1);
	}
	public Double evaluate(Program program) {

		return (double) ((GameObject) this.getExpression1().evaluate(program)).getHitpoints();

	}
}
