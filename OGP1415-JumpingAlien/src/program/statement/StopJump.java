package program.statement;

import java.util.Map;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;

public class StopJump extends Statement {

	public StopJump() {
	}

	@Override
	public void execute(Program program) {
		// TODO checkers
		((GameObject) program.getGameObject()).endJump();
		this.setReady();
	}
	
	@Override
	public void reset() {
		this.setNotReady();
	}

}
