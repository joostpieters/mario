package program.expression;

import program.Program;
import jumpingalien.model.Mazub;
import jumpingalien.model.SuperObject;

public class IsMazub extends UnaryExpression<Boolean, SuperObject> {
	
	public IsMazub(Expression<SuperObject> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program) instanceof Mazub);
	}

}
