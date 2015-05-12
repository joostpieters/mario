package jumpingalien.model;

public class Division extends BinaryExpression<Double, Double> {

	public Division(Expression<Double> expression1, Expression<Double> expression2) {
		super(expression1, expression2);
	}

	@Override
	protected Double evaluate(Program program) {
		return  (this.getExpression1().evaluate(program)) / (this.getExpression2().evaluate(program));
	}

}
