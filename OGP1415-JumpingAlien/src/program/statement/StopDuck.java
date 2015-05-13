package program.statement;

import jumpingalien.model.Mazub;
import jumpingalien.model.Program;

public class StopDuck extends Statement {

	public StopDuck() {
	}

	@Override
	public void execute(Program program) {
		// TODO checkers
		((Mazub) program.getGameObject()).endDuck();
		this.setReady();
	}

	@Override
	public void reset() {
		this.setNotReady();
	}
}
