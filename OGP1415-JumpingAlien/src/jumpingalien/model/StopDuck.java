package jumpingalien.model;

import java.util.Map;

public class StopDuck extends Statement {

	public StopDuck() {
	}

	@Override
	public Map<String, Type> execute(Map<String, Type> var) {
		// TODO checkers
		((Mazub) this.getGameObject()).endDuck();
		this.setReady();
		return var;
	}

	@Override
	public void reset() {
		this.setNotReady();
	}
}
