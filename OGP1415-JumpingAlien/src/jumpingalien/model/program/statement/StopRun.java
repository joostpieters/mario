package jumpingalien.model.program.statement;


import jumpingalien.model.Orientation;
import jumpingalien.model.Type;
import jumpingalien.model.program.Program;
import jumpingalien.model.program.expression.Expression;
import jumpingalien.part3.programs.IProgramFactory.Direction;

public class StopRun extends Statement {

	public StopRun(Expression<Direction> expr) {
		if (expr.getType() != Type.DIRECTION) {
			throw new IllegalArgumentException();
		}
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
	
	@Override
	public boolean isWellFormed() {
		if(this.getLoopStatement(this) instanceof ForEachStatement) {
			return false;
		}
		return true;
	}
}
