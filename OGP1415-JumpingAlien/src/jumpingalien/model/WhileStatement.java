package jumpingalien.model;

import java.util.Map;

public class WhileStatement extends Statement {

	public WhileStatement(Expression<Boolean> condition, Statement body) {
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
	
	@Override
	public Map<String, Type> execute(Map<String, Type> var) {
		// TODO Auto-generated method stub
		return null;
	}

}
