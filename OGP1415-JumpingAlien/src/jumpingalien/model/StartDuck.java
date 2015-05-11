package jumpingalien.model;

import java.util.Map;

public class StartDuck extends Statement {

	public StartDuck() {
	}

	@Override
	public Map<String, Type> execute(Map<String, Type> var) {
		// TODO checkers
		((Mazub) this.getGameObject()).startDuck();
		this.setReady();
		return var;
	}

	@Override
	public void reset() {
		this.setNotReady();
	}

}
