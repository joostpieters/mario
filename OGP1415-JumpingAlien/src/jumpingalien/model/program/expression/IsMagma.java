package jumpingalien.model.program.expression;

import jumpingalien.model.Tile;
import jumpingalien.model.program.Program;

public class IsMagma extends UnaryExpression<Boolean, Tile> {
	
	public IsMagma(Expression<Tile> expr) {
		super(expr);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program).getGeologicalFeature() == 3);
	}

}
