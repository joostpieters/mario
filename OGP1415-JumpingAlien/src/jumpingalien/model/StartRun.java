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
	public Map<String, Type> execute(Map<String, Type> var) {
		// TODO checkers
		if (this.getDirection().evaluate() == Direction.RIGHT) {
			((Mazub) this.getGameObject()).setOrientationRight();
		}
		else if (this.getDirection().evaluate() == Direction.LEFT) {
			((Mazub) this.getGameObject()).setOrientationLeft();
		}
		else {
			this.setReady();
			return var;
		}
		((Mazub) this.getGameObject()).startMove();
		this.setReady();
		return var;
	}
	
	@Override
	public void reset() {
		this.setNotReady();
	}

}
