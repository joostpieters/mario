package jumpingalien.model;

import jumpingalien.model.Type;

public abstract class Expression<T> {
	
	public Expression() {
	}
	
	protected abstract T evaluate();
	
	private GameObject gameObject;
	protected GameObject getGameObject() {
		return this.gameObject;
	}
	public void setGameObject(GameObject obj) {
		this.gameObject = obj;
	}
	
	
	// TODO Dit is misschien overbodig -> ik dacht ook al zoiets
	private Type type;
	
	protected Type getType() {
		return type;
	}
	
	protected void setType(Type type) {
		this.type = type;
	}
	
}
