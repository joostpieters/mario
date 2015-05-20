package jumpingalien.model.program.expression;

import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

public class ObjectNull extends Expression<Object> {

	public ObjectNull() {
		this.setType(Type.OBJECT);
	}

	@Override
	public Object evaluate(Program program) {
		return null;
	}
	
}
