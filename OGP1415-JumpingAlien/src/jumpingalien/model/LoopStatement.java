package jumpingalien.model;

import program.statement.Statement;


public abstract class LoopStatement extends Statement {
	

	public LoopStatement() {
	}
	
	private boolean inBody;
	protected boolean getInBody() {
		return inBody;
	}
	protected void setInBody(boolean b) {
		this.inBody = b;
	}

	
}
