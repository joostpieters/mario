package jumpingalien.model;

import java.util.Map;

public class StartJump extends Statement {

	public StartJump() {
	}

	@Override
	public void execute(Program program) {
		// TODO checkers
		((GameObject) program.getGameObject()).startJump();
		this.setReady();
		// TODO alle gameObjecten nen StartJump geven
	}
	
	@Override
	public void reset() {
		this.setNotReady();
	}

}
