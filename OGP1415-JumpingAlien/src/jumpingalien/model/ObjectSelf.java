package jumpingalien.model;

public class ObjectSelf extends Expression<Object> {

	public ObjectSelf() {
	}

	protected Object evaluate(Program program) {
		return program.getGameObject();
	}
	
}
