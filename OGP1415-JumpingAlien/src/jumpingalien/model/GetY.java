package jumpingalien.model;


public class GetY extends UnaryExpression<Double, SuperObject> {
	
	public GetY(Expression<SuperObject> expression1) {
		super(expression1);
	}
	public Double evaluate(Program program) {

		return this.getExpression1().evaluate(program).getYPos();

	}
}
