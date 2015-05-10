package jumpingalien.model;

import java.util.Map;

import jumpingalien.part3.programs.IProgramFactory.Direction;

public class StopRun extends Statement {

	public StopRun(Expression<Direction> expr) {
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
		if (this.getDirection().evaluate() == Direction.RIGHT &&
				((Mazub) this.getGameObject()).getOrientation() == Orientation.RIGHT) {
			((Mazub) this.getGameObject()).endMoveRight();
		}
		else if (this.getDirection().evaluate() == Direction.LEFT &&
				((Mazub) this.getGameObject()).getOrientation() == Orientation.LEFT) {
			((Mazub) this.getGameObject()).endMoveLeft();
		}
		this.setReady();
		return var;
	}

}
