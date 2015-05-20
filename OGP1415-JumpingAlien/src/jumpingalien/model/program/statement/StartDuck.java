package jumpingalien.model.program.statement;

import jumpingalien.model.Mazub;
import jumpingalien.model.program.Program;


public class StartDuck extends Statement {

	public StartDuck() {
	}

	@Override
	public void execute(Program program) {
		if (program.getGameObject() instanceof Mazub) {
			((Mazub) program.getGameObject()).startDuck();
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
		if(this.getLoopStatement(this) instanceof ForEachStatement) {
			return false;
		}
		return true;
	}
}
