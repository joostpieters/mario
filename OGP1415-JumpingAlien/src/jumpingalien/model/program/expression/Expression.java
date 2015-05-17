package jumpingalien.model.program.expression;

import jumpingalien.model.program.Program;

public abstract class Expression<T> {
	
	public Expression() {
	}
	
	public abstract T evaluate(Program program);
	

}
