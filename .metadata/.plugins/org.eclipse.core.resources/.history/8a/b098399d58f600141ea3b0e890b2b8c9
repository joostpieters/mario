package jumpingalien.model;

public class GetHeightObject extends UnaryExpression<Object>{

	public GetHeightObject(Expression<Object> expression1) {
		super(expression1); 
	}

	@Override
	public Double evaluate() {
		return ((Object) this.getExpression1().evaluate()).getYDim();
	}

}
