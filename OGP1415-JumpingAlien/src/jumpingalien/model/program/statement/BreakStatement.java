package jumpingalien.model.program.statement;

import jumpingalien.model.program.Program;


public class BreakStatement extends Statement {

	public BreakStatement() {
		
	}
	
	
	// meer classy recursie
	private void resetAllSuperStatTillLoop(Statement stat) {
		if( ! (stat.getSuperStatement() instanceof LoopStatement)) {
			stat.getSuperStatement().reset();
			this.resetAllSuperStatTillLoop(stat.getSuperStatement());
		}
	}
	
	
	@Override
	public void execute(Program program) {
		this.resetAllSuperStatTillLoop(this);
		LoopStatement loopStat = this.getLoopStatement(this);
		loopStat.setInBody(false);
		loopStat.setReady();
	}

	@Override
	public void reset() {
		this.setNotReady();
	}


	@Override
	public boolean isWellFormed() {
		if (this.getLoopStatement(this) != null) {
			return true;
		}
		return false;
	}

}
