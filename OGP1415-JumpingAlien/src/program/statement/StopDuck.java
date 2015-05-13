package program.statement;

import program.Program;
import jumpingalien.model.Mazub;

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