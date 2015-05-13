package program.expression;

import program.Program;
import jumpingalien.model.Slime;
import jumpingalien.model.SuperObject;

public class IsSlime extends UnaryExpression<Boolean, SuperObject> {
	
	public IsSlime(Expression<SuperObject> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program) instanceof Slime);
	}

}
