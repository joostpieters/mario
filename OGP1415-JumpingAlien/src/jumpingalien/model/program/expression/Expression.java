package jumpingalien.model.program.expression;

import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

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
