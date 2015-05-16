package jumpingalien.model.program.expression;

import jumpingalien.model.GameObject;
import jumpingalien.model.program.Program;

public class IsDead extends UnaryExpression<Boolean, GameObject> {
	
	public IsDead(Expression<GameObject> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program).getHitpoints() <= 0);
	}

}
