package jumpingalien.model;


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
	
	
}
