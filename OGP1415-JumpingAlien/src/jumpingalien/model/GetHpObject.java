package jumpingalien.model;
import jumpingalien.model.Expression;
import jumpingalien.model.UnaryExpression;


public class GetHpObject extends UnaryExpression<Double, GameObject> {
	
	public GetHpObject(Expression<GameObject> expression1) {
		super(expression1);
	}
	public Double evaluate() {
		return Math.sqrt(this.getExpression1().evaluate().getHitpoints());
	}
}
