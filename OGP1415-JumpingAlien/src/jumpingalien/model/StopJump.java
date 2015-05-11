package jumpingalien.model;

import java.util.Map;

public class StopJump extends Statement {

	public StopJump() {
	}

	@Override
	public Map<String, Type> execute(Map<String, Type> var) {
		// TODO checkers
		((Mazub) this.getGameObject()).endJump();
		this.setReady();
		return var;
	}
	
	@Override
	public void reset() {
		this.setNotReady();
	}

}
