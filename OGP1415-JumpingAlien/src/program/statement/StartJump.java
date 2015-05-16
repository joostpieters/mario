package program.statement;


import program.Program;
import jumpingalien.model.Mazub;
import jumpingalien.model.Shark;

public class StartJump extends Statement {

	public StartJump() {
	}

	@Override
	public void execute(Program program) {
		// TODO checkers
		if (program.getGameObject() instanceof Mazub) {
			((Mazub) program.getGameObject()).startJump();
		}
		else if (program.getGameObject() instanceof Shark) {
			((Shark) program.getGameObject()).startJump();
		}
		else {
			throw new IllegalArgumentException();
		}
		this.setReady();
	}
	
	@Override
	public void reset() {
		this.setNotReady();
	}

}
