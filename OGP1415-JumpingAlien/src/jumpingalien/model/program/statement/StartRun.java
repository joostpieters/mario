package jumpingalien.model.program.statement;


import jumpingalien.model.program.Program;
import jumpingalien.model.program.expression.Expression;
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
			program.getGameObject().startMoveRight();
		}
		else if (this.getDirection().evaluate(program) == Direction.LEFT) {
			program.getGameObject().startMoveLeft();
		}
		else {
			throw new IllegalArgumentException();
		}
		this.setReady();
	}
	
	@Override
	public void reset() {
		this.setNotReady();
	}

}
