package jumpingalien.model;
import jumpingalien.model.Expression;
import jumpingalien.model.UnaryExpression;


public class GetX extends UnaryExpression<Double, SuperObject> {
	
	public GetX(Expression<Object> expression1) {
		super(expression1);
	}
	public Double evaluate() {

		return (this.getExpression1().evaluate()).getXPos();

	}
}
