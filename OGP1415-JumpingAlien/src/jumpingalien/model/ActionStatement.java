package jumpingalien.model;

import java.util.Map;

public abstract class ActionStatement extends Statement {

	

	public ActionStatement(Statement action, GameObject gObject) {
		setAction(action);
		setGameObject(gObject);
	}
	
	private GameObject gameObject;
	private Statement action;
	
	protected GameObject getGameObject() {
		return this.gameObject;
	}
	
	protected Statement getAction() {
		return this.action;
	}
	
	private void setGameObject(GameObject gObject) {
		this.gameObject = gObject;		
	}

	private void setAction(Statement action) {
		this.action = action;		
	}

	@Override
	public abstract Map<String, Type> execute(Map<String, Type> var);		
	
	
}
