package jumpingalien.model.program;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.model.GameObject;
import jumpingalien.model.Type;
import jumpingalien.model.program.statement.Statement;


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
	@SuppressWarnings("unused")
	public Map<String, Type> getGlobalVariables() {
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
			if (map.get(key) == Type.DOUBLE) {
				this.environment.put(key, 0);
			}
			else if (map.get(key) == Type.BOOLEAN) {
				this.environment.put(key, false);
			}
			else if (map.get(key) == Type.OBJECT) {
				this.environment.put(key, null);
			}
			else if (map.get(key) == Type.DIRECTION) {
				this.environment.put(key, false);
			}
		}
	}
	
	public void addToEnvironment(String name, Object value ) {
		this.environment.put(name, value);
	}
	
	private boolean running = true;
	private boolean isRunning() {
		return running;
	}
	private void stopRunning() {
		running = false;
	}
	
	public void execute(double dt) {
		if(isRunning()) {
			try{
				if(dt <= 0.001) {
					this.getMainStatement().execute(this);
				}
				else {
					this.getMainStatement().execute(this);
					this.execute(dt - 0.001);
				}
			} catch(ClassCastException | IllegalArgumentException e) {
				stopRunning();
				System.out.println("Program stopped");
			}
		}
	}
	
	public boolean isWellFormed() {
		return this.getMainStatement().isWellFormed();
		
	}


}
