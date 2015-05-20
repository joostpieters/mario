package jumpingalien.model.program.expression;

import jumpingalien.model.Type;
import jumpingalien.model.program.Program;


public class BoolFalse extends Expression<Boolean> {
	
	public BoolFalse() {
		this.setType(Type.BOOLEAN);
	}
	
	@Override
	public Boolean evaluate(Program program) {
		return false;
	}
}