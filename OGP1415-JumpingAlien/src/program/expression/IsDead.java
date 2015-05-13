package program.expression;

import program.Program;
import jumpingalien.model.GameObject;

public class IsDead extends UnaryExpression<Boolean, GameObject> {
	
	public IsDead(Expression<GameObject> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program).getHitpoints() <= 0);
	}

}
