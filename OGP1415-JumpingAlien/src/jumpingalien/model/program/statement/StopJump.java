package jumpingalien.model.program.statement;


import jumpingalien.model.Mazub;
import jumpingalien.model.Shark;
import jumpingalien.model.program.Program;

public class StopJump extends Statement {

	public StopJump() {
	}

	@Override
	public void execute(Program program) {
		if (program.getGameObject() instanceof Mazub) {
			((Mazub) program.getGameObject()).endJump();
		}
		else if (program.getGameObject() instanceof Shark) {
			((Shark) program.getGameObject()).endJump();
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
