package program.statement;


import program.Program;
import jumpingalien.model.GameObject;

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
