package jumpingalien.model.program.statement;


import jumpingalien.model.Mazub;
import jumpingalien.model.Shark;
import jumpingalien.model.program.Program;

public class StartJump extends Statement {

	public StartJump() {
	}

	@Override
	public void execute(Program program) {
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

	@Override
	public boolean isWellFormed() {
		if(this.getLoopStatement(this) instanceof ForEachStatement) {
			return false;
		}
		return true;
	}
}
