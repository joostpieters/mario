package jumpingalien.model;

public class IsAir extends UnaryExpression<Boolean, Tile> {
	
	public IsAir(Expression<Tile> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program).getGeologicalFeature() == 1);
	}

}
