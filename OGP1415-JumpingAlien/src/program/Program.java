package program;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.model.GameObject;
import jumpingalien.model.Type;
import program.statement.Statement;


public class Program {
	
	public Program(Statement mainStatement, Map<String, Type> globalVariables) {
		this.setMainStatement(mainStatement);
		this.setGlobalVariables(globalVariables);
		this.setEnvironment(globalVariables);
	}
	
	private Statement mainStatement;
	private Statement getMainStatement() {
		return this.mainStatement;
	}
	private void setMainStatement(Statement main) {
		this.mainStatement = main;
	}
	
	private Map<String, Type> globalVariables;
	private Map<String, Type> getGlobalVariables() {
		return this.globalVariables;
	}
	private void setGlobalVariables(Map<String, Type> var) {
		this.globalVariables = var;
	}
	
	private GameObject gameObject;
	public GameObject getGameObject() {
		return this.gameObject;
	}
	public void setGameObject(GameObject obj) {
		this.gameObject = obj;
	}
	
	private Map<String, Object> environment;
	public Map<String, Object> getEnvironment() {
		return this.environment;
	}
	protected void setEnvironment(Map<String, Type> map) {
		this.environment = new HashMap<String, Object>();
		for (String key : map.keySet()) {
			if (map.get(key) == Type.Double) {
				this.environment.put(key, 0);
			}
			else if (map.get(key) == Type.Boolean) {
				this.environment.put(key, false);
			}
			else if (map.get(key) == Type.Object) {
				this.environment.put(key, null);
			}
			else if (map.get(key) == Type.Direction) {
				this.environment.put(key, false);
			}
		}
	}
	
	public void addToEnvironment(String name, Object value ) {
		this.environment.put(name, value);
	}
	
	
	public void execute(double dt) {
		if(dt <= 0.001) {
			this.getMainStatement().execute(this);
		}
		else {
			this.getMainStatement().execute(this);
			this.execute(dt - 0.001);
		}
	}
	

	public boolean isWellFormed() {
		
		return true;
	}


}
