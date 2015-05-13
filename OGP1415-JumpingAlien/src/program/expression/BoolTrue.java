package program.expression;

import program.Program;

public class BoolTrue extends Expression<Boolean> {
	
	public BoolTrue() {
		
	}
	
	public Boolean evaluate(Program program) {
		return  true;
	}
}
