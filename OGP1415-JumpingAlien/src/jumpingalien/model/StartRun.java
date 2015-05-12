package jumpingalien.model;

import java.util.Map;

import jumpingalien.part3.programs.IProgramFactory.Direction;

public class StartRun extends Statement {

	public StartRun(Expression<Direction> expr) {
		this.setDirection(expr);
	}

	private Expression<Direction> direction;
	protected Expression<Direction> getDirection() {
		return direction;
	}
	private void setDirection(Expression<Direction> expr) {
		this.direction = expr;
	}
	
	@Override
	public void execute(Program program) {
		// TODO checkers
		if (this.getDirection().evaluate(program) == Direction.RIGHT) {
			((GameObject) program.getGameObject()).setOrientationRight();
		}
		else if (this.getDirection().evaluate(program) == Direction.LEFT) {
			((GameObject) program.getGameObject()).setOrientationLeft();
		}
		else {
			this.setReady();
		}
		((GameObject) program.getGameObject()).startMove();
		// TODO Voor alle gameObject ne startmove maken
		this.setReady();
	}
	
	@Override
	public void reset() {
		this.setNotReady();
	}

}
