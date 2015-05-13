package jumpingalien.model;

import java.util.Map;

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
			
		}
		//((GameObject) program.getGameObject()).startJump();
		this.setReady();
		// TODO alle gameObjecten nen StartJump geven -> waarom, niet alle objecten kunnen jumpen?
	}
	
	@Override
	public void reset() {
		this.setNotReady();
	}

}
