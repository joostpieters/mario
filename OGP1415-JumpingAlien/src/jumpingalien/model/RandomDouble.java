package jumpingalien.model;


public class RandomDouble extends UnaryExpression<Double> {
	
	public RandomDouble(Expression<Double> expression1) {
		super(expression1);
	}
	public Double evaluate() {
		return Math.random() * this.getExpression1().evaluate();
	}
}
