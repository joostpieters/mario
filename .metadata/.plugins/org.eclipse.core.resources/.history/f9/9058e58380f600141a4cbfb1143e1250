package jumpingalien.model;
import jumpingalien.model.Expression;
import jumpingalien.model.UnaryExpression;


public class RandomDouble extends UnaryExpression<Double, Double> {
	
	public RandomDouble(Expression<Double> expression1) {
		super(expression1);
	}
	public Double evaluate() {
		return Math.random() * this.getExpression1().evaluate();
	}
}
