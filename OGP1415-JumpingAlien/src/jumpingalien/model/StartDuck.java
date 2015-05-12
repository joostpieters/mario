package jumpingalien.model;

import java.util.Map;

public class StartDuck extends Statement {

	public StartDuck() {
	}

	@Override
	public void execute(Program program) {
		// TODO checkers
		((Mazub) this.getGameObject()).startDuck();
		this.setReady();
	}

	@Override
	public void reset() {
		this.setNotReady();
	}

}
