package program.statement;


import program.Program;
import program.expression.Expression;
import jumpingalien.model.Mazub;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
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
			 // ((GameObject) program.getGameObject()).setOrientationRight();
			if (program.getGameObject() instanceof Mazub) {
				((Mazub) program.getGameObject()).startMoveRight();
			}
			else if (program.getGameObject() instanceof Slime) {
				((Slime) program.getGameObject()).startMoveRight();
			}
			else if (program.getGameObject() instanceof Shark) {
				((Shark) program.getGameObject()).startMoveRight();
			}
		}
		else if (this.getDirection().evaluate(program) == Direction.LEFT) {
			// ((GameObject) program.getGameObject()).setOrientationLeft();
			if (program.getGameObject() instanceof Shark) {
				((Shark) program.getGameObject()).startMoveLeft();
			}
			else if (program.getGameObject() instanceof Slime) {
				((Slime) program.getGameObject()).startMoveLeft();
			}
			else if (program.getGameObject() instanceof Shark) {
				((Shark) program.getGameObject()).startMoveLeft();
			}
		}
		else {
			this.setReady();
		}
		
		// TODO Voor alle gameObject ne startmove maken
		this.setReady();
	}
	
	@Override
	public void reset() {
		this.setNotReady();
	}

}
