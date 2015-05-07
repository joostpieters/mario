package jumpingalien.model;


public abstract class DoubleExpression extends Expression {
	
	public DoubleExpression(Type type) {
		super(type);
	}

	public abstract double evaluate();
	
}
