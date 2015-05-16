package jumpingalien.model.program.expression;

import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.program.Program;

public class IsJumping extends UnaryExpression<Boolean, Mazub> {
	
	public IsJumping(Expression<Mazub> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (((GameObject) this.getExpression1().evaluate(program)).isJumping());
	}

}
