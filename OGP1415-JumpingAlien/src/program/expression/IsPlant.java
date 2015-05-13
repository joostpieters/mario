package program.expression;

import program.Program;
import jumpingalien.model.Plant;
import jumpingalien.model.SuperObject;

public class IsPlant extends UnaryExpression<Boolean, SuperObject> {
	
	public IsPlant(Expression<SuperObject> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program) instanceof Plant);
	}

}
