package jumpingalien.model.program.expression;

import jumpingalien.model.Buzam;
import jumpingalien.model.GameObject;
import jumpingalien.model.Shark;
import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

public class IsJumping extends UnaryExpression<Boolean, GameObject> {
	
	public IsJumping(Expression<GameObject> expr) {
		super(expr);
		if (expr.getType() != Type.OBJECT) {
			throw new IllegalArgumentException();
		}
		this.setType(Type.BOOLEAN);
	}

	@Override
	public Boolean evaluate(Program program) {
		if (this.getExpression1().evaluate(program) instanceof Buzam || 
				this.getExpression1().evaluate(program) instanceof Shark) {
			return this.getExpression1().evaluate(program).isJumping();
		}
		else {
			throw new IllegalArgumentException();
		}
	}

}
