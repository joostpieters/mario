package jumpingalien.model.program.expression;

import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

public class BoolTrue extends Expression<Boolean> {
	
	public BoolTrue() {
		this.setType(Type.BOOLEAN);
	}
	
	public Boolean evaluate(Program program) {
		return  true;
	}
}
