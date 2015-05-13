package program.expression;

import program.Program;
import jumpingalien.model.Tile;

public class IsAir extends UnaryExpression<Boolean, Tile> {
	
	public IsAir(Expression<Tile> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program).getGeologicalFeature() == 1);
	}

}
