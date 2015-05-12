package jumpingalien.model;

public class IsPassable extends UnaryExpression<Boolean, Tile> {
	
	public IsPassable(Expression<Tile> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program).getGeologicalFeature() != 0);
	}

}
