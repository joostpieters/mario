package program.expression;

import program.Program;
import jumpingalien.model.Type;

public abstract class Expression<T> {
	
	public Expression() {
	}
	
	public abstract T evaluate(Program program);
	
//	// TODO Dit is misschien overbodig -> ik dacht ook al zoiets
//	private Type type;
//	
//	protected Type getType() {
//		return type;
//	}
//	
//	protected void setType(Type type) {
//		this.type = type;
//	}
	
	
}
