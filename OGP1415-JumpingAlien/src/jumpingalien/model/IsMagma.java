package jumpingalien.model;

public class IsMagma extends UnaryExpression<Boolean, Tile> {
	
	public IsMagma(Expression<Tile> expr) {
		super(expr);
	}

	@Override
	protected Boolean evaluate(Program program) {
		return (this.getExpression1().evaluate(program).getGeologicalFeature() == 3);
	}

}
