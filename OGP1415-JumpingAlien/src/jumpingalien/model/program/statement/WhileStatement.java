package jumpingalien.model.program.statement;

import jumpingalien.model.Type;
import jumpingalien.model.program.Program;
import jumpingalien.model.program.expression.Expression;

public class WhileStatement extends LoopStatement {

	public WhileStatement(Expression<Boolean> condition, Statement body) {
		if (condition.getType() != Type.BOOLEAN) {
			throw new IllegalArgumentException();
		}
		this.setCondition(condition);
		this.setBody(body);
	}
	
	private Expression<Boolean> condition;
	protected Expression<Boolean> getCondition() {
		return condition;
	}
	private void setCondition(Expression<Boolean> expr) {
		this.condition = expr;
	}
	
	@Override
	public void execute(Program program) {
		if (!this.getInBody()) {
			if (this.getCondition().evaluate(program)) {
				this.setInBody(true);
			}
			else {
				this.setReady();
			}
		}
		else {
			this.getBody().execute(program);
			if(this.getBody().isReady()) {
				this.getBody().setNotReady();
				this.setInBody(false);
			}
		}
	}
	@Override
	public void reset() {
		this.setNotReady();
		this.setInBody(false);
	}
	
	@Override
	public boolean isWellFormed() {
		return this.getBody().isWellFormed();
	}
}
