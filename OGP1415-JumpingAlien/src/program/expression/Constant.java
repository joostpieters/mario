package program.expression;

import program.Program;

public class Constant extends Expression<Double> {

	public Constant(double value) {
		this.setValue(value);
	}

	private double value;
	
	private double getValue() {
		return this.value;
	}
	
	private void setValue(double v) {
		this.value = v;
	}
	
	@Override
	public Double evaluate(Program program) {
		return this.getValue();
	}
	
}