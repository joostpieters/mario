package jumpingalien.model.program.statement;

import jumpingalien.model.Mazub;
import jumpingalien.model.program.Program;

public class StopDuck extends Statement {

	public StopDuck() {
	}

	@Override
	public void execute(Program program) {
		if (program.getGameObject() instanceof Mazub) {
			((Mazub) program.getGameObject()).endDuck();
			this.setReady();
		}
		else{
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void reset() {
		this.setNotReady();
	}
	
	@Override
	public boolean isWellFormed() {
		if(this.getLoopStatement(this) != null) {
			return false;
		}
		return true;
	}
}
