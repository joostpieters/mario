package program.statement;


import program.Program;
import program.expression.Expression;
import jumpingalien.model.Orientation;
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
				program.getGameObject().getOrientation() == Orientation.RIGHT) {
			program.getGameObject().stopMovingX();
		}
		else if (this.getDirection().evaluate(program) == Direction.LEFT &&
				program.getGameObject().getOrientation() == Orientation.LEFT) {
			program.getGameObject().stopMovingX();
		}
		this.setReady();
	}

	@Override
	public void reset() {
		this.setNotReady();
	}
}
