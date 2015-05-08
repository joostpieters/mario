package jumpingalien.model;

public abstract class Expression<T> {
	
	public Expression() {
	}
	
	protected abstract T evaluate();
	
	

	private Type type;
	
	protected Type getType() {
		return type;
	}
	
	protected void setType(Type type) {
		this.type = type;
	}
	
}
