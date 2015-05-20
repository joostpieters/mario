package jumpingalien.model.program.expression;

import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

public abstract class Expression<T> {
	
	public Expression() {
	}
	
	public abstract T evaluate(Program program);
	
	private Type type;
	public Type getType() {
		return type;
	}
	protected void setType(Type t) {
		type = t;
	}
	

}
