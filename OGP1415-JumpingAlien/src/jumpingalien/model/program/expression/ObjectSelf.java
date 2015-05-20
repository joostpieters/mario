package jumpingalien.model.program.expression;

import jumpingalien.model.Type;
import jumpingalien.model.program.Program;

public class ObjectSelf extends Expression<Object> {

	public ObjectSelf() {
		this.setType(Type.OBJECT);
	}

	public Object evaluate(Program program) {
		return program.getGameObject();
	}
	
}
