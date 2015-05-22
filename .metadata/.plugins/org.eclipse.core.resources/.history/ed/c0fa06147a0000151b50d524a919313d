package jumpingalien.model.program.statement;

import jumpingalien.model.program.Program;


public class SkipStatement extends Statement {

	public SkipStatement() {
	}
	
	@Override
	public void execute(Program program) {
		this.setReady();
	}

	@Override
	public void reset() {
		this.setNotReady();
	}

	@Override
	public boolean isWellFormed() {
		if(this.getLoopStatement(this) instanceof ForEachStatement) {
			return false;
		}
		return true;
	}
	
	
}
