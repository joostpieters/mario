package jumpingalien.model;

public class ObjectNull extends Expression<Object> {

	public ObjectNull(Object object) {
		this.setObject(null);
	}

	private Object object;
	
	private Object getObject() {
		return this.object;
	}
	
	private void setObject(Object object) {
		this.object = object;
	}
	
	@Override
	protected Object evaluate() {
		return this.getObject();
	}
	
}
