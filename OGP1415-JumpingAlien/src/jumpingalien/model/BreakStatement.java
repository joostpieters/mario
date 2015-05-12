package jumpingalien.model;

import java.util.Map;

public class BreakStatement extends Statement {

	public BreakStatement() {
	}
	
	// Classy recursie
	private LoopStatement getLoopStatement(Statement stat) {
		if(stat.getSuperStatement() instanceof LoopStatement) {
			return (LoopStatement) stat.getSuperStatement();
		}
		else {
			return this.getLoopStatement(stat.getSuperStatement());
		}
	}
	
	// meer classy recursie
	private void resetAllSuperStatTillLoop(Statement stat) {
		if( ! (stat.getSuperStatement() instanceof LoopStatement)) {
			stat.getSuperStatement().reset();
			this.resetAllSuperStatTillLoop(stat.getSuperStatement());
		}
	}
	
	
	// Pas doen als for each af is mss
	// TODO nog de statement waarin het zit laten breaken
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

}
