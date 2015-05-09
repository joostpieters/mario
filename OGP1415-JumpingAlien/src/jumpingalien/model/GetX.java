package jumpingalien.model;


public class GetX extends UnaryExpression<Double, SuperObject> {
	
	public GetX(Expression<Object> expression1) {
		super(expression1);
	}
	public Double evaluate() {

		return (this.getExpression1().evaluate()).getXPos();

	}
}
