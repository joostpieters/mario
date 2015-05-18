package jumpingalien.model.program.statement;



public abstract class LoopStatement extends Statement {
	

	public LoopStatement() {
	}
	private Statement body;
	protected Statement getBody() {
		return this.body;
	}
	protected void setBody(Statement stat) {
		this.body = stat;
		stat.setSuperStatement(this);
	}
	
	private boolean inBody;
	protected boolean getInBody() {
		return inBody;
	}
	protected void setInBody(boolean b) {
		this.inBody = b;
	}

	
}
