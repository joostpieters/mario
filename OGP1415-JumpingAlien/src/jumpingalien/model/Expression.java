package jumpingalien.model;

public abstract class Expression<T> {
	
	public Expression() {
	}
	
	public Type type;
	
	protected Type getType() {
		return type;
	}
	
	private void setType(Type type) {
		this.type = type;
	}
	
	protected T evaluate(Expression expression) {
		return null;
		
	}
	
	
}
