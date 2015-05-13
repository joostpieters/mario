package program.expression;

import program.Program;
import jumpingalien.model.SuperObject;
import jumpingalien.model.Tile;

public class IsTerrain extends UnaryExpression<Boolean, SuperObject> {
	
	public IsTerrain(Expression<SuperObject> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program) instanceof Tile);
	}

}
