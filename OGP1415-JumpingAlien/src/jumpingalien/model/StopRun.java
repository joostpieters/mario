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
	public void execute(Program program) {
		// TODO checkers
		
		//TODO nog functies toevoegen in GameOjbect -> stopMovingX bestond al
		if (this.getDirection().evaluate(program) == Direction.RIGHT &&
				((GameObject) program.getGameObject()).getOrientation() == Orientation.RIGHT) {
			((GameObject) program.getGameObject()).stopMovingX();
		}
		else if (this.getDirection().evaluate(program) == Direction.LEFT &&
				((GameObject) program.getGameObject()).getOrientation() == Orientation.LEFT) {
			((GameObject) program.getGameObject()).stopMovingX();
		}
		this.setReady();
	}

	@Override
	public void reset() {
		this.setNotReady();
	}
}
