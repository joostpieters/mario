package jumpingalien.model;

import java.util.Map;

public class StopJump extends Statement {

	public StopJump() {
	}

	@Override
	public void execute(Program program) {
		// TODO checkers
		//TODO functie aanmaken in gameObject
		((GameObject) program.getGameObject()).endJump();
		this.setReady();
	}
	
	@Override
	public void reset() {
		this.setNotReady();
	}

}
