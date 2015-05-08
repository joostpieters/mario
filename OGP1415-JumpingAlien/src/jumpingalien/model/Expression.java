package jumpingalien.model;

public abstract class Expression<T> {
	
	public Expression() {
	}
	
	private Type type;
	
	protected Type getType() {
		return type;
	}
	
	protected void setType(Type type) {
		this.type = type;
	}
	
	protected abstract T evaluate();
	
}
