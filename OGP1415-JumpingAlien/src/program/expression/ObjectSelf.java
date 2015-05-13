package program.expression;

import program.Program;

public class ObjectSelf extends Expression<Object> {

	public ObjectSelf() {
	}

	public Object evaluate(Program program) {
		return program.getGameObject();
	}
	
}
