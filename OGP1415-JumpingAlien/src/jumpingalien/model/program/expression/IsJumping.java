package jumpingalien.model.program.expression;

import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.Shark;
import jumpingalien.model.program.Program;

public class IsJumping extends UnaryExpression<Boolean, GameObject> {
	
	public IsJumping(Expression<GameObject> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		if (this.getExpression1().evaluate(program) instanceof Mazub || 
				this.getExpression1().evaluate(program) instanceof Shark) {
			return this.getExpression1().evaluate(program).isJumping();
		}
		else {
			throw new IllegalArgumentException();
		}
	}

}
