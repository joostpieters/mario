package jumpingalien.model;

import java.util.Map;

public class WaitStatement extends Statement {

	public WaitStatement(Expression<Double> duration) {
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
	
}
