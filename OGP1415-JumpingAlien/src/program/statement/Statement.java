package program.statement;

import program.Program;

 
public abstract class Statement {
	
	public Statement(){
		
	}
	
	private double EXECUTION_TIME = 0.001;
	protected double getExecutionTime() {
		return EXECUTION_TIME;
	}

	private Statement superStatement;
	public Statement getSuperStatement() {
		return superStatement;
	}
	public void setSuperStatement(Statement statement) {
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

	
	public abstract void execute(Program program);
	
	public abstract void reset();
	// Nog een heleboel belangrijke abstracte functies
	
}

