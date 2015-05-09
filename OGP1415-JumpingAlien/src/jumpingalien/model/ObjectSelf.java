package jumpingalien.model;

public class ObjectSelf extends Expression<Object> {

	public ObjectSelf(Object object) {
		this.setObject(object);
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
