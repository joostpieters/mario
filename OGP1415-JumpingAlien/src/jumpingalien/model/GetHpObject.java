package jumpingalien.model;
import jumpingalien.model.Expression;
import jumpingalien.model.UnaryExpression;


public class GetHpObject extends UnaryExpression<Double> {
	
	public GetHpObject(Expression<GameObject> expression1) {
		super(expression1);
	}
	public Double evaluate() {
		return ((GameObject) this.getExpression1().evaluate()).getHitpoints();
	}
}
