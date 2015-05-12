package jumpingalien.model;

public class ObjectNull extends Expression<Object> {

	public ObjectNull() {
	}

	@Override
	protected Object evaluate(Program program) {
		return null;
	}
	
}
