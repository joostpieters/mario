package jumpingalien.model.program.expression;

import jumpingalien.model.GameObject;
import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

public class IsDead extends UnaryExpression<Boolean, GameObject> {
	
	public IsDead(Expression<GameObject> expr) {
		super(expr);
		if (expr.getType() != Type.OBJECT) {
			throw new IllegalArgumentException();
		}
		this.setType(Type.BOOLEAN);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program).getHitpoints() <= 0);
	}

}
