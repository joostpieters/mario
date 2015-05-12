package jumpingalien.model;


public class StartDuck extends Statement {

	public StartDuck() {
	}

	@Override
	public void execute(Program program) {
		// TODO checkers
		((Mazub) program.getGameObject()).startDuck();
		this.setReady();
	}

	@Override
	public void reset() {
		this.setNotReady();
	}

}
