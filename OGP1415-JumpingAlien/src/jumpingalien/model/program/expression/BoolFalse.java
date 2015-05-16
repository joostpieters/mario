package jumpingalien.model.program.expression;

import jumpingalien.model.program.Program;


public class BoolFalse extends Expression<Boolean> {
	
	public BoolFalse() {
		
	}
	
	@Override
	public Boolean evaluate(Program program) {
		return false;
	}
}