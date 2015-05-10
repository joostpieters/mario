package jumpingalien.model;

import java.util.Map;

public class StartJump extends Statement {

	public StartJump() {
	}

	@Override
	public Map<String, Type> execute(Map<String, Type> var) {
		// TODO checkers
		((Mazub) this.getGameObject()).startJump();
		this.setReady();
		return var;
	}

}
