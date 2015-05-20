package jumpingalien.model.program.expression;

import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

public class Constant extends Expression<Double> {

	public Constant(double value) {
		this.setValue(value);
		this.setType(Type.DOUBLE);
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
