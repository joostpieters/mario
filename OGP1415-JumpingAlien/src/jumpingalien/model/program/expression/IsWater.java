package jumpingalien.model.program.expression;

import jumpingalien.model.Tile;
import jumpingalien.model.program.Program;

public class IsWater extends UnaryExpression<Boolean, Tile> {
	
	public IsWater(Expression<Tile> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program).getGeologicalFeature() == 2);
	}

}
