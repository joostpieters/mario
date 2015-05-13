package program.expression;

import program.Program;

public class ObjectNull extends Expression<Object> {

	public ObjectNull() {
	}

	@Override
	public Object evaluate(Program program) {
		return null;
	}
	
}
