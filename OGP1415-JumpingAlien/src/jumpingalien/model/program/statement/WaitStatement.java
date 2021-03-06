package jumpingalien.model.program.statement;

import jumpingalien.model.Type;
import jumpingalien.model.program.Program;
import jumpingalien.model.program.expression.Expression;

public class WaitStatement extends Statement {

	public WaitStatement(Expression<Double> duration) {
		if (duration.getType() != Type.DOUBLE) {
			throw new IllegalArgumentException();
		}
		this.setDuration(duration);
	}
	
	private Expression<Double> duration;
	protected Expression<Double> getDuration() {
		return duration;
	}
	private void setDuration(Expression<Double> expr) {
		this.duration = expr;
	}
	
	private double timePassed;
	private double getTimePassed() {
		return timePassed;
	}
	private void setTimePassed(double timePassed) {
		this.timePassed = timePassed;
	}
	
	@Override
	public void execute(Program program) {
		if(this.getTimePassed() >= this.getDuration().evaluate(program)) {
			this.setTimePassed(0);
			this.setReady();
		}
		else {
			this.setTimePassed(getTimePassed() + this.getExecutionTime());
		}
	}
	
	@Override
	public void reset() {
		this.setNotReady();
		this.setTimePassed(0);
	}
	
	@Override
	public boolean isWellFormed() {
		if(this.getLoopStatement(this) instanceof ForEachStatement) {
			return false;
		}
		return true;
	}
}
