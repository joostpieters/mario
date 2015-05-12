package jumpingalien.model;

public class IsWater extends UnaryExpression<Boolean, Tile> {
	
	public IsWater(Expression<Tile> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program).getGeologicalFeature() == 2);
	}

}
