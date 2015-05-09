package jumpingalien.model;

import java.util.Map;

public class WhileStatement extends Statement {

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
	}
	
	private boolean inBody;
	private boolean getInBody() {
		return inBody;
	}
	private void setInBody(boolean b) {
		this.inBody = b;
	}
	
	@Override
	public Map<String, Type> execute(Map<String, Type> var) {
		if (!this.getInBody()) {
			if (this.getCondition().evaluate()) {
				this.setInBody(true);
			}
			else {
				this.setReady();
			}
			return var;
		}
		else {
			Map<String, Type> var2 = this.getBody().execute(var);
			if(this.getBody().isReady()) {
				this.getBody().setNotReady();
				this.setInBody(false);
			}
			// TODO dit return statement zal wel anders moeten
			return var2;	
		}
	}
}
