package jumpingalien.model;

public class GetWidthObject extends UnaryExpression<Double, GameObject>{

	public GetWidthObject(Expression<GameObject> expression1) {
		super(expression1); 
	}

	@Override
	public Double evaluate() {
		return (double) ((GameObject) this.getExpression1().evaluate()).getXDim();
	}

}
