package jumpingalien.model;

public class IsTerrain extends UnaryExpression<Boolean, SuperObject> {
	
	public IsTerrain(Expression<SuperObject> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program) instanceof Tile);
	}

}
