package jumpingalien.model.program.statement;

import jumpingalien.model.program.Program;
import jumpingalien.model.program.expression.Expression;

public class WhileStatement extends LoopStatement {

	public WhileStatement(Expression<Boolean> condition, Statement body) {
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
	
	private Statement body;
	private Statement getBody() {
		return this.body;
	}
	private void setBody(Statement stat) {
		this.body = stat;
		stat.setSuperStatement(this);
	}
	
//	private boolean inBody;
//	private boolean getInBody() {
//		return inBody;
//	}
//	private void setInBody(boolean b) {
//		this.inBody = b;
//	}
	
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
}
