package jumpingalien.model.program.statement;

import jumpingalien.model.program.Program;

 
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
	
	
	// Classy recursie
	public LoopStatement getLoopStatement(Statement stat) {
		if(stat.getSuperStatement() instanceof LoopStatement) {
			return (LoopStatement) stat.getSuperStatement();
		}
		else if (stat.getSuperStatement() == null) {
			return null;
		}
		else {
			return this.getLoopStatement(stat.getSuperStatement());
		}
	}
	
	public abstract void execute(Program program);
	
	public abstract void reset();

	public abstract boolean isWellFormed();
}

