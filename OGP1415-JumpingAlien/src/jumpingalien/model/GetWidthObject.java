package jumpingalien.model;

public class GetWidthObject extends UnaryExpression<Double, GameObject>{

	public GetWidthObject(Expression<GameObject> expression1) {
		super(expression1); 
	}

	@Override
	public Double evaluate(Program program) {
		return (double) ((GameObject) this.getExpression1().evaluate(program)).getXDim();
	}

}
