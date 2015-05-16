package jumpingalien.model.program.expression;

import jumpingalien.model.program.Program;

public class BoolTrue extends Expression<Boolean> {
	
	public BoolTrue() {
		
	}
	
	public Boolean evaluate(Program program) {
		return  true;
	}
}
