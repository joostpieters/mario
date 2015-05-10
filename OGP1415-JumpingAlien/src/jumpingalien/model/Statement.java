package jumpingalien.model;

import java.util.Map;
 
public abstract class Statement {
	
	public Statement(){
		
	}
	
	private double EXECUTION_TIME = 0.001;
	protected double getExecutionTime() {
		return EXECUTION_TIME;
	}
	
	private GameObject gameObject;
	protected GameObject getGameObject() {
		return this.gameObject;
	}
	public void setGameObject(GameObject obj) {
		this.gameObject = obj;
	}
	
	private Program program;
	protected Program getProgram() {
		return program;
	}
	protected void setProgram(Program program) {
		this.program = program;
	}

	private Statement superStatement;
	protected Statement getSuperStatement() {
		return superStatement;
	}
	protected void setSuperStatement(Statement statement) {
		this.superStatement = statement;
	}

	private boolean ready = false;
	public boolean isReady() {
		return this.ready;
	}
	public void setReady() {
		this.ready = true;
	}
	public void setNotReady() {
		this.ready = false;
	}
	
	public abstract Map<String, Type> execute(Map<String, Type> var);
	// Nog een heleboel belangrijke abstracte functies
	
}

